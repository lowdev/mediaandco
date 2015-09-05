package eu.entropy.mediapedia.company;

import eu.entropy.mediapedia.company.apigee.ApigeeCompanyResult;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

public interface CompanyApigeeService {

    @GET("/companies/{id}")
    ApigeeCompanyResult findById(@Path("id") String id);

    @GET("/companies")
    ApigeeCompanyResult findAll(@Query("ql") String query);
}
