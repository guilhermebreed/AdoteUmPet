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

    @Expose
    @SerializedName("idPessoa")
    private String idPessoa;

    @Expose
    @SerializedName("contato")
    private String contato;

    @Expose
    @SerializedName("foto")
    private String foto;

    public AnimalApi(String id, String nomeAnimal, String raca, Integer idade, String descricao, String idPessoa, String contato, String foto) {
        this.id = id;
        this.nomeAnimal = nomeAnimal;
        this.raca = raca;
        this.idade = idade;
        this.descricao = descricao;
        this.idPessoa = idPessoa;
        this.contato = contato;
        this.foto = foto;
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

    public String getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(String idPessoa) {
        this.idPessoa = idPessoa;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public void modificarAnimal(AnimalApi animal) {
        this.nomeAnimal = animal.getNomeAnimal();
        this.idade = animal.getIdade();
        this.descricao = animal.getDescricao();
        this.raca = animal.getRaca();
    }
}
