package com.davidargote.apppruebarepo2.controller;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.davidargote.apppruebarepo2.R;
import com.davidargote.apppruebarepo2.draw.CanvasDraw;
import com.davidargote.apppruebarepo2.model.ImagenesDB;
import com.frosquivel.magicalcamera.MagicalCamera;
import com.frosquivel.magicalcamera.MagicalPermissions;

import java.util.UUID;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnTakePhoto, btnSave;
    private CanvasDraw canvasDraw;
    private ImageView btnClose;

    public static String[] PERMISSONS = {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
    public static int RESIZE_PHOTO = 10;

    private MagicalPermissions permissions;
    private MagicalCamera camera;

    private Bitmap bitmapPhoto;
    private String urlPhoto = "";

    private ImagenesDB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        permissions = new MagicalPermissions(this, PERMISSONS);
        camera = new MagicalCamera(this, RESIZE_PHOTO, permissions);

        db = new ImagenesDB(getApplicationContext());

        initViews();

    }

    private void initViews() {

        btnTakePhoto = findViewById(R.id.btnTakePhoto);
        btnTakePhoto.setOnClickListener(this);

        btnSave = findViewById(R.id.btnSavePhoto);
        btnSave.setOnClickListener(this);

        btnClose = findViewById(R.id.btnClose);
        btnClose.setOnClickListener(this);

        canvasDraw = findViewById(R.id.canvasView);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btnTakePhoto:
                camera.takePhoto();
                break;

            case R.id.btnSavePhoto:
                if (camera.getPhoto() != null) {
                    savePhotoInStorage();
                }else{
                    Toast.makeText(this, "Toma primero una foto", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.btnClose:
                System.exit(0);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        camera.resultPhoto(requestCode, resultCode, data, MagicalCamera.ORIENTATION_ROTATE_90);

        if (camera.getPhoto() != null) {

            bitmapPhoto = camera.getPhoto();

            //Cambia el background de el canvas para pintar la imagen
            Drawable drawable = new BitmapDrawable(bitmapPhoto);
            canvasDraw.setBackgroundDrawable(drawable);

        } else {
            Toast.makeText(this, "No se tomó la foto", Toast.LENGTH_SHORT).show();
        }

    }

    public void savePhotoInStorage() {

        String nombre = UUID.randomUUID().toString();
        urlPhoto = camera.savePhotoInMemoryDevice(bitmapPhoto, nombre, false);

        if (urlPhoto != null) {
            if (db.insertar(nombre, urlPhoto)) {
                Toast.makeText(this, "  Guardada con exito", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Error al guardar", Toast.LENGTH_SHORT).show();
            }
        }

    }

}
