package com.smc.firstapp.api;

import com.smc.firstapp.model.SearchRes;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface IApi {

    @GET("/v1/search/shop.json")
    @Headers({
            "X-Naver-Client-Id:zUaz8k7jeF4eUno8mx0O",
            "X-Naver-Client-Secret:3LBBhY4aMf"
    })
    Call<SearchRes> search(@Query("query") String keyword);

}
