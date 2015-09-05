package eu.entropy.mediapedia.company.apigee;

import com.google.common.base.Function;
import com.google.common.collect.FluentIterable;

import java.util.ArrayList;
import java.util.List;

import eu.entropy.mediapedia.company.Company;

public class CompanyConverterService {
    public List<Company> fromApigeeCompanyResult(ApigeeCompanyResult apigeeCompanyResult) {
        return FluentIterable
                .from(apigeeCompanyResult.getEntities())
                .transform(new ToCompany())
                .toList();
    }

    private class ToCompany implements Function<Entity, Company> {
        @Override
        public Company apply(Entity entity) {
            return Company.builder()
                    .id(entity.getName())
                    .logo(entity.getLogo())
                    .name(entity.getCorporateName())
                    .assets(entity.getAssets())
                    .owners(entity.getOwners())
                    .revenue(entity.getRevenue())
                    .unit(entity.getUnit())
                    .build();
        }
    }
}
