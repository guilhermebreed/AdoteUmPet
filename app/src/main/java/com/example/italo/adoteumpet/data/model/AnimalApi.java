package com.example.italo.adoteumpet.data.model;

import com.google.gson.annotations.*;

/**
 * Created by guilh on 02/11/2016.
 */

public class AnimalApi {

    @Expose
    @SerializedName("_id")
    private String id;

    @Expose
    @SerializedName("nomeAnimal")
    private String nomeAnimal;

    @Expose
    @SerializedName("raca")
    private String raca;

    @Expose
    @SerializedName("idade")
    private Integer idade;

    @Expose
    @SerializedName("descricao")
    private String descricao;

    public AnimalApi(String nomeAnimal, Integer idade, String descricao) {
        this.nomeAnimal = nomeAnimal;
        this.idade = idade;
        this.descricao = descricao;
    }

    public AnimalApi() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public void modificarAnimal(AnimalApi animal) {
        this.nomeAnimal = animal.getNomeAnimal();
        this.idade = animal.getIdade();
        this.descricao = animal.getDescricao();
    }
}
