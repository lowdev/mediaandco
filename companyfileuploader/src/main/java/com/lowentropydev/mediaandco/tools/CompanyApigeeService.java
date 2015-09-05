package com.lowentropydev.mediaandco.tools;

import retrofit.Callback;
import retrofit.http.POST;
import retrofit.http.Path;

public interface CompanyApigeeService {

    @POST("/companies%20-d%20'{json}'")
    void upload(@Path("json") String json, Callback<Object> callback);
}
