package com.davidargote.apppruebarepo2.controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.davidargote.apppruebarepo2.R;
import com.davidargote.apppruebarepo2.model.ImagenesDB;
import com.davidargote.apppruebarepo2.model.ObjetoImagen;
import com.davidargote.apppruebarepo2.model.adaptadorLista;

import java.util.ArrayList;

public class ListadoActivity extends AppCompatActivity {

    private RecyclerView recy;
    ImagenesDB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);

        db = new ImagenesDB(getApplicationContext());

        recy = (RecyclerView) findViewById(R.id.lvImagenes);
        recy.setLayoutManager(new LinearLayoutManager(this));

        final ArrayList<ObjetoImagen> datos = db.darListado();
        adaptadorLista adapter = new adaptadorLista(datos);

        recy.setAdapter(adapter);
    }
}
