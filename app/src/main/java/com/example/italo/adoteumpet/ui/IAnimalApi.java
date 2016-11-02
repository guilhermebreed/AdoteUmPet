package com.example.italo.adoteumpet.ui;

import android.telecom.Call;

import com.example.italo.adoteumpet.data.model.AnimalApi;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by guilh on 02/11/2016.
 */

public interface IAnimalApi {
    @GET("/animal/")
    //Call<AnimalApi> animais();
    public void getFeed(@Query("id") String animal, Callback<IAnimalApi> response);
}
