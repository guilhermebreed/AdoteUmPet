package com.example.italo.adoteumpet.data.model;

import com.google.gson.annotations.*;

/**
 * Created by guilh on 02/11/2016.
 */

public class AnimalApi {
    @Expose
    private String nomeAnimal;

    @Expose
    private String raca;

    @Expose
    private Integer idade;

    @Expose
    private String descricao;

    public String getNomeAnimal() {
        return nomeAnimal;
    }

    public void setNomeAnimal(String nomeAnimal) {
        this.nomeAnimal = nomeAnimal;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
