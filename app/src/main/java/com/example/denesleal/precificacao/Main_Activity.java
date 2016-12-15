package com.example.denesleal.precificacao;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Main_Activity extends Activity {
    private static final String SH_NAME="MySharedPrefs";
    public static boolean logado;
    public static boolean cadastroinicial;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_);

        //Declara shared preference
        SharedPreferences sharedPref = getSharedPreferences(SH_NAME, 0);
        SharedPreferences.Editor editor = sharedPref.edit();

       if(cadastroinicial == false) { //Condição pra saber se já foi feito o cadastro inicial
           logado = sharedPref.getBoolean(SH_NAME, false);
           Log.d("if cadastro", "LOGADO = "+logado);
       }
           Log.d(SH_NAME, "CRIOU A SHARED PREFERENCE");

           //Se fez login uma vez e tem shared preference, não vai pra tela de login
           if (logado) {
               editor.putBoolean(SH_NAME, logado);
               editor.commit();
               setContentView(R.layout.activity_main_);
           } else {
               Log.d(SH_NAME, "ENTROU NO ELSE");
               Intent intent = new Intent(this, Login.class);
               Log.d(SH_NAME, "CRIOU INTENT");
               startActivity(intent);
               Log.d(SH_NAME, "STARTOU INTENT");
           }

    }

    public void startServico(View view) {
        //setContentView(R.layout.matrizes); //Vou usar uma nova activity ao invés de uma outra view
        Intent intent = new Intent(this,Matrizes.class);
        startActivity(intent);
    }

    public void semServico(View view) {
        setContentView(R.layout.precodireto);
    }

    public void AtualizarBD(View view) {

        //Só colocando o texto inserido em uma variável:
        EditText edtProduto = (EditText) findViewById(R.id.edtProduto);
        EditText edtPreco = (EditText) findViewById(R.id.edtPreco);
        //Posteriormente tem que dar um jeito de mandar isso pro banco de dados;
        //Enquanto isso, só ver de mostrar uma msg dizendo que o BD foi atualizado
        // EDIT: Mais uns IFs pro usuário ñ por dado errado;

        String produto = String.valueOf(edtProduto.getText().toString());
        if (produto.length() == 0) {
            Toast.makeText(this, "Digite algo no lugar de -Produto-", Toast.LENGTH_SHORT).show();
        } else {
            if(edtPreco.length() == 0){
                Toast.makeText(this, "Digite um valor para o preço", Toast.LENGTH_SHORT).show();
            }else {

                float preco = Float.valueOf(edtPreco.getText().toString());

                if (edtPreco.length() == 0) {
                    Toast.makeText(this, "Digite um preço valido", Toast.LENGTH_SHORT).show();
                } else {
                    if (preco < 0) {
                        Toast.makeText(this, "Digite um preço valido", Toast.LENGTH_SHORT).show();
                    } else {

                        Toast.makeText(this, "O banco de dados foi atualizado com um novo Produto", Toast.LENGTH_LONG).show();
                        setContentView(R.layout.activity_main_);
                    }
                }
            }
        }
    }
    }
