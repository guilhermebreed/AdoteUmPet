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
    private static List<AnimalApi> animaisListaCompleta = new ArrayList<>();
    public static List<AnimalApi> getAnimais() {
        return animais;
    }

    public static void setAnimais(List<AnimalApi> animais) {
        ControladorAnimal.animais.addAll(animais);
    }

    public static void setAnimaisListaCompleta(List<AnimalApi> animais){
        ControladorAnimal.animaisListaCompleta.addAll(animais);
    }

    public static void getAnimaisOrdenado(String pesquisa, int opcao){
        zerarLista();
        animais.addAll(animaisListaCompleta);
        List<AnimalApi> resultado = new ArrayList<>();
        if(opcao == 0){
            resultado.addAll(animais);
        }
        else if(opcao == 1) {
            for (int index = animais.size() - 1; index >= 0; index--) {
                AnimalApi corrente = animais.get(index);

                if (corrente.getRaca().toString().toLowerCase().contains(pesquisa.toLowerCase())) { //pesquisa.equalsIgnoreCase(corrente.getRaca().toString().trim())
                    resultado.add(corrente);
                }
            }
        }else if(opcao == 3){
            for (int index = animais.size() - 1; index >= 0; index--) {
                AnimalApi corrente = animais.get(index);
                if (Integer.parseInt(pesquisa) == corrente.getIdade()) {
                    resultado.add(corrente);
                }
            }
        }
        zerarLista();
        setAnimais(resultado);
    }
    //public static String getAnimaisRaca(AnimalApi raca){return raca.getRaca();}

    public static void zerarLista(){
        animais.removeAll(animais);
    }

    public static void zerarLista(int valor){
        animais.removeAll(animais);
        animaisListaCompleta.removeAll(animaisListaCompleta);
    }

    public static void semFiltro(){
        zerarLista();
    }


}
