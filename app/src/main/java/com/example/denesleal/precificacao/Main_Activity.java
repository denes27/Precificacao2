package com.example.denesleal.precificacao;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import static com.example.denesleal.precificacao.R.id.home;

public class Main_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_);
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
        if (produto.equals("Produto")) {
            Toast.makeText(this, "Digite algo no lugar de -Produto-", Toast.LENGTH_SHORT).show();
        } else {

            float preco = Float.valueOf(edtPreco.getText().toString());

            if (preco < 0) {
                Toast.makeText(this, "Digite um preço valido", Toast.LENGTH_SHORT).show();
            } else {

                Toast.makeText(this, "O banco de dados foi atualizado com um novo Produto", Toast.LENGTH_LONG).show();
                setContentView(R.layout.activity_main_);
            }
        }
    }
    }
