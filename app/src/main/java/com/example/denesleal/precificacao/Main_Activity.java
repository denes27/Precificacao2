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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_);


    }

    public void startServico(View view) {
        //setContentView(R.layout.matrizes); //Vou usar uma nova activity ao invés de uma outra view
        Intent intent = new Intent(this, Matrizes.class);
        startActivity(intent);
    }

    public void semServico(View view) {
        setContentView(R.layout.precodireto);
    }

    public void AtualizarBD(View view) {

        //Colocando o Banco de Dados
        MeuBD meuBD = new MeuBD(this, MeuBD.DB_NAME, null, MeuBD.DB_VERSION);

        ProdutosDAO produtosDAO = new ProdutosDAO(meuBD);


        //Só colocando o texto inserido em uma variável:
        EditText edtProduto = (EditText) findViewById(R.id.edtProduto);
        EditText edtPreco = (EditText) findViewById(R.id.edtPreco);

        //Posteriormente tem que dar um jeito de mandar isso pro banco de dados;
        //Enquanto isso, só ver de mostrar uma msg dizendo que o BD foi atualizado
        // EDIT: Mais uns IFs pro usuário ñ por dado errado;

        Produtos prod = new Produtos();
        String produto = String.valueOf(edtProduto.getText().toString());

        if (produto.length() == 0) {
            Toast.makeText(this, "Digite algo no lugar de -Produto-", Toast.LENGTH_SHORT).show();
        } else {
            if (edtPreco.length() == 0) {
                Toast.makeText(this, "Digite um valor para o preço", Toast.LENGTH_SHORT).show();
            } else {

                float preco = Float.valueOf(edtPreco.getText().toString());

                if (edtPreco.length() == 0) {
                    Toast.makeText(this, "Digite um preço valido", Toast.LENGTH_SHORT).show();
                } else {
                    if (preco < 0) {
                        Toast.makeText(this, "Digite um preço valido", Toast.LENGTH_SHORT).show();
                    } else {
                        prod.setNome(edtProduto.getText().toString());
                        prod.setPreco(Float.valueOf(edtPreco.getText().toString()));
                        produtosDAO.create(prod);
                        Toast.makeText(this, "O banco de dados foi atualizado com um novo Produto", Toast.LENGTH_LONG).show();
                        setContentView(R.layout.activity_main_);
                    }
                }
            }
        }
    }
}