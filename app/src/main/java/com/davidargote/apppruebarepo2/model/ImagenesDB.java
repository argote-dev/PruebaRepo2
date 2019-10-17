package com.davidargote.apppruebarepo2.model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class ImagenesDB extends SQLiteOpenHelper {

    private final static int DATABASE_VERSION = 1;
    private final static String DATABASE_NAME = "imagenes";


    public ImagenesDB(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "CREATE TABLE imagenes (id integer PRIMARY KEY AUTOINCREMENT,"+
                "nombre TEXT NOT NULL,"+
                "imagen_url TEXT NOT NULL)";

        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


    public boolean insertar(String nombre, String url)
    {
        boolean paso = false;

        String sql = "INSERT INTO imagenes (nombre,imagen_url) VALUES (?,?)";
        String[] param = new String[] {nombre,url};

        try {
            SQLiteDatabase db = getWritableDatabase();
            db.execSQL(sql,param);
            paso = true;
        }catch (SQLiteException e){
            paso = false;
            Log.e("Error", e.getMessage());
        }

        return paso;

    }

    public ArrayList<ObjetoImagen> darListado()
    {
        ArrayList<ObjetoImagen> listado = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();
        String sql = "SELECT * FROM imagenes";
        Cursor registros = db.rawQuery(sql,null);

        if(registros.moveToFirst())
        {
            do {
                ObjetoImagen o = new ObjetoImagen(registros.getInt(0),registros.getString(1),registros.getString(2));
                listado.add(o);
            }while (registros.moveToNext());
        }

        return listado;
    }
}
