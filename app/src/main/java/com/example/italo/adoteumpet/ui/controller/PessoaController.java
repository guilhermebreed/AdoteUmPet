package com.example.italo.adoteumpet.ui.controller;

import com.example.italo.adoteumpet.data.model.PessoaApi;

/**
 * Created by Italo on 20/11/2016.
 */

public class PessoaController {
    private static PessoaApi pessoaLogada;

    public PessoaController() {
    }

    public static PessoaApi getPessoaLogada() {
        return pessoaLogada;
    }

    public static void setPessoaLogada(PessoaApi pessoaLogada) {
        PessoaController.pessoaLogada = pessoaLogada;
    }

}
