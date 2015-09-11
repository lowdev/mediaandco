package eu.entropy.mediapedia.company;

import android.util.Log;

import com.google.common.base.Function;
import com.google.common.collect.FluentIterable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

import eu.entropy.mediapedia.company.apigee.ApigeeCompanyResult;
import eu.entropy.mediapedia.company.apigee.CompanyConverterService;
import retrofit.Callback;
import retrofit.RestAdapter;

public class CompanyRepository {
    private CompanyApigeeService service;
    private CompanyConverterService companyConverterService;
    private ExecutorService executorService;
    private Map<String, List<Company>> companyCache;

    public CompanyRepository() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint("https://api.usergrid.com/lowentropydev/sandbox")
                .build();
        service = restAdapter.create(CompanyApigeeService.class);
        companyConverterService = new CompanyConverterService();
        executorService = Executors.newFixedThreadPool(2);
        companyCache = new HashMap<>();
    }

    public List<Company> findAll(final CompanySpecification companySpecification) {
        String clause = companySpecification.getClause();
        if (clause.isEmpty()) {
            return new ArrayList<>();
        }

        if (null != companyCache.get(clause)) {
            return companyCache.get(clause);
        }

        Callable<ApigeeCompanyResult> callable = new Callable<ApigeeCompanyResult>() {
            @Override
            public ApigeeCompanyResult call() throws Exception {
                return service.findAll(50, companySpecification.getClause());
            }
        };

        FutureTask<ApigeeCompanyResult> futureTask = new FutureTask<>(callable);
        executorService.execute(futureTask);

        try {
            List<Company> companies = companyConverterService.fromApigeeCompanyResult(futureTask.get());
            companyCache.put(clause, companies);
            return companies;
        } catch (InterruptedException e) {
            Log.e("mediaandco", "Connection issue", e);
        } catch (ExecutionException e) {
            Log.e("mediaandco", "Connection issue", e);
        }
        return new ArrayList<>();
    }

    public Company findById(final String companyId) {
        if (null != companyCache.get(companyId)) {
            return companyCache.get(companyId).get(0);
        }

        Callable<ApigeeCompanyResult> callable = new Callable<ApigeeCompanyResult>() {
            @Override
            public ApigeeCompanyResult call() throws Exception {
                return service.findById(companyId);
            }
        };

        FutureTask<ApigeeCompanyResult> futureTask = new FutureTask<>(callable);
        executorService.execute(futureTask);

        try {
            Company company = companyConverterService.fromApigeeCompanyResult(futureTask.get()).get(0);
            List<Company> companies = new ArrayList<>();
            companies.add(company);
            companyCache.put(companyId, companies);

            return company;
        } catch (InterruptedException e) {
            Log.e("mediaandco", "Connection issue", e);
        } catch (ExecutionException e) {
            Log.e("mediaandco", "Connection issue", e);
        }
        return Company.NOT_FOUND;
    }

    public List<Company> findByIds(Iterable<String> ids) {
        return FluentIterable
                .from(ids)
                .transform(new ToCompany())
                .toList();
    }

    private class ToCompany implements Function<String, Company> {
        @Override
        public Company apply(String input) {
            return findById(input);
        }
    }
}
