package com.example.italo.adoteumpet.data;

/**
 * Created by Italo on 05/10/2016.
 */

public class Animal {
    private String nome;
    private String raca;
    private Integer idade;
    private String descricao;

    public Animal(String nome, Integer idade, String descricao) {
        this.nome = nome;
        this.idade = idade;
        this.descricao = descricao;
    }

    public Animal(){

    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    public void modificarAnimal(Animal animal){
        this.nome = animal.getNome();
        this.idade = animal.getIdade();
        this.descricao = animal.getDescricao();
    }
}
