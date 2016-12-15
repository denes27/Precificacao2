package com.example.denesleal.precificacao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by User on 14/12/2016.
 */

public class OrcamentoDAO implements DAO {
    private MeuBD dataSource;

    public OrcamentoDAO(MeuBD meuBD) {
        this.dataSource = meuBD;
    }


    @Override
    public void create(Object o) {
        Orcamento orc = (Orcamento) o;
        try {
            SQLiteDatabase db = dataSource.getWritableDatabase();

            ContentValues cv = new ContentValues();
            cv.put("id", orc.getId());
            cv.put("nome", orc.getNome());
            cv.put("email", orc.getSenttoemail());
            cv.put("total",orc.getTotal());

            // insiro na tabela, sem nenhuma coluna cujo valor é null
            db.insert(dataSource.TABLE_NAME2, null, cv);

            db.close();
            Log.d("OrcamentoDAO.CREATE", "Registro inserido com sucesso!");
        } catch (Exception ex) {
            Log.d("OrcamentoDAO", ex.getMessage());
        }

    }

    @Override
    public Object read(Object o) {
        ArrayList<Orcamento> lista = new ArrayList<Orcamento>();
        try {
            // defino um vetor com os nomes das colunas que eu quero
            String colunas[] = {"id", "nome", "email","total"};
            // pego a instancia de leitura do meu banco
            SQLiteDatabase db = dataSource.getReadableDatabase();

            // Toda Query retorna um objeto do tipo Cursor
            Cursor cursor = db.query(false,            // distinct?
                    MeuBD.TABLE_NAME2, // nome da tabela
                    colunas,          // nomes das colunas
                    null,             // where?
                    null,             // argumentos do where
                    null,             // argumentos do groupBy
                    null,             // argumentos do having
                    null,             // argumentos do orderBy
                    null);            // argumentos do limit

            if (cursor.moveToFirst()) {  // se houver registro vá para o 1o
                do {
                    Orcamento orc = new Orcamento();
                    orc.setId(cursor.getInt(0));         // recupero cada coluna pelo numero
                    orc.setNome(cursor.getString(1));
                    orc.setSenttoemail(cursor.getString(2));
                    orc.setTotal(cursor.getInt(3));
                    lista.add(orc);  // adiciono na lista

                } while (cursor.moveToNext()); // enquanto houver proximo registro
            }
            db.close();
            return lista;
        } catch (Exception ex) {
            Log.d("OrcamentoDAO.READ", ex.getMessage());
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


