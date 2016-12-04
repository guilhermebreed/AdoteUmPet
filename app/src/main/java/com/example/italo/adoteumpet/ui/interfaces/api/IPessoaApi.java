package com.example.italo.adoteumpet.ui.interfaces.api;

import com.example.italo.adoteumpet.data.model.AnimalApi;
import com.example.italo.adoteumpet.data.model.PessoaApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Italo on 20/11/2016.
 */

public interface IPessoaApi{

    final String API_LOCATION = "http://192.168.1.6:3000/api/v1.1/";

    @GET("Pessoa")
    Call<List<PessoaApi>> getPessoas();

    @GET("Pessoa/{id}")
    Call<PessoaApi> buscarPessoaID(@Path("id") String id);

    @POST("Pessoa")
    Call<PessoaApi> postPessoa(@Body PessoaApi pessoaApi); //@Body: Todos os parâmetro da Pessoa

    @DELETE("Pessoa/{id}")
    Call<PessoaApi> deletePessoa(@Path("id") String id);

}
