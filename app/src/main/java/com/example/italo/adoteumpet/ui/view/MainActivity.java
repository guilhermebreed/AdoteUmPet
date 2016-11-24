package com.example.italo.adoteumpet.ui.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import com.example.italo.adoteumpet.R;
import com.example.italo.adoteumpet.ui.adapter.AnimalAdapter;
import com.example.italo.adoteumpet.data.model.AnimalApi;
import com.example.italo.adoteumpet.ui.controller.ControladorAnimal;
import com.example.italo.adoteumpet.ui.controller.PessoaController;
import com.example.italo.adoteumpet.ui.interfaces.api.IAnimalApi;

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
    private Button pesquisaBtn;
    private ListView animaisList;
    private TextView tv;
    private EditText pesquisa;
    private int opcao;
    //private AnimalApi animal;
    AnimalApi animal = new AnimalApi();
    //A String de localização da API


    private ControladorAnimal controladorAnimal;
    private PessoaController pessoaController;
    static private AnimalAdapter adapter;
    private int requestCode;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newButton = (Button) findViewById(R.id.new_button);
        animaisList = (ListView) findViewById(R.id.animais_list);
        tv = (TextView) findViewById(R.id.testid);
        pesquisa = (EditText) findViewById(R.id.filtroRaca);
        pesquisaBtn = (Button) findViewById(R.id.btnFiltro);

        pessoaController = new PessoaController();
        controladorAnimal = new ControladorAnimal();

        adapter = new AnimalAdapter(this,controladorAnimal.getAnimais());
        animaisList.setAdapter(adapter);
        animaisList.setOnItemClickListener(this);
        newButton.setOnClickListener(this);

        pesquisa.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String raca = String.valueOf(pesquisa.getText());
                if(raca == null || raca.equalsIgnoreCase("")) {
                    controladorAnimal.getAnimaisOrdenado(raca,0);
                }else if(raca != null){
                    try {
                        Integer.parseInt(raca);
                        controladorAnimal.getAnimaisOrdenado(raca, 3);
                    }catch (Exception ex){
                        controladorAnimal.getAnimaisOrdenado(raca, 1);
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        pesquisaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String raca = String.valueOf(pesquisa.getText());
                if(raca == null || raca.equalsIgnoreCase("")) {
                    controladorAnimal.getAnimaisOrdenado(raca,0);
                }else if(raca != null){
                    try {
                        Integer.parseInt(raca);
                        controladorAnimal.getAnimaisOrdenado(raca, 3);
                    }catch (Exception ex){
                        controladorAnimal.getAnimaisOrdenado(raca, 1);
                    }
                }
                adapter.notifyDataSetChanged();
                //filtro();
            }
        });
        if(pessoaController.alguemLogado()) {
            controladorAnimal.atualizarLista();
        }else{
            startActivityForResult(new Intent(this, LoginAcitivity.class), 2);
        }
    }

    @Override
    public void onClick(View v){
        startActivityForResult(new Intent(this, AnimalActivity.class), 1);
    }

    @Override
    protected void onActivityResult(int codigo, int resultado, Intent intent){
        if(resultado == 1){
            Toast.makeText(this,"Cadastrador com Sucesso!!", Toast.LENGTH_SHORT).show();
            controladorAnimal.atualizarLista();
        }else if(resultado == 2) {
            Toast.makeText(this, "Modificado com Sucesso!!", Toast.LENGTH_SHORT).show();
            controladorAnimal.atualizarLista();
        }else if(resultado == 3){
            Toast.makeText(this, "Excluido com Sucesso!!", Toast.LENGTH_SHORT).show();
            controladorAnimal.atualizarLista();
        }else if (resultado == 4){
            controladorAnimal.atualizarLista();
        }else if (resultado == 5){
            controladorAnimal.atualizarLista();
            Toast.makeText(this, "Seja bem vindo ao sistema adote um pet!",Toast.LENGTH_SHORT).show();
        }else if(resultado == 6){
            finish();
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
    //Verificar e colocar na Controlador Animal
    public void filtro(){
       AnimalApi animal = new AnimalApi();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(IAnimalApi.API_LOCATION)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        String raca = String.valueOf(pesquisa.getText());

        IAnimalApi service = retrofit.create(IAnimalApi.class);
        final Call<List<AnimalApi>> getRaca = service.getAnimalByRaca(pesquisa.getText().toString().trim());

        getRaca.enqueue(new Callback<List<AnimalApi>>() {
            @Override
            public void onResponse(Call<List<AnimalApi>> call, Response<List<AnimalApi>> response) {
                if(response.isSuccessful()){
                    List<AnimalApi> filtroAnimal = response.body();
                    controladorAnimal.zerarLista();
                    controladorAnimal.setAnimais(filtroAnimal);
                    adapter.notifyDataSetChanged();
                }else{
                    tv.setText("Não foi possível pesquisar este infeliz");
                }
            }

            @Override
            public void onFailure(Call<List<AnimalApi>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Não foi possível pesquisar Falhou pq: "+t.getMessage(), Toast.LENGTH_LONG).show();

            }
        });

    }

    public static void notificarAdapter(){
        adapter.notifyDataSetChanged();
    }
}
