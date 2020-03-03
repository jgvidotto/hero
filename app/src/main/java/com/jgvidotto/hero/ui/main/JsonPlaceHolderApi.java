package com.jgvidotto.hero.ui.main;

import com.jgvidotto.hero.ui.main.Model.CharacterDataWrapper;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface JsonPlaceHolderApi {

    @GET("characters")
    Call<CharacterDataWrapper> getResults(@Query("ts")String ts,
                                          @Query("apikey")String apikey,
                                          @Query("hash")String hash);

    @GET("characters")
    Call<CharacterDataWrapper> getSearchResults(@Query("ts")String ts,
                                          @Query("apikey")String apikey,
                                          @Query("hash")String hash,
                                          @Query("name")String name);
}
