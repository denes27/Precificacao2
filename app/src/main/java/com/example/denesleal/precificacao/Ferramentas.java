package com.example.denesleal.precificacao;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Ferramentas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ferramentas);
    }

    public void cadastrarProduto (View view){
        Intent intent = new Intent(this,Main_Activity.class);
        startActivity(intent);

    }
/*
    public void novoOrcamento (View view){


    }*/
}
