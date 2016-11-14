package com.example.italo.adoteumpet.ui;

import com.example.italo.adoteumpet.data.model.Animal;
import com.example.italo.adoteumpet.data.model.AnimalApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by guilh on 02/11/2016.
 */

public interface IAnimalApi {
    //@GET("/animal/")
    //Call<AnimalApi> animais();
    //public void getFeed(@Query("id") String animal, Callback<IAnimalApi> response);

    @GET("Animal")
    Call<List<AnimalApi>> getAnimais();

    @POST("Animal")
    Call<AnimalApi> postAnimal(@Body Animal animal); //@Body: Todos os parâmetro do Animal

    @DELETE("Animal/{id}")
    Call<AnimalApi> deleteAnimal(@Path("id") String id);
}
