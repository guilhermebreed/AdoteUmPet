package com.example.italo.adoteumpet.ui;

import com.example.italo.adoteumpet.data.model.Animal;
import com.example.italo.adoteumpet.data.model.AnimalApi;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Italo on 06/10/2016.
 */

public class ControladorAnimal {
    public static List<AnimalApi> animais = new ArrayList<>();

    public static List<AnimalApi> getAnimais() {
        return animais;
    }


}
