package eu.entropy.mediapedia;

import eu.entropy.mediapedia.company.CompanyApigeeService;
import eu.entropy.mediapedia.company.apigee.ApigeeCompanyResult;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class CompanyApigeeServiceTest {

    public static void main(String [] args) {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint("https://api.usergrid.com/lowentropydev/sandbox")
                .build();

        CompanyApigeeService service = restAdapter.create(CompanyApigeeService.class);

       service.findAll("select * where name='le_monde'", new Callback<ApigeeCompanyResult>() {
           @Override
           public void success(ApigeeCompanyResult apigeeCompanyResult, Response response) {
               System.out.print(apigeeCompanyResult.getAction());
           }

           @Override
           public void failure(RetrofitError error) {

           }
       });
    }
}
