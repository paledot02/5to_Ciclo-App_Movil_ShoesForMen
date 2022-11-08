package com.paledot01.shoesformen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.paledot01.shoesformen.includes.MyToolbar;
import com.paledot01.shoesformen.models.Calzado;

import java.util.Objects;

public class EditActivity extends AppCompatActivity {

    EditText mEditTextMarca;
    EditText mEditTextModelo;
    EditText mEditTextTalla;
    EditText mEditTextPrecio;
    EditText mEditTextDescripcion;
    EditText mEditTextURL;
    Button mButtonUpdate;

    private DatabaseReference mDatabaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        MyToolbar.show(this, "Editar Calzado", true);

        mEditTextMarca = (EditText) findViewById(R.id.txt_edit_marca);
        mEditTextModelo = (EditText) findViewById(R.id.txt_edit_modelo);
        mEditTextTalla = (EditText) findViewById(R.id.txt_edit_talla);
        mEditTextPrecio = (EditText) findViewById(R.id.txt_edit_precio);
        mEditTextDescripcion = (EditText) findViewById(R.id.txt_edit_descripcion);
        mEditTextURL = (EditText) findViewById(R.id.txt_edit_url);

        mButtonUpdate = (Button) findViewById(R.id.btnActualizar);

        String mKey= Objects.requireNonNull(getIntent().getExtras()).getString("id"); // obtenemos el id de el intent que llama a este activity
        mDatabaseReference = FirebaseDatabase.getInstance().getReference("calzado").child(mKey); // con esto ya obtiene la key de la pizza que le das click

        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Calzado mcalzado = dataSnapshot.getValue(Calzado.class);

                // Encuentra los textBox con su id y modifica su contenido
                mEditTextMarca.setText(mcalzado.getMarca());
                mEditTextModelo.setText(mcalzado.getModelo());
                mEditTextTalla.setText(mcalzado.getTalla());
                mEditTextPrecio.setText(mcalzado.getPrecio());
                mEditTextDescripcion.setText(mcalzado.getDescripcion());
                mEditTextURL.setText(mcalzado.getUrl());
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Toast.makeText(EditActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        mButtonUpdate.setOnClickListener(v -> {
            mDatabaseReference.child("marca").setValue(mEditTextMarca.getText().toString());
            mDatabaseReference.child("modelo").setValue(mEditTextModelo.getText().toString());
            mDatabaseReference.child("talla").setValue(mEditTextTalla.getText().toString());
            mDatabaseReference.child("precio").setValue(mEditTextPrecio.getText().toString());
            mDatabaseReference.child("descripcion").setValue(mEditTextDescripcion.getText().toString());
            mDatabaseReference.child("url").setValue(mEditTextURL.getText().toString());
            finish();
        });

    }
}