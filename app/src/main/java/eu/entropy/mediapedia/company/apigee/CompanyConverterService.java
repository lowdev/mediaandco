package eu.entropy.mediapedia.company.apigee;

import com.google.common.base.Function;
import com.google.common.collect.FluentIterable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import eu.entropy.mediapedia.company.Company;
import eu.entropy.mediapedia.company.Revenue;

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
            Map<String, Double> assets = entity.getAssets();
            if (null == assets) {
                assets = new HashMap<>();
            }

            Map<String, Double> owners = entity.getOwners();
            if (null == owners) {
                owners = new HashMap<>();
            }

            Revenue revenue = Revenue.NONE;
            ApigeeRevenue apigeeRevenue = entity.getRevenue();
            if (null != apigeeRevenue) {
                revenue = Revenue.builder()
                        .value(apigeeRevenue.getValue()).unit(apigeeRevenue.getUnit())
                        .currency(apigeeRevenue.getCurrency()).year(apigeeRevenue.getYear()).build();
            }

            return Company.builder()
                    .id(entity.getName())
                    .logo(entity.getLogo())
                    .name(entity.getCorporateName())
                    .assets(assets)
                    .owners(owners)
                    .revenue(revenue)
                    .build();
        }
    }
}
