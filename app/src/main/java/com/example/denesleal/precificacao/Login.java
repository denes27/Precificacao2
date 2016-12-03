package com.example.denesleal.precificacao;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import static com.example.denesleal.precificacao.Main_Activity.cadastroinicial;
import static com.example.denesleal.precificacao.Main_Activity.logado;


public class Login extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("LALALA", "CRIOU LOGIN");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
    //Variaveis usadas no login
    public ArrayList<String> Nomes = new ArrayList<String>();
    public ArrayList<String> Emails = new ArrayList<String>();
    public ArrayList<String> Senhas = new ArrayList<String>();
    public ArrayList<String> Keys = new ArrayList<String>();

    public int nUsuarios = 0;

    public void Cadastro(View view){

        EditText edtNewNome = (EditText)findViewById(R.id.edtNewNome);
        EditText edtNewEmail = (EditText)findViewById(R.id.edtNewEmail);
        EditText edtNewSenha = (EditText)findViewById(R.id.edtNewSenha);
        EditText edtConfirmaSenha = (EditText)findViewById(R.id.edtConfirmaSenha);

        String nome = edtNewNome.getText().toString();
        String email = edtNewEmail.getText().toString();
        String senha = edtNewSenha.getText().toString();
        String confirmasenha = edtConfirmaSenha.getText().toString();

        if      (nome.length()          != 0 &&
                email.length()          != 0 &&
                senha.length()          != 0 &&
                confirmasenha.length()  != 0 &&
                senha.equals(confirmasenha)){


            Nomes.add(nUsuarios , edtNewNome.getText().toString());
            Emails.add(nUsuarios , edtNewEmail.getText().toString());
            Senhas.add(nUsuarios , edtNewSenha.getText().toString());

            //Teria que jogar esses valores novos pro banco de dados também
            Toast.makeText(this, "Novo usuário registrado", Toast.LENGTH_SHORT).show();
            Keys.add(Nomes.get(nUsuarios));

            nUsuarios = Nomes.size() + 1;

        } else Toast.makeText(this, "Houve algum erro, verifique se há algum campo vazio " +
                "ou se a senha informada para confirmação está correta", Toast.LENGTH_LONG).show();

    }

    public void fazerLogin(View view){

        EditText login = (EditText)findViewById(R.id.edtLogin);
        EditText senha = (EditText)findViewById(R.id.edtSenha);

        boolean UsuarioExiste = false;
        int UsuarioAtual = 0;
        //Verificar se o login existe na lista
        for(int m = 0; m < Emails.size(); m++  ){
            if(Emails.get(m).equals(login.getText().toString())){
                UsuarioExiste = true;
                UsuarioAtual = m;
                finish();
            }
            //se existe, ver se a senha bate, se a senha bate, entre no app
        if(UsuarioExiste == true){

            if(Senhas.get(UsuarioAtual).equals(senha.getText().toString())){

                // logado e cadastro true, para voltar para ir para não voltar a tela de login quando iniciar a outra activity
                logado = true;
                cadastroinicial = true;
                Intent intent = new Intent(this, Main_Activity.class);
                startActivity(intent);

            }else Toast.makeText(this, "Senha incorreta", Toast.LENGTH_SHORT).show();


        }else Toast.makeText(this, "Usuário não existe", Toast.LENGTH_SHORT).show();

        }



    }
}



