package com.example.italo.adoteumpet.ui.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.italo.adoteumpet.R;
import com.example.italo.adoteumpet.ui.controller.PessoaController;

public class LoginAcitivity extends AppCompatActivity {

    private EditText txtUsuario;
    private EditText txtSenha;

    private TextView lbEsqueciSenha;
    private TextView lbCadastrar;

    private Button btnLogin;
    private Button btnCancelar;

    private int qtdCliquesCancelar = 0;

    private PessoaController pessoaController = new PessoaController();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtUsuario = (EditText) findViewById(R.id.login_user);
        txtSenha = (EditText) findViewById(R.id.login_password);

        lbEsqueciSenha = (TextView) findViewById(R.id.login_retrievePassword);
        lbCadastrar = (TextView) findViewById(R.id.login_cadastro);

        btnLogin = (Button) findViewById(R.id.login_btnLogin);
        btnCancelar = (Button) findViewById(R.id.login_btnCancelar);

        lbCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(LoginAcitivity.this, PessoaCadastroEditarActivity.class), 1);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usuario = txtUsuario.getText().toString();
                String senha = txtSenha.getText().toString();
                Log.w("Usuario Logado", "Passei aqui");

                if(pessoaController.realizarLogin(usuario,senha)){
                    Log.d("Usuario Logado", "Login Sucess"+usuario);
                    Intent in = new Intent();
                    setResult(5,in);//Here I am Setting the Requestcode 1, you can put according to your requirement
                    finish();
                }else{
                    Log.e("Usuario Logado", "Usuario ou senha invalidos");
                }
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent();
                setResult(6,in);//Here I am Setting the Requestcode 1, you can put according to your requirement
                finish();
            }
        });
    }

    @Override
    public void onBackPressed(){
        qtdCliquesCancelar++;
        Toast.makeText(LoginAcitivity.this,"Se deseja sair prescione mais uma vez",Toast.LENGTH_SHORT);
        if(qtdCliquesCancelar >= 2){
            Intent in = new Intent();
            setResult(6,in);//Here I am Setting the Requestcode 1, you can put according to your requirement
            finish();
        }else{
            Toast.makeText(LoginAcitivity.this,"Se deseja sair prescione mais uma vez",Toast.LENGTH_SHORT);
        }
    }

    @Override
    protected void onActivityResult(int codigo, int resultado, Intent intent){
        if(resultado == 1){
            Toast.makeText(this,"Cadastrador com Sucesso!!", Toast.LENGTH_SHORT).show();
        }else if(resultado == 2){

        }
    }
}
