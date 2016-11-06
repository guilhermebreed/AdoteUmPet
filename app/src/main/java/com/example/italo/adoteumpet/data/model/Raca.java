package com.example.italo.adoteumpet.data.model;

/**
 * Created by Alexandre on 15/10/2016.
 */
public class Raca {

    private String nomeRaca;
    private TipoAnimal tipoAnimal;

    public Raca(String nomeRaca){
        this.nomeRaca = nomeRaca;
    }

    public String getNomeRaca() {
        return nomeRaca;
    }

    public void setNomeRaca(String nomeRaca) {
        this.nomeRaca = nomeRaca;
    }

    public TipoAnimal getTipoAnimal() {
        return tipoAnimal;
    }

    public void setTipoAnimal(TipoAnimal tipoAnimal) {
        this.tipoAnimal = tipoAnimal;
    }
}
