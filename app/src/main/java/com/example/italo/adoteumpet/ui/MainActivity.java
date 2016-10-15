package com.example.italo.adoteumpet.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.italo.adoteumpet.R;
import com.example.italo.adoteumpet.data.Animal;
import com.example.italo.adoteumpet.ui.adapter.AnimalAdapter;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private Button newButton;
    private ListView animaisList;

    private ControladorAnimal controladorAnimal;
    private AnimalAdapter adapter;
    private int requestCode;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newButton = (Button) findViewById(R.id.new_button);
        animaisList = (ListView) findViewById(R.id.animais_list);

        controladorAnimal = new ControladorAnimal();
        adapter = new AnimalAdapter(this,controladorAnimal.getAnimais());
        animaisList.setAdapter(adapter);
        animaisList.setOnItemClickListener(this);
        newButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        startActivityForResult(new Intent(this, AnimalActivity.class), 1);
    }

    @Override
    protected void onActivityResult(int codigo, int resultado, Intent intent){
        if(resultado == 1){
            Toast.makeText(this,"Cadastrador com Sucesso!!", Toast.LENGTH_SHORT).show();
            adapter.notifyDataSetChanged();
        }else if(resultado == 2) {
            Toast.makeText(this, "Modificado com Sucesso!!", Toast.LENGTH_SHORT).show();
            adapter.notifyDataSetChanged();
        }else if(resultado == 3){
            Toast.makeText(this, "Excluido com Sucesso!!", Toast.LENGTH_SHORT).show();
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id){

        Bundle extras = new Bundle();
        extras.putInt("modificar_tudo",position);
        Intent it = new Intent(this, AnimalEditarActivity.class);
        it.putExtras(extras);
        startActivityForResult(it, 2);

    }
}
