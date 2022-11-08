package com.paledot01.shoesformen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.paledot01.shoesformen.includes.MyToolbar;
import com.paledot01.shoesformen.models.Calzado;
import com.squareup.picasso.Picasso;

import java.util.Objects;

public class DetailActivity extends AppCompatActivity {

    TextView mTextViewMarca;
    TextView mTextViewModelo;
    TextView mTextViewTalla;
    TextView mTextViewPrecio;
    TextView mTextViewDescripcion;
    TextView mTextViewURL;
    ImageView image_calzado;

    private DatabaseReference mDatabaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


        MyToolbar.show(this, "Detalle Calzado", true);

        mTextViewMarca = (TextView) findViewById(R.id.txt_detail_marca);
        mTextViewModelo = (TextView) findViewById(R.id.txt_detail_modelo);
        mTextViewTalla = (TextView) findViewById(R.id.txt_detail_talla);
        mTextViewPrecio = (TextView) findViewById(R.id.txt_detail_precio);
        mTextViewDescripcion = (TextView) findViewById(R.id.txt_detail_descripcion);
        mTextViewURL = (TextView) findViewById(R.id.txt_detail_url);
        image_calzado = (ImageView) findViewById(R.id.img_detail_calzado);

        String mKey= Objects.requireNonNull(getIntent().getExtras()).getString("id");
        mDatabaseReference = FirebaseDatabase.getInstance().getReference("calzado").child(mKey);

        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Calzado mcalzado = dataSnapshot.getValue(Calzado.class);

                // Encuentra los textBox con su id y modifica su contenido
                mTextViewMarca.setText(mcalzado.getMarca());
                mTextViewModelo.setText(mcalzado.getModelo());
                mTextViewTalla.setText(mcalzado.getTalla());
                mTextViewPrecio.setText(mcalzado.getPrecio());
                mTextViewDescripcion.setText(mcalzado.getDescripcion());
                mTextViewURL.setText(mcalzado.getUrl());
                Picasso.get().load(mcalzado.getUrl()).into(image_calzado);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                Toast.makeText(DetailActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}