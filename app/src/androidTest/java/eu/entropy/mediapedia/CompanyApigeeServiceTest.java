package eu.entropy.mediapedia;

import eu.entropy.mediapedia.company.apigee.ApigeeCompanyResult;
import retrofit.RestAdapter;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

public class CompanyApigeeServiceTest {

    public static void main(String [] args) {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint("https://api.usergrid.com/lowentropydev/sandbox")
                .build();

        CompanyApigeeService2 service = restAdapter.create(CompanyApigeeService2.class);
        //ApigeeCompanyResult result = service.findAll(1, "mediatype='tv'");
        ApigeeCompanyResult result = service.findById("france2");
        result.getEntities();
    }

    public interface CompanyApigeeService2 {

        @GET("/companies/{id}")
        ApigeeCompanyResult findById(@Path("id") String id);

        @GET("/companies")
        ApigeeCompanyResult findAll(@Query("limit") int limit, @Query("ql") String query);
    }
}
