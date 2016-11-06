package com.example.italo.adoteumpet.ui;

import com.example.italo.adoteumpet.data.model.Animal;
import com.example.italo.adoteumpet.data.model.AnimalApi;

import java.util.List;

import retrofit2.Callback;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by guilh on 02/11/2016.
 */

public interface IAnimalApi {
    //@GET("/animal/")
    //Call<AnimalApi> animais();
    //public void getFeed(@Query("id") String animal, Callback<IAnimalApi> response);

    @GET("Animal")
    retrofit2.Call<List<AnimalApi>> getAnimais();

    @POST("animal")
    Callback<AnimalApi> postAnimal(@Body AnimalApi animalApi); //@Body: Todos os parâmetro do Animal

}
