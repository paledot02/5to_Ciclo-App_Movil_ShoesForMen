package com.paledot01.shoesformen;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.paledot01.shoesformen.includes.MyToolbar;
import com.paledot01.shoesformen.models.Calzado;

import dmax.dialog.SpotsDialog;

public class AddActivity extends AppCompatActivity {

    EditText mEditTextMarca;
    EditText mEditTextModelo;
    EditText mEditTextTalla;
    EditText mEditTextPrecio;
    EditText mEditTextDescripcion;
    EditText mEditTextURL;
    Button mButtonAdd;

    private DatabaseReference mDatabaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);


        MyToolbar.show(this, "Agregar Calzado", true);


        mEditTextMarca = (EditText) findViewById(R.id.txt_add_marca);
        mEditTextModelo = (EditText) findViewById(R.id.txt_add_modelo);
        mEditTextTalla = (EditText) findViewById(R.id.txt_add_talla);
        mEditTextPrecio = (EditText) findViewById(R.id.txt_add_precio);
        mEditTextDescripcion = (EditText) findViewById(R.id.txt_add_descripcion);
        mEditTextURL = (EditText) findViewById(R.id.txt_add_url);

        mButtonAdd = (Button) findViewById(R.id.btnRegister);

        mDatabaseReference = FirebaseDatabase.getInstance().getReference("calzado");

        mButtonAdd.setOnClickListener(v -> {


            String marca = mEditTextMarca.getText().toString();
            String modelo = mEditTextModelo.getText().toString();
            String talla = mEditTextTalla.getText().toString();
            String precio = mEditTextPrecio.getText().toString();
            String descripcion = mEditTextDescripcion.getText().toString();
            String url = mEditTextURL.getText().toString();


            Calzado mcalzado = new Calzado(marca, modelo, talla, precio, descripcion, url);
            String id = mDatabaseReference.push().getKey();
            if(id !=null){
                mDatabaseReference.child(id).setValue(mcalzado);
            }


            Intent intent=new Intent(this, ListActivity.class);
            startActivity(intent);

        });

    }
}