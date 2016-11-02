package com.example.italo.adoteumpet.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.italo.adoteumpet.R;
import com.example.italo.adoteumpet.data.model.Animal;

import java.util.List;

/**
 * Created by Italo on 06/10/2016.
 */

public class AnimalAdapter extends ArrayAdapter<Animal>{
    private Context context;
    private List<Animal> animalList;

    public AnimalAdapter (Context context, List<Animal> animalList){
        super(context,0,animalList);
        this.context = context;
        this.animalList = animalList;
    }

    @Override
    public View getView (int position, View view, ViewGroup parent){
        Animal animal = animalList.get(position);

        if(view == null){
            view = LayoutInflater.from(context).inflate(R.layout.item, null);
        }

        TextView txtDesc = (TextView) view.findViewById(R.id.nome_pet);
        txtDesc.setText(animal.getNome());

        return view;
    }
}
