package com.example.italo.adoteumpet.ui.view;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.italo.adoteumpet.R;
import com.example.italo.adoteumpet.data.model.Animal;
import com.example.italo.adoteumpet.data.model.AnimalApi;
import com.example.italo.adoteumpet.ui.controller.ControladorAnimal;
import com.example.italo.adoteumpet.ui.controller.PessoaController;
import com.example.italo.adoteumpet.ui.interfaces.api.IAnimalApi;

//import retrofit.Callback;
//import retrofit.RestAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Italo on 06/10/2016.
 */

public class AnimalActivity extends AppCompatActivity{

    private EditText cadNomeAnimal;
    private EditText cadDescricaoAnimal;
    private EditText cadIdadeAnimal;
    private EditText cadRacaAnimal;
    private Button cadIncluirAnimal;
    private ControladorAnimal controladorAnimal = new ControladorAnimal();
    private String textoRaca;
    private int ra;
    private String textoTipoAnimal;
    private int tip;

    private PessoaController pessoaController = new PessoaController();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.animal_cadastro);

        cadNomeAnimal = (EditText) findViewById(R.id.cad_nome_animal);
        cadDescricaoAnimal = (EditText) findViewById(R.id.cad_descricao_animal);
        cadIdadeAnimal = (EditText) findViewById(R.id.cad_idade_animal);
        cadRacaAnimal = (EditText) findViewById(R.id.cad_raca_animal);
        cadIncluirAnimal = (Button) findViewById(R.id.cad_btnIncluir);

        /*//Spinner Raça
        // Cria um ArraAdapter usando um array de string e um layout padrão de spinner
        Spinner spnRaca = (Spinner)
                findViewById(R.id.spnRaca);
        ArrayAdapter adapterRaca =
                ArrayAdapter.createFromResource(this, R.array.Raca, android.R.layout.simple_spinner_item);
        //alterar a fonte de dados(adapter) do Spinner
        spnRaca.setAdapter(adapterRaca);

        textoRaca = spnRaca.getSelectedItem().toString();
        ra = spnRaca.getSelectedItemPosition();


        //Spinner TipoAnimal
        // Cria um ArraAdapter usando um array de string e um layout padrão de spinner
        Spinner spnTipoAnimal = (Spinner)
                findViewById(R.id.spnTipoAnimal);
        ArrayAdapter adapterTipoAnimal =
                ArrayAdapter.createFromResource(this, R.array.TipoAnimal, android.R.layout.simple_spinner_item);
        //alterar a fonte de dados(adapter) do Spinner
        spnTipoAnimal.setAdapter(adapterTipoAnimal);

        textoTipoAnimal = spnTipoAnimal.getSelectedItem().toString();
        tip = spnRaca.getSelectedItemPosition();*/

        cadIncluirAnimal.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //Rest Adapte
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(IAnimalApi.API_LOCATION)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                IAnimalApi service = retrofit.create(IAnimalApi.class);

                    AnimalApi animal = new AnimalApi();
                    animal.setNomeAnimal(cadNomeAnimal.getText().toString());
                    animal.setDescricao(cadDescricaoAnimal.getText().toString());
                    animal.setIdade(Integer.parseInt(cadIdadeAnimal.getText().toString()));
                    animal.setRaca(cadRacaAnimal.getText().toString());
                    animal.setIdPessoa(pessoaController.getPessoaLogada().getIdPessoa());
                    animal.setContato(pessoaController.getPessoaLogada().getContato());

                    Call<AnimalApi> animalPost = service.postAnimal(animal);
                    animalPost.enqueue(new Callback<AnimalApi>() {
                        @Override
                        public void onResponse(Call<AnimalApi> call, Response<AnimalApi> response) {
                            int statusCode = response.code();

                            AnimalApi animalapi = response.body();
                            Log.d("Salvar animal", "animal salvo" + statusCode);

                            Intent in = new Intent();
                            setResult(1,in);//Here I am Setting the Requestcode 1, you can put according to your requirement
                            finish();
                        }

                        @Override
                        public void onFailure(Call<AnimalApi> call, Throwable t) {
                            Log.d("Salvar animal", "Erro"+ t.getMessage());
                        }
                    });

            }
        });
    }

    public boolean verificarCampos(){
        int resultado = 0; // resultado os valores são 1 para cadNomeAnimal, 2 para cadIdadeAnimal, e 5 para cadDescricaoAnimal
        if(cadNomeAnimal.getText().toString().equals("") || cadNomeAnimal.getText() == null){
            resultado +=1;
        }
        if(cadIdadeAnimal.getText().toString().equals("") || cadNomeAnimal.getText() == null){
            resultado += 2;
        }
        if(cadDescricaoAnimal.getText().toString().equals("") || cadDescricaoAnimal.getText() == null){
            resultado +=5;
        }

        if(resultado > 0){
            destacarCampos(resultado);
            return false;
        }
        return true;
    }

    public void destacarCampos(int valor){
        cadDescricaoAnimal.getBackground().setColorFilter(Color.parseColor("#DDDDDD"), PorterDuff.Mode.LIGHTEN);
        cadIdadeAnimal.getBackground().setColorFilter(Color.parseColor("#DDDDDD"), PorterDuff.Mode.LIGHTEN);
        cadNomeAnimal.getBackground().setColorFilter(Color.parseColor("#DDDDDD"), PorterDuff.Mode.LIGHTEN);
        if (valor >= 5){
            cadDescricaoAnimal.getBackground().setColorFilter(Color.parseColor("#FF0000"), PorterDuff.Mode.LIGHTEN);
            valor -= 5;
        }
        if (valor >= 2){
            cadIdadeAnimal.getBackground().setColorFilter(Color.parseColor("#FF0000"), PorterDuff.Mode.LIGHTEN);
            valor -= 2;
        }
        if(valor >= 1){
            cadNomeAnimal.getBackground().setColorFilter(Color.parseColor("#FF0000"), PorterDuff.Mode.LIGHTEN);
            valor -= 1;
        }
    }
}
