package com.smc.firstapp.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface IApi {

    @GET("/v1/search/shop.json")
    @Headers({
            "X-Naver-Client-Id:6Q529WyLo_JxhsIzN62X",
            "X-Naver-Client-Secret:k2mp3yt9At"
    })
    Call<Object> search(@Query("query") String keyword);


}
