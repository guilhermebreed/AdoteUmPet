package com.example.italo.adoteumpet.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.italo.adoteumpet.R;

//Comentário inútil
public class PessoaCadastroEditarActivity extends AppCompatActivity {

    private EditText nomePessoa;
    private  EditText endereco;
    private EditText numero;
    private EditText bairro;
    private EditText estado;
    private EditText cidade;
    private Button confirmar;
    private  Button cancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pessoa_cadastrar_editar);

        nomePessoa = (EditText) findViewById(R.id.pessoa_cadastrarEditar);
        endereco = (EditText) findViewById(R.id.cad_pessoa_endereco);
        numero = (EditText) findViewById(R.id.cad_pessoa_endereco_numero);
        bairro = (EditText) findViewById(R.id.cad_pessoa_bairro);
        estado = (EditText) findViewById(R.id.cad_pessoa_estado);
        cidade = (EditText) findViewById(R.id.cad_pessoa_cidade);

        confirmar = (Button) findViewById(R.id.btnCadastrar);
        cancelar = (Button) findViewById(R.id.btnCancelar);
    }


}
