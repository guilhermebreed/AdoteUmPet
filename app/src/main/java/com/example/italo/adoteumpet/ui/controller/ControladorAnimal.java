package com.example.italo.adoteumpet.ui.controller;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.CursorLoader;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.italo.adoteumpet.R;
import com.example.italo.adoteumpet.data.model.AnimalApi;
import com.example.italo.adoteumpet.ui.view.MainActivity;
import com.example.italo.adoteumpet.ui.interfaces.api.IAnimalApi;
import com.example.italo.adoteumpet.ui.view.UploadFoto;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.italo.adoteumpet.R.mipmap.dog1;

/**
 * Created by Italo on 06/10/2016.
 */

public class ControladorAnimal {
    private static ImageView img;
    private static String path;

    private static List<AnimalApi> animais = new ArrayList<>();
    private static List<AnimalApi> animaisListaCompleta = new ArrayList<>();
    public static List<AnimalApi> getAnimais() {
        return animais;
    }

    public static void setAnimais(List<AnimalApi> animais) {
        ControladorAnimal.animais.addAll(animais);
    }

    public static void setAnimaisListaCompleta(List<AnimalApi> animais){
        ControladorAnimal.animaisListaCompleta.addAll(animais);
    }

    public static void getAnimaisOrdenado(String pesquisa, int opcao){
        zerarLista();
        animais.addAll(animaisListaCompleta);
        List<AnimalApi> resultado = new ArrayList<>();
        if(opcao == 0){
            resultado.addAll(animais);
        }
        else if(opcao == 1) {
            for (int index = animais.size() - 1; index >= 0; index--) {
                AnimalApi corrente = animais.get(index);

                if (corrente.getRaca().toString().toLowerCase().contains(pesquisa.toLowerCase())) { //pesquisa.equalsIgnoreCase(corrente.getRaca().toString().trim())
                    resultado.add(corrente);
                }
            }
        }else if(opcao == 3){
            for (int index = animais.size() - 1; index >= 0; index--) {
                AnimalApi corrente = animais.get(index);
                if (Integer.parseInt(pesquisa) == corrente.getIdade()) {
                    resultado.add(corrente);
                }
            }
        }
        zerarLista();
        setAnimais(resultado);
    }
    //public static String getAnimaisRaca(AnimalApi raca){return raca.getRaca();}

    public static void zerarLista(){
        animais.removeAll(animais);
    }

    public static void zerarLista(int valor){
        animais.removeAll(animais);
        animaisListaCompleta.removeAll(animaisListaCompleta);
    }

    public static void semFiltro(){
        zerarLista();
    }

    public static void atualizarLista(){
        //Rest Adapter
        //URL Base
        //Rest Adapter
        //Gson g = new GsonBuilder().registerTypeAdapter(Animal.class, new AnimalDes()).create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(IAnimalApi.API_LOCATION)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        IAnimalApi service = retrofit.create(IAnimalApi.class);
        Call<List<AnimalApi>> animaisResponse = service.getAnimais();

        animaisResponse.enqueue(new Callback<List<AnimalApi>>() {
            @Override
            public void onResponse(Call<List<AnimalApi>> call, Response<List<AnimalApi>> response) {
                if(response.isSuccessful()){
                    ControladorAnimal.zerarLista(1);

                    List<AnimalApi> animals = response.body();
                    ControladorAnimal.setAnimais(animals);
                    ControladorAnimal.setAnimaisListaCompleta(animals);
                    MainActivity.notificarAdapter();
                }else{
                    Log.e("Erro ao Atualizar Lista",response.message());
                }
            }

            @Override
            public void onFailure(Call<List<AnimalApi>> call, Throwable t) {
                Log.e("Erro ao Tentar Conectar","Não foi possível conectar");
            }
        });

    }

    //Upload de fotos
    public static int converterImagemCerta(String nome){
        if(nome.equalsIgnoreCase("dog1")){
            return R.mipmap.dog1;
        }else if(nome.equalsIgnoreCase("dog2")){
            return R.mipmap.dog2;
        }else{
            return R.mipmap.dogimg;
        }
    }
}
