package com.example.denesleal.precificacao;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class CalculoDespesas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculo_despesas);

      /*  Intent intent2 = getIntent();
        Bundle pacote = intent2.getExtras();
        float precoMP = pacote.getFloat("preco");*/
    }
    public float precoMP = Matrizes.precoMP;

    public void Calculo(View view){
        EditText aluguel = (EditText)findViewById(R.id.edtAluguel);
            Float valoraluguel = Float.valueOf(aluguel.getText().toString());

        EditText Energia = (EditText)findViewById(R.id.edtEnergia);
            Float valorenergia = Float.valueOf(Energia.getText().toString());

        EditText Combustivel = (EditText)findViewById(R.id.edtCombustivel);
            Float valorcombustivel = Float.valueOf(Combustivel.getText().toString());


        EditText Agua = (EditText)findViewById(R.id.edtAgua);
            Float valoragua = Float.valueOf(Agua.getText().toString());

        EditText Tempo = (EditText)findViewById(R.id.edtTempo);
            Float valortempo = Float.valueOf(Tempo.getText().toString());

        EditText Lucro = (EditText)findViewById(R.id.edtLucro);
            Float valorlucro = Float.valueOf(Lucro.getText().toString());



        float despesa = 0;

        despesa = (valoraluguel + valoragua + valorenergia + valorcombustivel)*(valortempo/160);

        float horatrabalhada = (valortempo*valorlucro)/160;
        float margem = despesa + precoMP;
        float preco = margem + horatrabalhada;
        Toast.makeText(this, "A margem é: "+margem+" e o preço é: "+preco, Toast.LENGTH_LONG).show();
    }


}
