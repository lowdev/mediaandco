package eu.entropy.mediapedia.company;

import android.util.Log;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

import eu.entropy.mediapedia.company.apigee.ApigeeCompanyResult;
import eu.entropy.mediapedia.company.apigee.CompanyConverterService;
import retrofit.RestAdapter;

public class CompanyRepository {
    private CompanyApigeeService service;
    private CompanyConverterService companyConverterService;
    private ExecutorService executorService;

    public CompanyRepository() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint("https://api.usergrid.com/lowentropydev/sandbox")
                .build();
        service = restAdapter.create(CompanyApigeeService.class);
        companyConverterService = new CompanyConverterService();
        executorService = Executors.newFixedThreadPool(2);
    }

    public List<Company> findAll(final CompanySpecification companySpecification) {
        if (companySpecification.getClause().isEmpty()) {
            return new ArrayList<>();
        }

        Callable<ApigeeCompanyResult> callable = new Callable<ApigeeCompanyResult>() {
            @Override
            public ApigeeCompanyResult call() throws Exception {
                return service.findAll(companySpecification.getClause());
            }
        };

        FutureTask<ApigeeCompanyResult> futureTask = new FutureTask<>(callable);
        executorService.execute(futureTask);

        try {
            return companyConverterService.fromApigeeCompanyResult(futureTask.get());
        } catch (InterruptedException e) {
            Log.e("mediaandco", "Connection issue", e);
        } catch (ExecutionException e) {
            Log.e("mediaandco", "Connection issue", e);
        }
        return new ArrayList<>();
    }

    public Company findById(final String companyId) {
        Callable<ApigeeCompanyResult> callable = new Callable<ApigeeCompanyResult>() {
            @Override
            public ApigeeCompanyResult call() throws Exception {
                return service.findById(companyId);
            }
        };

        FutureTask<ApigeeCompanyResult> futureTask = new FutureTask<>(callable);
        executorService.execute(futureTask);

        try {
            return companyConverterService.fromApigeeCompanyResult(futureTask.get()).get(0);
        } catch (InterruptedException e) {
            Log.e("mediaandco", "Connection issue", e);
        } catch (ExecutionException e) {
            Log.e("mediaandco", "Connection issue", e);
        }
        return Company.NOT_FOUND;
    }

    public List<Company> findByIds(Iterable<String> ids) {
        return findAll(CompanySpecification.builder().ids(Lists.newArrayList(ids)).build());
    }
}
