package com.paledot01.shoesformen.models;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.paledot01.shoesformen.DetailActivity;
import com.paledot01.shoesformen.EditActivity;
import com.paledot01.shoesformen.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CalzadoAdapter extends RecyclerView.Adapter<CalzadoAdapter.ViewHolder>{

    private ArrayList<Calzado> list_calzado;
    private Context contexto;

    // Creamos su constructor
    public CalzadoAdapter(ArrayList<Calzado> list_calzado){
        this.list_calzado = list_calzado;
    }

    // ------->
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Calzado calzado = list_calzado.get(position);

        holder.marca.setText(calzado.getMarca());
        holder.modelo.setText(calzado.getModelo());
        holder.talla.setText(calzado.getTalla());
        holder.precio.setText(calzado.getPrecio());
        Picasso.get().load(calzado.getUrl()).into(holder.image_calzado); // descarga la url de la imagen desde interne y coloca en imagen del cardview(holder)

        holder.onBind(position); // abajo
    }

    @Override
    public int getItemCount() {
        return list_calzado.size();
    }



    // --> clase anidada
    public class ViewHolder extends RecyclerView.ViewHolder{


        private ImageView image_calzado ;
        private TextView marca;
        private TextView modelo;
        private TextView talla;
        private TextView precio;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            image_calzado = (ImageView) itemView.findViewById(R.id.img_cardview_calzado);
            marca = (TextView) itemView.findViewById(R.id.txt_cardview_marca);
            modelo = (TextView) itemView.findViewById(R.id.txt_cardview_modelo);
            talla = (TextView) itemView.findViewById(R.id.txt_cardview_talla);
            precio = (TextView) itemView.findViewById(R.id.txt_cardview_precio);

        }

        public void onBind(int position) {
            Calzado calzado = list_calzado.get(position);

            itemView.setOnClickListener(v -> {
                Intent intent = new Intent(itemView.getContext(), DetailActivity.class);
                intent.putExtra("id",  calzado.getId());
                itemView.getContext().startActivity(intent);
            });

            itemView.setOnLongClickListener(v -> {
                Intent intent = new Intent(itemView.getContext(), EditActivity.class);
                intent.putExtra("id",  calzado.getId());
                itemView.getContext().startActivity(intent);
                return false;
            });

        }
    }

    public void deleteCalzado(int position) {
        if (list_calzado != null & list_calzado.size() > 0) {
            list_calzado.remove(position);
        }
        notifyItemRemoved(position);
        notifyDataSetChanged();
    }


}
