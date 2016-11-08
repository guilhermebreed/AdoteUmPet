package com.example.italo.adoteumpet.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.italo.adoteumpet.R;
import com.example.italo.adoteumpet.data.model.Animal;
import com.example.italo.adoteumpet.data.model.AnimalDes;
import com.example.italo.adoteumpet.ui.adapter.AnimalAdapter;
import com.example.italo.adoteumpet.data.model.AnimalApi;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
//import retrofit2.RestAdapter;
import retrofit2.Response;
import retrofit2.Retrofit;
//import retrofit2.RetrofitError;
//import retrofit2.client.Response;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private Button newButton;
    private ListView animaisList;
    private TextView tv;
    //private AnimalApi animal;
    AnimalApi animal = new AnimalApi();
    //A String de localização da API
    String API = "http://192.168.1.6:3000/api/";

    private ControladorAnimal controladorAnimal;
    private AnimalAdapter adapter;
    private int requestCode;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newButton = (Button) findViewById(R.id.new_button);
        animaisList = (ListView) findViewById(R.id.animais_list);
        tv = (TextView) findViewById(R.id.testid);

        controladorAnimal = new ControladorAnimal();
        adapter = new AnimalAdapter(this,controladorAnimal.getAnimais());
        animaisList.setAdapter(adapter);
        animaisList.setOnItemClickListener(this);
        newButton.setOnClickListener(this);

        atualizarLista();
    }

    @Override
    public void onClick(View v){
        startActivityForResult(new Intent(this, AnimalActivity.class), 1);
    }

    @Override
    protected void onActivityResult(int codigo, int resultado, Intent intent){
        if(resultado == 1){
            Toast.makeText(this,"Cadastrador com Sucesso!!", Toast.LENGTH_SHORT).show();
            atualizarLista();
            adapter.notifyDataSetChanged();
        }else if(resultado == 2) {
            Toast.makeText(this, "Modificado com Sucesso!!", Toast.LENGTH_SHORT).show();
            atualizarLista();
            adapter.notifyDataSetChanged();
        }else if(resultado == 3){
            Toast.makeText(this, "Excluido com Sucesso!!", Toast.LENGTH_SHORT).show();
            adapter.notifyDataSetChanged();
            atualizarLista();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id){

        Bundle extras = new Bundle();
        extras.putInt("idModificar",position);
        Intent it = new Intent(this, AnimalEditarActivity.class);
        it.putExtras(extras);
        startActivityForResult(it, 2);
    }

    public void atualizarLista(){
        //Rest Adapter
        //URL Base
        //Rest Adapter
        //Gson g = new GsonBuilder().registerTypeAdapter(Animal.class, new AnimalDes()).create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        IAnimalApi service = retrofit.create(IAnimalApi.class);
        final Call<List<AnimalApi>> animais = service.getAnimais();

        animais.enqueue(new Callback<List<AnimalApi>>() {
            @Override
            public void onResponse(Call<List<AnimalApi>> call, Response<List<AnimalApi>> response) {
                if(response.isSuccessful()){
                    controladorAnimal.zerarLista();
                    List<AnimalApi> animals = response.body();
                    for(AnimalApi a: animals){
                        controladorAnimal.animais.add(a);

                        adapter.notifyDataSetChanged();
                    }
                }else{
                    tv.setText("Erro");
                }
            }

            @Override
            public void onFailure(Call<List<AnimalApi>> call, Throwable t) {
                tv.setText("Não foi possível listar");
            }
        });
    }
}
