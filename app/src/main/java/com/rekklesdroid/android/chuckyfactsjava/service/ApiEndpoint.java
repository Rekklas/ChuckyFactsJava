package com.rekklesdroid.android.chuckyfactsjava.service;

import com.rekklesdroid.android.chuckyfactsjava.entity.JsonResult;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiEndpoint {

    @GET("/jokes")
    Call<JsonResult> getJokes();
}
