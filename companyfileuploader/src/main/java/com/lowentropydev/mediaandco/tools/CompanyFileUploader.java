package com.lowentropydev.mediaandco.tools;

import com.apigee.sdk.ApigeeClient;
import com.apigee.sdk.data.client.ApigeeDataClient;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Consumer;
import java.util.stream.Stream;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class CompanyFileUploader {

    public static void main(String... args) throws IOException {
        String ORGNAME = "your-org";
        String APPNAME = "your-app";

        ApigeeClient apigeeClient = new ApigeeClient(ORGNAME,APPNAME,null);
        ApigeeDataClient client = apigeeClient.getDataClient();

        /*RestAdapter restAdapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint("https://api.usergrid.com/lowentropydev/sandbox")
                .build();
        final CompanyApigeeService service = restAdapter.create(CompanyApigeeService.class);

        Callback<Object> callback = new Callback<Object>() {
            @Override
            public void success(Object o, Response response) {
            }

            @Override
            public void failure(RetrofitError error) {
                System.out.println(error);
            }
        };
        //new File("./companies/nt1.json").toPath())
        service.upload("{\"name\":\"titi\"}", callback);
        try (final Stream<Path> pathStream = Files.walk(Paths.get("./companies"), FileVisitOption.FOLLOW_LINKS)) {
            pathStream.forEach(new Consumer<Path>() {
                @Override
                public void accept(Path path) {
                    String content = pathtoString(path);
                    service.upload(content, new Callback<Object>() {
                        @Override
                        public void success(Object o, Response response) {

                        }

                        @Override
                        public void failure(RetrofitError error) {
                            System.out.println(error.getBody());
                        }
                    });
                }
            });
        } catch (final IOException e) {
        }*/
    }

    private static String pathtoString(Path path) {
        try {
            return new String(Files.readAllBytes(path));
        } catch (IOException e) {
            return "";
        }
    }
}
