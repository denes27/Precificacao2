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

import static com.example.denesleal.precificacao.Ferramentas.nomeUsuario;

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
//Inicializando variaveis pro java não reclamar;
        Float valoraluguel = Float.valueOf(0);
        Float valorenergia = Float.valueOf(0);
        Float valorcombustivel = Float.valueOf(0);
        Float valoragua = Float.valueOf(0);
        Float valortempo = Float.valueOf(0);
        Float valorlucro = Float.valueOf(0);

        //Pegando valores fornecidos pelo usuário;
        EditText aluguel = (EditText)findViewById(R.id.edtAluguel);
        EditText Energia = (EditText)findViewById(R.id.edtEnergia);
        EditText Combustivel = (EditText)findViewById(R.id.edtCombustivel);
        EditText Agua = (EditText)findViewById(R.id.edtAgua);
        EditText Tempo = (EditText)findViewById(R.id.edtTempo);
        EditText Lucro = (EditText)findViewById(R.id.edtLucro);

        //Condição pra evitar que tenha espaços vazios, pq aí da pau;
        if     (aluguel.length() == 0 ||
                Energia.length() == 0 ||
                Combustivel.length() == 0 ||
                Agua.length() == 0 ||
                Tempo.length() == 0 ||
                Lucro.length() == 0){

            Toast.makeText(this, "Preencha todos os campos.", Toast.LENGTH_SHORT).show();


        }else {
            //Se todos os campos estiverem preenchidos, cria floats com os valores digitados pelo usuário;
            valoraluguel = Float.valueOf(aluguel.getText().toString());
            valorenergia = Float.valueOf(Energia.getText().toString());
            valorcombustivel = Float.valueOf(Combustivel.getText().toString());
            valoragua = Float.valueOf(Agua.getText().toString());
            valortempo = Float.valueOf(Tempo.getText().toString());
            valorlucro = Float.valueOf(Lucro.getText().toString());

            float despesa = 0;

            despesa = (valoraluguel + valoragua + valorenergia + valorcombustivel)*(valortempo/160);
            //lembrando que 160 horas é assumindo que a pessoa trabalha 8h por dia, 5 dias por semana.
            // Talvez a gente tenha que diminuir o número, pq assume que todo o trabalho da pessoa vai para a produção;
            float horatrabalhada = (valortempo*valorlucro)/160;
            float margem = despesa + precoMP;
            float preco = margem + horatrabalhada;
            Toast.makeText(this, "A margem é: "+margem+" e o preço é: "+preco, Toast.LENGTH_LONG).show();
//Mudar para a ultima tela
            setContentView(R.layout.fimdoprocesso);

            //colocar os valores nos textviews e no editText
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
       EditText edtNomeProduto = (EditText)findViewById(R.id.edtNomeProduto);
        EditText edtvalorfinal = (EditText)findViewById(R.id.edtvalorfinal);
        TextView txtPreco = (TextView)findViewById(R.id.txtPreco);

        Produtos prod = new Produtos();
        prod.setNome(edtNomeProduto.getText().toString());

        if(edtvalorfinal.length() != 0) {
            prod.setPreco(Float.valueOf(edtvalorfinal.getText().toString()));
        }
        else{
            prod.setPreco(Float.valueOf(txtPreco.getText().toString()));
        }

        MeuBD meuBD = new MeuBD(this,MeuBD.DB_NAME,null,MeuBD.DB_VERSION);

        ProdutosDAO produtosDAO = new ProdutosDAO(meuBD);

        produtosDAO.create(prod);

        float precofinal = Float.valueOf(edtvalorfinal.getText().toString());
        Toast.makeText(this, "o nome de usuario é "+nomeUsuario, Toast.LENGTH_SHORT).show();
    }

}
