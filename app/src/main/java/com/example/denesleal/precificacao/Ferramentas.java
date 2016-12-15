package com.example.denesleal.precificacao;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class Ferramentas extends AppCompatActivity {
    private static final String SH_NAME="MySharedPrefs";
    private static final String USERNAME="NomedoUsuario";
    private static final String USEREMAIL="EmaildoUsuario";
    public static boolean logado;
    public static boolean cadastroinicial;
    public static String nomeUsuario;
    public static String emailUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ferramentas);

        //Declara shared preference
        SharedPreferences sharedPref = getSharedPreferences(SH_NAME, 0);
        SharedPreferences.Editor editor = sharedPref.edit();

        if(cadastroinicial == false) { //Condição pra saber se já foi feito o cadastro inicial
            logado = sharedPref.getBoolean(SH_NAME, false);
            nomeUsuario = sharedPref.getString(USERNAME,null);
            emailUsuario = sharedPref.getString(USEREMAIL,null);
            Log.d("if cadastro", "LOGADO = "+logado);

        }
        Log.d(SH_NAME, "CRIOU A SHARED PREFERENCE");

        //Se fez login uma vez e tem shared preference, não vai pra tela de login
        if (logado) {
            editor.putBoolean(SH_NAME, logado);
            editor.putString(USERNAME, nomeUsuario);
            editor.putString(USEREMAIL,emailUsuario);
            editor.commit();
            setContentView(R.layout.activity_ferramentas);
        } else {
            Log.d(SH_NAME, "ENTROU NO ELSE");
            Intent intent = new Intent(this, Login.class);
            Log.d(SH_NAME, "CRIOU INTENT");
            startActivity(intent);
            Log.d(SH_NAME, "STARTOU INTENT");
        }
    }

    public void cadastrarProduto (View view){
        Intent intent = new Intent(this,Main_Activity.class);
        startActivity(intent);

    }

    public void novoOrcamento (View view){
        Intent intent = new Intent(this,FazerOrcamento.class);
        startActivity(intent);

    }
}
