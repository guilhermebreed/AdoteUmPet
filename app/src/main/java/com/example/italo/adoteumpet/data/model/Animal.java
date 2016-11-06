package com.example.italo.adoteumpet.data.model;

import java.io.Serializable;

/**
 * Created by Italo on 05/10/2016.
 */

public class Animal{
    private int _id;
    private String nomeAnimal;
    private Raca raca;
    private Integer idade;
    private String descricao;
    private String status;

    public Animal(String nome, Integer idade, String descricao, String status) {
        this.nomeAnimal = nome;
        this.idade = idade;
        this.descricao = descricao;
        this.status = status;
    }

    public Animal() {

    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Raca getRaca() {
        return raca;
    }

    public void setRaca(Raca raca) {
        this.raca = raca;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getNomeAnimal() {
        return nomeAnimal;
    }

    public void setNomeAnimal(String nomeAnimal) {
        this.nomeAnimal = nomeAnimal;
    }

    public void modificarAnimal(Animal animal) {
        this.nomeAnimal = animal.getNomeAnimal();
        this.idade = animal.getIdade();
        this.descricao = animal.getDescricao();
        this.status = animal.getStatus();
    }
}
