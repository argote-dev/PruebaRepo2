package com.davidargote.apppruebarepo2;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class adaptadorLista extends RecyclerView.Adapter<adaptadorLista.ViewHolderDatos> {

    ArrayList<ObjetoImagen> lista;

    public adaptadorLista(ArrayList<ObjetoImagen> objetos)
    {
        this.lista = objetos;
    }

    @NonNull
    @Override
    public adaptadorLista.ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View v = null;

        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,null,false);

        return new ViewHolderDatos(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos holder, int position) {

        holder.img.setImageURI(Uri.parse(lista.get(position).getUrl()));

    }

    @Override
    public int getItemCount() {
       return lista.size();
    }


    public class ViewHolderDatos extends RecyclerView.ViewHolder {

        ImageView img;

        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.imgListado);

        }
    }
}
