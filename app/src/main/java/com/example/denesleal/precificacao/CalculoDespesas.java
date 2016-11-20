package com.example.denesleal.precificacao;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
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

        Float valoraluguel = Float.valueOf(0);
        Float valorenergia = Float.valueOf(0);
        Float valorcombustivel = Float.valueOf(0);
        Float valoragua = Float.valueOf(0);
        Float valortempo = Float.valueOf(0);
        Float valorlucro = Float.valueOf(0);

        EditText aluguel = (EditText)findViewById(R.id.edtAluguel);
        EditText Energia = (EditText)findViewById(R.id.edtEnergia);
        EditText Combustivel = (EditText)findViewById(R.id.edtCombustivel);
        EditText Agua = (EditText)findViewById(R.id.edtAgua);
        EditText Tempo = (EditText)findViewById(R.id.edtTempo);
        EditText Lucro = (EditText)findViewById(R.id.edtLucro);

        if     (aluguel.length() == 0 ||
                Energia.length() == 0 ||
                Combustivel.length() == 0 ||
                Agua.length() == 0 ||
                Tempo.length() == 0 ||
                Lucro.length() == 0){

            Toast.makeText(this, "Preencha todos os campos.", Toast.LENGTH_SHORT).show();


        }else {
            valoraluguel = Float.valueOf(aluguel.getText().toString());
            valorenergia = Float.valueOf(Energia.getText().toString());
            valorcombustivel = Float.valueOf(Combustivel.getText().toString());
            valoragua = Float.valueOf(Agua.getText().toString());
            valortempo = Float.valueOf(Tempo.getText().toString());
            valorlucro = Float.valueOf(Lucro.getText().toString());

            float despesa = 0;

            despesa = (valoraluguel + valoragua + valorenergia + valorcombustivel)*(valortempo/160);

            float horatrabalhada = (valortempo*valorlucro)/160;
            float margem = despesa + precoMP;
            float preco = margem + horatrabalhada;
            Toast.makeText(this, "A margem é: "+margem+" e o preço é: "+preco, Toast.LENGTH_LONG).show();

            setContentView(R.layout.fimdoprocesso);

            String txtm = "R$ "+Float.toString(margem);
            String txtp = "R$ "+Float.toString(preco);
            String txtp2 = Float.toString(preco);
            TextView txtmargem = (TextView)findViewById(R.id.txtMargem);
            txtmargem.setText(txtm);
            TextView txtpreco = (TextView)findViewById(R.id.txtPreco);
            txtpreco.setText(txtp);
            EditText edtvalorfinal = (EditText)findViewById(R.id.edtvalorfinal);
            edtvalorfinal.setText(txtp2);

        }
    }
    public void atualizaBD(View view){
        //Seja la qual for o destino das infos coletadas, usar esse método chamado pelo ultimo botão pra executar
        EditText edtvalorfinal = (EditText)findViewById(R.id.edtvalorfinal);
        float precofinal = Float.valueOf(edtvalorfinal.getText().toString());
        Toast.makeText(this, "O preço final é R$ "+precofinal, Toast.LENGTH_SHORT).show();
    }

}
