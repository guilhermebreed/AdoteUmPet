package com.example.italo.adoteumpet.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by guilh on 02/11/2016.
 */

public class PessoaApi {

    @Expose
    @SerializedName("_id")
    private String idPessoa;

    @Expose
    @SerializedName("nomePessoa")
    private String nomePessoa;

    @Expose
    @SerializedName("endereco")
    private String endereco;

    @Expose
    @SerializedName("contato")
    private String contato;

    @Expose
    @SerializedName("usuario")
    private String usuario;

    @Expose
    @SerializedName("senha")
    private String senha;

    public PessoaApi() {
    }

    public PessoaApi(String idPessoa, String nomePessoa, String endereco, String contato, String usuario, String senha) {
        this.idPessoa = idPessoa;
        this.nomePessoa = nomePessoa;
        this.endereco = endereco;
        this.contato = contato;
        this.usuario = usuario;
        this.senha = senha;
    }

    public String getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(String idPessoa) {
        this.idPessoa = idPessoa;
    }

    public String getNomePessoa() {
        return nomePessoa;
    }

    public void setNomePessoa(String nomePessoa) {
        this.nomePessoa = nomePessoa;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
