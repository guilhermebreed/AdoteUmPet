package com.example.italo.adoteumpet.ui;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.italo.adoteumpet.R;
import com.example.italo.adoteumpet.data.Animal;
import com.example.italo.adoteumpet.ui.ControladorAnimal;

/**
 * Created by Italo on 06/10/2016.
 */

public class AnimalActivity extends AppCompatActivity{

    private EditText cadNomeAnimal;
    private EditText cadDescricaoAnimal;
    private EditText cadIdadeAnimal;
    private Button cadIncluirAnimal;
    private ControladorAnimal controladorAnimal = new ControladorAnimal();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.animal_cadastro);

        cadNomeAnimal = (EditText) findViewById(R.id.cad_nome_animal);
        cadDescricaoAnimal = (EditText) findViewById(R.id.cad_descricao_animal);
        cadIdadeAnimal = (EditText) findViewById(R.id.cad_idade_animal);

        cadIncluirAnimal = (Button) findViewById(R.id.cad_btnIncluir);

        cadIncluirAnimal.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(verificarCampos()) {
                    String nome = cadNomeAnimal.getText().toString();
                    String descricao = cadDescricaoAnimal.getText().toString();
                    int idade;
                    try {
                        idade = Integer.parseInt(cadIdadeAnimal.getText().toString());
                        controladorAnimal.animais.add(new Animal(nome, idade, descricao));

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
