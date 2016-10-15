package com.example.italo.adoteumpet.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

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

    private String textoRaca;
    private int ra;
    private String textoTipoAnimal;
    private int tip;


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

        editModificarAnimal.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                String nome = editNomeAnimal.getText().toString();
                String descricao = editDescricaoAnimal.getText().toString();
                int idade;
                idade = Integer.parseInt(editIdadeAnimal.getText().toString());
                //Modificar depois que modificar o xml
                Animal animal = new Animal(nome,idade,descricao, "Teste");


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
