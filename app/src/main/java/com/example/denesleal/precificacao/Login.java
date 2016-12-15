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

import static com.example.denesleal.precificacao.Ferramentas.cadastroinicial;
import static com.example.denesleal.precificacao.Ferramentas.emailUsuario;
import static com.example.denesleal.precificacao.Ferramentas.logado;
import static com.example.denesleal.precificacao.Ferramentas.nomeUsuario;

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

    public void Cadastro(View view){    }

    public void fazerLogin(View view){

        EditText nome = (EditText)findViewById(R.id.edtNome);
        EditText email = (EditText)findViewById(R.id.edtEmail);


        // logado e cadastro true, para não voltar a tela de login quando iniciar a outra activity
        logado = true;
        cadastroinicial = true;
        nomeUsuario = nome.getText().toString();
        emailUsuario = email.getText().toString();

        Intent intent = new Intent(this, Ferramentas.class);
        startActivity(intent);

        Toast.makeText(this, "Cadastro realizado", Toast.LENGTH_SHORT).show();

        }
        public void onDestroy(){
            super.onDestroy();
            if(!cadastroinicial){
                System.exit(0);
            }
        }


    }