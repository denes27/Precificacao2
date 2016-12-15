package com.example.denesleal.precificacao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by User on 14/12/2016.
 */

public class ProdutosDAO implements DAO {
    private MeuBD dataSource;

    public ProdutosDAO(MeuBD meuBD) {
        this.dataSource = meuBD;
    }


    @Override
    public void create(Object o) {
        Produtos prod = (Produtos) o;
        try {
            SQLiteDatabase db = dataSource.getWritableDatabase();

            ContentValues cv = new ContentValues();
            cv.put("id", prod.getId());
            cv.put("nome", prod.getNome());
            cv.put("preco", prod.getPreco());

            // insiro na tabela, sem nenhuma coluna cujo valor é null
            db.insert(dataSource.TABLE_NAME, null, cv);

            db.close();
            Log.d("ProdutosDAO.CREATE", "Registro inserido com sucesso!");
        } catch (Exception ex) {
            Log.d("ProdutosDAO", ex.getMessage());
        }

    }

    @Override
    public Object read(Object o) {
        ArrayList<Produtos> lista = new ArrayList<Produtos>();
        try {
            // defino um vetor com os nomes das colunas que eu quero
            String colunas[] = {"id", "nome", "preco"};
            // pego a instancia de leitura do meu banco
            SQLiteDatabase db = dataSource.getReadableDatabase();

            // Toda Query retorna um objeto do tipo Cursor
            Cursor cursor = db.query(false,            // distinct?
                    MeuBD.TABLE_NAME, // nome da tabela
                    colunas,          // nomes das colunas
                    null,             // where?
                    null,             // argumentos do where
                    null,             // argumentos do groupBy
                    null,             // argumentos do having
                    null,             // argumentos do orderBy
                    null);            // argumentos do limit

            if (cursor.moveToFirst()) {  // se houver registro vá para o 1o
                do {
                    Produtos prod = new Produtos();
                    prod.setId(cursor.getInt(0));         // recupero cada coluna pelo numero
                    prod.setNome(cursor.getString(1));
                    prod.setPreco(cursor.getInt(2));
                    lista.add(prod);  // adiciono na lista

                } while (cursor.moveToNext()); // enquanto houver proximo registro
            }
            db.close();
            return lista;
        } catch (Exception ex) {
            Log.d("ProdutosDAO.READ", ex.getMessage());
            return null;
        }
    }

    @Override
    public void update (Object o){

    }

    @Override
    public void delete (Object o){

    }

}
