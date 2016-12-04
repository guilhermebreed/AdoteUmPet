package com.example.italo.adoteumpet.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.italo.adoteumpet.R;
import com.example.italo.adoteumpet.data.model.AnimalApi;
import com.example.italo.adoteumpet.ui.controller.ControladorAnimal;
import com.example.italo.adoteumpet.ui.view.MainActivity;

import java.util.List;

/**
 * Created by Italo on 06/10/2016.
 */

public class AnimalAdapter extends ArrayAdapter<AnimalApi>{
    private Context context;
    private List<AnimalApi> animalList;

    public AnimalAdapter (Context context, List<AnimalApi> animalList){
        super(context,0,animalList);
        this.context = context;
        this.animalList = animalList;
    }

    @Override
    public View getView (int position, View view, ViewGroup parent){
        AnimalApi animal = animalList.get(position);

        if(view == null){
            view = LayoutInflater.from(context).inflate(R.layout.item, null);
        }

        TextView txtRaca = (TextView) view.findViewById(R.id.tv_racaPet);
        TextView txtIdade = (TextView) view.findViewById(R.id.tv_idadePet);
        TextView txtDesc = (TextView) view.findViewById(R.id.nome_pet);
        ImageView imgFoto = (ImageView) view.findViewById(R.id.imagem_pet);

        /*if(MainActivity.qualestou >= 2) {
            imgFoto.setImageResource(R.mipmap.dog1);
            MainActivity.qualestou = 0;
        }else{

            imgFoto.setImageResource(MainActivity.fotos[MainActivity.qualestou]);
            MainActivity.qualestou++;
        }
        */
        ControladorAnimal controladorAnimal = new ControladorAnimal();
        if(animal.getFoto() == null) {

        }else {
            imgFoto.setImageResource(controladorAnimal.converterImagemCerta(animal.getFoto()));
        }
        txtDesc.setText(" Nome: "+animal.getNomeAnimal());
        txtIdade.setText(" Idade: "+animal.getIdade());
        txtRaca.setText(" Raça: "+animal.getRaca());
        return view;
    }
}
