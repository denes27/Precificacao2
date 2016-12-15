package com.example.denesleal.precificacao;

import android.app.Activity;
import android.app.Notification;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by User on 15/12/2016.
 */

public class ListaOrcamento extends Activity {
    MeuBD meuBD = new MeuBD(this, MeuBD.DB_NAME, null, MeuBD.DB_VERSION);
    private ProdutosDAO produtosDAO = new ProdutosDAO(meuBD);
    ArrayList<Produtos> lista = (ArrayList<Produtos>)produtosDAO.read(null);
    private ListView products;


    protected void OnCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculo_despesas);
    }
}
