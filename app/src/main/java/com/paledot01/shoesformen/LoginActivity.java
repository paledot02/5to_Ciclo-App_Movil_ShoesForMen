package com.paledot01.shoesformen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import dmax.dialog.SpotsDialog;

public class LoginActivity extends AppCompatActivity {


    TextInputEditText mTextInputEmail; // para obtener el email
    TextInputEditText mTextInputPassword; // para obtener el password
    Button mButtonLogin; // para la funcion del boton
    FirebaseAuth mAuth; // autenticacon de FIrebase
    DatabaseReference mDatabase; // base de datos para FIrebase

    AlertDialog mDialog; // barra de carga

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        // obtenermos lo necesario
        mTextInputEmail = (TextInputEditText)  findViewById(R.id.txt_login_correo);
        mTextInputPassword = (TextInputEditText) findViewById(R.id.txt_login_pass);
        mButtonLogin = (Button) findViewById(R.id.btn_login_login);
        mAuth = FirebaseAuth.getInstance(); // isntanciamos la autenticacion del Firebase para ...xdsdasd
        mDatabase = FirebaseDatabase.getInstance().getReference(); // instancia la base de datos, 2 importante

        mButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        // para la barra de carga(espera) de github
        mDialog = new SpotsDialog.Builder().setContext(LoginActivity.this).setMessage("Espere un momento").build();

    }

    private void login() {

        String email = mTextInputEmail.getText().toString();
        String password = mTextInputPassword.getText().toString();

        // Firebase restringe que las constraseñas sean mayores a 6 caracteres.
        if(!email.isEmpty() && !password.isEmpty()){
            if(password.length() >= 6){
                mDialog.show(); // inicia la ventana de carga
                mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {  // Evento que detecta si se ah completado el logig al sistema Firebase de manera correcta
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
//                            Toast.makeText(LoginActivity.this, "El login se realizo correctamente", Toast.LENGTH_LONG).show();

                            Intent intent = new Intent(LoginActivity.this, ListActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); // para que no pueda regresar
                            startActivity(intent);

                        }else{
                            Toast.makeText(LoginActivity.this, "El email o el password son incorrecto", Toast.LENGTH_LONG).show();
                        }
                        mDialog.dismiss(); // cuando termine de cargar para que cierre la ventana de carga
                    }
                });
            }else{
                Toast.makeText(LoginActivity.this, "La contraseña debe tener mas de 6 caracteres", Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(LoginActivity.this, "La contraseña y el email son obligatorios", Toast.LENGTH_LONG).show();
        }

    }
}