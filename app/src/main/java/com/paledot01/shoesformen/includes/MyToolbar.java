package com.paledot01.shoesformen.includes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.paledot01.shoesformen.R;

public class MyToolbar {

    public static void show(AppCompatActivity activity, String title, boolean upButton){

        // esto es para el toolbar las 4 filas
        Toolbar toolbar = activity.findViewById(R.id.toolbar);
        activity.setSupportActionBar(toolbar);
        activity.getSupportActionBar().setTitle(title);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);

    }

}
