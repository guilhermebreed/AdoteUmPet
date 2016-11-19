package com.example.italo.adoteumpet.ui;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.italo.adoteumpet.R;
import com.example.italo.adoteumpet.data.model.Animal;
import com.example.italo.adoteumpet.data.model.AnimalApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AnimalEditarActivity extends AppCompatActivity {
    private EditText editNomeAnimal;
    private EditText editDescricaoAnimal;
    private EditText editIdadeAnimal;

    private Button editModificarAnimal;
    private Button editExcluirAnimal;
    private Button editLigar;

    private ControladorAnimal controladorAnimal = new ControladorAnimal();
    private AnimalApi animal = new AnimalApi();
    private Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal_editar);

        extras  = getIntent().getExtras();
        int i = extras.getInt("idModificar");
        animal.modificarAnimal(controladorAnimal.animais.get(i));

        editNomeAnimal = (EditText) findViewById(R.id.edit_nome_animal);
        editDescricaoAnimal = (EditText) findViewById(R.id.edit_descricao_animal);
        editIdadeAnimal = (EditText) findViewById(R.id.edit_idade_animal);

        editNomeAnimal.setText(animal.getNomeAnimal());
        editIdadeAnimal.setText(animal.getIdade().toString());
        editDescricaoAnimal.setText(animal.getDescricao());

        editModificarAnimal = (Button) findViewById(R.id.edit_btnModificar);
        editExcluirAnimal = (Button) findViewById(R.id.edit_btnExcluir);
        editLigar = (Button) findViewById(R.id.edit_btnLigar);
        //chama a função pra ver oque a pessoa vai poder enchergar (não sei portugues... Visivelmente percebe-se kkk)
        verificarCampos();

        editModificarAnimal.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                String nome = editNomeAnimal.getText().toString();
                String descricao = editDescricaoAnimal.getText().toString();
                int idade;
                idade = Integer.parseInt(editIdadeAnimal.getText().toString());
                AnimalApi animal = new AnimalApi(nome,idade,descricao);


                controladorAnimal.animais.get(extras.getInt("idModificar")).modificarAnimal(animal);

                Intent in = new Intent();
                setResult(2,in);//Here I am Setting the Requestcode 1, you can put according to your requirement
                finish();
            }
        });

        editExcluirAnimal.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                AnimalApi animalExcluir = controladorAnimal.animais.get(extras.getInt("idModificar"));
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(IAnimalApi.API_LOCATION)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                IAnimalApi service = retrofit.create(IAnimalApi.class);

                Call<AnimalApi> excluir = service.deleteAnimal(animalExcluir.getId());

                excluir.enqueue(new Callback<AnimalApi>() {
                    @Override
                    public void onResponse(Call<AnimalApi> call, Response<AnimalApi> response) {
                        Intent in = new Intent();
                        setResult(3,in);//Here I am Setting the Requestcode 1, you can put according to your requirement
                        finish();
                    }

                    @Override
                    public void onFailure(Call<AnimalApi> call, Throwable t) {
                        Intent in = new Intent();
                        setResult(3,in);//Here I am Setting the Requestcode 1, you can put according to your requirement
                        finish();
                    }
                });
                
                Intent in = new Intent();
                setResult(3,in);//Here I am Setting the Requestcode 1, you can put according to your requirement
                finish();
            }
        });

        editLigar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{

                    //Tipos de chamadas
                        //ACTION_DIAL
                        // ACTION_CALL

                    Intent chamada = new Intent(Intent.ACTION_DIAL);

                    //Pegar o telefone da pessoa que vai estar dentro de animal
                    String numeroTelefone = "034998064079";
                    chamada.setData(Uri.parse("tel:"+numeroTelefone));

                    startActivity(chamada);

                }catch(ActivityNotFoundException act){
                    Log.e("Exemplo de chamada", "falha", act);

                }catch(Exception ex){
                    Log.e("Erro Na chamada","Erro: "+ex.getMessage());
                }
            }
        });
    }
    private void verificarCampos(){
        //Pega o animal da vez que está sendo editado.
        AnimalApi animal = controladorAnimal.animais.get(extras.getInt("idModificar"));
        //Pega o Id de quem ta logado no app e verifica se é o mesmo de quem cadastrou o animal.
        if(false) { // Caso for o mesmo cai no TRUE, senão vai pro else
            editModificarAnimal.setVisibility(View.VISIBLE);
            editExcluirAnimal.setVisibility(View.VISIBLE);
            editLigar.setVisibility(View.INVISIBLE);
        }else{
            editModificarAnimal.setVisibility(View.INVISIBLE);
            editExcluirAnimal.setVisibility(View.INVISIBLE);
            editLigar.setVisibility(View.VISIBLE);
        }
    }
}
