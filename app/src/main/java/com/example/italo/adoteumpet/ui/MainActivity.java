package com.example.italo.adoteumpet.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.italo.adoteumpet.R;
import com.example.italo.adoteumpet.ui.adapter.AnimalAdapter;
import com.example.italo.adoteumpet.data.model.AnimalApi;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private Button newButton;
    private ListView animaisList;
    private TextView tv;
    private AnimalApi animal;

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

    }

    @Override
    public void onClick(View v){
        //startActivityForResult(new Intent(this, AnimalActivity.class), 1);

        String API = "http://192.168.1.3:3000/api";

        RestAdapter restAdapter = new RestAdapter.Builder().setLogLevel(RestAdapter.LogLevel.FULL).setEndpoint(API).build();

        IAnimalApi api = restAdapter.create(IAnimalApi.class);

        api.getFeed("animal", new Callback<IAnimalApi>() {
            @Override
            public void success(IAnimalApi iAnimalApi, Response response) {
                tv.setText("Animal" +animal.getNomeAnimal()+ "Raça: "
                        +animal.getRaca()+ "Idade: " +animal.getIdade()+ "Descricao: " +animal.getDescricao());
            }

            @Override
            public void failure(RetrofitError error) {
                tv.setText("Não salvou hahahaha pq:  "+error.getMessage());
            }
        });
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
        extras.putInt("idModificar",position);
        Intent it = new Intent(this, AnimalEditarActivity.class);
        it.putExtras(extras);
        startActivityForResult(it, 2);

    }
}
