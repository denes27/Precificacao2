package com.example.denesleal.precificacao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by User on 13/12/2016.
 */

public class MeuBD extends SQLiteOpenHelper {
    public static final String DB_NAME = "EmpriceOrc"
    public static final String TABLE_NAME = "Produtos";
    public static final String TABLE_NAME2 = "Orcamentos";
    public static final String TABLE_NAME3 = "Produtos_Orcamentos";
    public static final int    DB_VERSION = 1;


    public MeuBD(Context context, String name, SQLiteDatabase.CursorFactory factory,
                 int version) {
        super(context, name, factory, version);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String strSq1 = "CREATE TABLE IF NOT EXIST "+TABLE_NAME+ " (" +
                " id          INTEGER AUTOINCREMENT," +
                " nome        varchar(150), " +
                " price       decimal(10,2)) ";

        String strSq2 = "CREATE TABLE IF NOT EXIST "+TABLE_NAME2+ " (" +
                " id          INTEGER AUTOINCREMENT," +
                " nome        varchar(150), " +
                " senttoemail varchar(150)," +
                " total       decimal(10,2)) ";

        String strSq3 = "CREATE TABLE IF NOT EXIST "+TABLE_NAME3+ " (" +
                " orcamentos_id     int(11)," +
                " produtos_id       int(11)) ";

        db.execSQL(strSq1);
        db.execSQL(strSq2);
        db.execSQL(strSq3);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // do nothing
    }
}
