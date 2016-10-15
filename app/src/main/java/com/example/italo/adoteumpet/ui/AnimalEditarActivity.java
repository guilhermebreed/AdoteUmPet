package com.example.italo.adoteumpet.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.italo.adoteumpet.R;
import com.example.italo.adoteumpet.data.Animal;

public class AnimalEditarActivity extends AppCompatActivity {
    private EditText editNomeAnimal;
    private EditText editDescricaoAnimal;
    private EditText editIdadeAnimal;

    private Button editModificarAnimal;
    private Button editExcluirAnimal;

    private ControladorAnimal controladorAnimal = new ControladorAnimal();
    private Animal animal = new Animal();
    private Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal_editar);

        extras  = getIntent().getExtras();
        int i = extras.getInt("modificar_tudo");
        animal.modificarAnimal(controladorAnimal.animais.get(i));

        editNomeAnimal = (EditText) findViewById(R.id.edit_nome_animal);
        editDescricaoAnimal = (EditText) findViewById(R.id.edit_descricao_animal);
        editIdadeAnimal = (EditText) findViewById(R.id.edit_idade_animal);

        editNomeAnimal.setText(animal.getNome());
        editIdadeAnimal.setText(animal.getIdade().toString());
        editDescricaoAnimal.setText(animal.getDescricao());

        editModificarAnimal = (Button) findViewById(R.id.edit_btnModificar);
        editExcluirAnimal = (Button) findViewById(R.id.edit_btnExcluir);

        editModificarAnimal.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                String nome = editNomeAnimal.getText().toString();
                String descricao = editDescricaoAnimal.getText().toString();
                int idade;
                idade = Integer.parseInt(editIdadeAnimal.getText().toString());
                Animal animal = new Animal(nome,idade,descricao, "Disponível");


                controladorAnimal.animais.get(extras.getInt("idModificar")).modificarAnimal(animal);

                Intent in = new Intent();
                setResult(2,in);//Here I am Setting the Requestcode 1, you can put according to your requirement
                finish();
            }
        });

        editExcluirAnimal.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                controladorAnimal.animais.remove(extras.getInt("idModificar"));
                Intent in = new Intent();
                setResult(3,in);//Here I am Setting the Requestcode 1, you can put according to your requirement
                finish();
            }
        });
    }
}
