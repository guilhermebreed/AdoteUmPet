package com.example.italo.adoteumpet.ui.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.italo.adoteumpet.R;
import com.example.italo.adoteumpet.data.model.PessoaApi;
import com.example.italo.adoteumpet.ui.controller.PessoaController;
import com.example.italo.adoteumpet.ui.interfaces.api.IPessoaApi;

import java.text.NumberFormat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//Comentário inútil
public class PessoaCadastroEditarActivity extends AppCompatActivity {

    private EditText txtNomePessoa;
    private EditText txtEndereco;
    private EditText txtNumero;
    private EditText txtBairro;
    private EditText txtEstado;
    private EditText txtCidade;
    private EditText txtContato;
    private EditText txtUsuario;
    private EditText txtSenha;

    private Button btnConfirmar;
    private  Button btnCancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pessoa_cadastrar_editar);

        txtNomePessoa = (EditText) findViewById(R.id.pessoa_cadastrarEditar);
        txtEndereco = (EditText) findViewById(R.id.cad_pessoa_endereco);
        txtNumero = (EditText) findViewById(R.id.cad_pessoa_endereco_numero);
        txtBairro = (EditText) findViewById(R.id.cad_pessoa_bairro);
        txtEstado = (EditText) findViewById(R.id.cad_pessoa_estado);
        txtCidade = (EditText) findViewById(R.id.cad_pessoa_cidade);
        txtContato = (EditText) findViewById(R.id.cad_pessoa_contato);
        txtUsuario = (EditText) findViewById(R.id.cad_pessoa_usuario);
        txtSenha = (EditText) findViewById(R.id.cad_pessoa_senha);

        btnConfirmar = (Button) findViewById(R.id.btnCadastrar);
        btnCancelar = (Button) findViewById(R.id.btnCancelar);

        btnConfirmar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //Rest Adapte
                if(montarEndereco() != null && verificarTelefone()) {
                    /*Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(IPessoaApi.API_LOCATION)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    IPessoaApi service = retrofit.create(IPessoaApi.class);
                    */
                    PessoaApi pessoa = new PessoaApi();
                    pessoa.setNomePessoa(txtNomePessoa.getText().toString());
                    pessoa.setEndereco(montarEndereco());
                    pessoa.setContato(txtContato.getText().toString());
                    pessoa.setUsuario(txtUsuario.getText().toString());
                    pessoa.setSenha(txtSenha.getText().toString());

                    if(PessoaController.salvarPessoa(pessoa)){
                        Intent in = new Intent();
                        setResult(1, in);//Here I am Setting the Requestcode 1, you can put according to your requirement
                        finish();
                    }else{
                        Toast.makeText(PessoaCadastroEditarActivity.this,"Falha no cadastro.",Toast.LENGTH_SHORT);
                    }
                    /*Call<PessoaApi> pessoaPost = service.postPessoa(pessoa);
                    pessoaPost.enqueue(new Callback<PessoaApi>() {
                        @Override
                        public void onResponse(Call<PessoaApi> call, Response<PessoaApi> response) {
                            int statusCode = response.code();

                            PessoaApi pessoaApi = response.body();
                            Log.d("Salvar Pessoa", "pessoa salvo" + statusCode);

                            Intent in = new Intent();
                            setResult(1, in);//Here I am Setting the Requestcode 1, you can put according to your requirement
                            finish();
                        }

                        @Override
                        public void onFailure(Call<PessoaApi> call, Throwable t) {
                            Log.d("Salvar Pessoa", "Erro: " + t.getMessage());
                        }
                    });*/

                }else{
                }
            }
        });
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent();
                setResult(2, in);//Here I am Setting the Requestcode 1, you can put according to your requirement
                finish();
            }
        });
    }

    private String montarEndereco(){
        String enderecoMontado = null;

        try {
            enderecoMontado = txtEndereco.getText().toString() + ", " + txtNumero.getText().toString();
        }catch (NumberFormatException nbex){
            Toast.makeText(this,"Digite um valor numerico no campo Numero",Toast.LENGTH_SHORT);
        }catch (Exception ex){
            Toast.makeText(this,"Erro: "+ex.getMessage(),Toast.LENGTH_SHORT);
        }
        return enderecoMontado;
    }

    private boolean verificarTelefone(){
        try{
            Long.valueOf(txtContato.getText().toString().trim());
        }catch(NumberFormatException nbex){
            Toast.makeText(this,"Em contato digite apenas numeros sem espaços",Toast.LENGTH_SHORT);
            return false;
        }catch(Exception ex){
            Toast.makeText(this,"Erro: "+ex.getMessage(),Toast.LENGTH_SHORT);
            return false;
        }
        return true;
    }
}
