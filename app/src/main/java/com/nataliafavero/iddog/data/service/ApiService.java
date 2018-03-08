package com.nataliafavero.iddog.data.service;

import com.nataliafavero.iddog.data.model.FeedResponse;
import com.nataliafavero.iddog.data.model.SignupRequest;
import com.nataliafavero.iddog.data.model.SignupResponse;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by nataliafavero on 06/03/18.
 */

public class ApiService {
    private static String BASE_URL = "https://iddog-api.now.sh/";

    public IdDogApi getApi() {
        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(IdDogApi.class);
    }

    public interface IdDogApi {
        @POST("signup")
        Call<SignupResponse> signup(@Body SignupRequest email);

        @GET("feed")
        Call<FeedResponse> feed(@Header("Authorization") String authorization, @Query("category") String category);
    }

}
