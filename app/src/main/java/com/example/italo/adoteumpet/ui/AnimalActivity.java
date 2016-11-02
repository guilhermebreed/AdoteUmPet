package com.example.italo.adoteumpet.ui;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.italo.adoteumpet.R;
import com.example.italo.adoteumpet.data.model.Animal;

/**
 * Created by Italo on 06/10/2016.
 */

public class AnimalActivity extends AppCompatActivity{

    private EditText cadNomeAnimal;
    private EditText cadDescricaoAnimal;
    private EditText cadIdadeAnimal;
    private Button cadIncluirAnimal;
    private ControladorAnimal controladorAnimal = new ControladorAnimal();
    private String textoRaca;
    private int ra;
    private String textoTipoAnimal;
    private int tip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.animal_cadastro);

        cadNomeAnimal = (EditText) findViewById(R.id.cad_nome_animal);
        cadDescricaoAnimal = (EditText) findViewById(R.id.cad_descricao_animal);
        cadIdadeAnimal = (EditText) findViewById(R.id.cad_idade_animal);
        cadIncluirAnimal = (Button) findViewById(R.id.cad_btnIncluir);

        //Spinner Raça
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
        tip = spnRaca.getSelectedItemPosition();

        cadIncluirAnimal.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(verificarCampos()) {
                    String nome = cadNomeAnimal.getText().toString();
                    String descricao = cadDescricaoAnimal.getText().toString();
                    int idade;
                    try {
                        idade = Integer.parseInt(cadIdadeAnimal.getText().toString());
                        controladorAnimal.animais.add(new Animal(nome, idade, descricao, "Disponível"));

                        Intent in = new Intent();
                        setResult(1, in);//Here I am Setting the Requestcode 1, you can put according to your requirement
                        finish();
                    }catch(Exception ex){
                        Toast.makeText(AnimalActivity.this, "Campo Idade Aceita Somente numeros!", Toast.LENGTH_SHORT).show();
                        destacarCampos(2);
                    }
                }else{
                    Toast.makeText(AnimalActivity.this, "Verifique os campos destacados, queremos o melhor para seu animal!", Toast.LENGTH_SHORT).show();
                }
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
