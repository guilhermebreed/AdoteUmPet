package com.example.italo.adoteumpet.ui.controller;

import android.content.Intent;
import android.util.Log;

import com.example.italo.adoteumpet.data.model.AnimalApi;
import com.example.italo.adoteumpet.data.model.PessoaApi;
import com.example.italo.adoteumpet.ui.interfaces.api.IAnimalApi;
import com.example.italo.adoteumpet.ui.interfaces.api.IPessoaApi;
import com.example.italo.adoteumpet.ui.view.MainActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Italo on 20/11/2016.
 */

public class PessoaController {
    public static PessoaApi pessoaLogada;
    public static List<PessoaApi> pessoas = new ArrayList<>();

    public PessoaController() {
        atualizarLista();
    }

    public static PessoaApi getPessoaLogada() {
        return pessoaLogada;
    }

    public static void setPessoas(List<PessoaApi> p){
        pessoas.addAll(p);
    }

    public List<PessoaApi> getPessoas(){
        return pessoas;
    }

    public static void setPessoaLogada(PessoaApi pessoaLogada) {
        PessoaController.pessoaLogada = pessoaLogada;
    }

    public boolean alguemLogado(){
        if(pessoaLogada == null){
            return false;
        }
        return true;
    }

    public static void atualizarLista(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(IPessoaApi.API_LOCATION)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        IPessoaApi service = retrofit.create(IPessoaApi.class);
        Call<List<PessoaApi>> pessoasResponse = service.getPessoas();

        pessoasResponse.enqueue(new Callback<List<PessoaApi>>(){
            @Override
            public void onResponse(Call<List<PessoaApi>> call, Response<List<PessoaApi>> response) {
                if(response.isSuccessful()){
                    List<PessoaApi> p = response.body();
                    Log.e("Erro ao Atualizar Lista"," Tamanho da lista"+p.size());
                    if(PessoaController.pessoas != null){
                        PessoaController.pessoas.removeAll(PessoaController.pessoas);
                        PessoaController.setPessoas(p);
                    }else {
                        PessoaController.setPessoas(p);
                    }
                    Log.e("Erro ao Atualizar Lista"," Tamanho da lista Pessoas "+PessoaController.pessoas.size());
                }else{
                    Log.e("Erro ao Atualizar Lista",response.message());
                }
            }

            @Override
            public void onFailure(Call<List<PessoaApi>> call, Throwable t) {
                Log.e("Erro ao Tentar Conectar","Não foi possível conectar");
            }
        });

    }

    public static boolean salvarPessoa(PessoaApi pessoaApi){
        try{
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(IPessoaApi.API_LOCATION)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            IPessoaApi service = retrofit.create(IPessoaApi.class);

            Call<PessoaApi> pessoaPost = service.postPessoa(pessoaApi);
            pessoaPost.enqueue(new Callback<PessoaApi>() {
                @Override
                public void onResponse(Call<PessoaApi> call, Response<PessoaApi> response) {
                    int statusCode = response.code();
                    PessoaApi pessoaApi = response.body();
                    Log.d("Salvar Pessoa", "pessoa salvo" + statusCode);
                }

                @Override
                public void onFailure(Call<PessoaApi> call, Throwable t) {
                    Log.d("Salvar Pessoa", "Erro: " + t.getMessage());
                }
            });
        }catch (Exception ex){
            return false;
        }
        return true;
    }

    public static boolean realizarLogin (String user, String senha) {
        atualizarLista();
        Log.d("Passei por"," Pessoa com "+pessoas.size());
        for (int index =pessoas.size()-1; index >= 0; index--) {
            Log.d("Passei por"," Pessoa com nome "+pessoas.get(index).getNomePessoa());
            if (pessoas.get(index).getUsuario().equals(user) && pessoas.get(index).getSenha().equals(senha)) {
                PessoaController.setPessoaLogada(pessoas.get(index));
                return true;
            }
        }
        return false;
    }
    public static void fazerLogoff(){
        setPessoaLogada(null);
    }
}
