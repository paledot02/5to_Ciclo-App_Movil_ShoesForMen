package com.paledot01.shoesformen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.paledot01.shoesformen.includes.MyToolbar;
import com.paledot01.shoesformen.models.Calzado;
import com.paledot01.shoesformen.models.CalzadoAdapter;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    private ArrayList<Calzado> lista_calzado;
    RecyclerView mRecyclerView;
    CalzadoAdapter mCalzadoAdapter;
    FloatingActionButton mFloatingActionButton;

    private DatabaseReference mDatabaseReference; // para actualizar la base de datos


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        MyToolbar.show(this, "Lista Calzado", true);

        mDatabaseReference = FirebaseDatabase.getInstance().getReference("calzado");
        lista_calzado = new ArrayList<>();

        // creamos el Recycler
        mRecyclerView = (RecyclerView) findViewById(R.id.RecyclerViewCalzado);
        // Creamos el LayoutManager y se lo pasamos al Recycler creado
        LinearLayoutManager mlinearLayoutManager = new LinearLayoutManager(this);
        mlinearLayoutManager.setOrientation(mlinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mlinearLayoutManager);
        // Cremos el adaptador y se lo pasamos al Recycler creado
        mCalzadoAdapter = new CalzadoAdapter(lista_calzado);
        mRecyclerView.setAdapter(mCalzadoAdapter);


        mFloatingActionButton = findViewById(R.id.ButtonFlotante);
        mFloatingActionButton.setOnClickListener(v -> {
            Intent intent=new Intent(ListActivity.this, AddActivity.class);
            startActivity(intent);
        });

        updateData();
        deleteSwipe();
    }

    private void updateData(){
        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                lista_calzado.clear();

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                    Calzado mcalzado = postSnapshot.getValue(Calzado.class);

                    if (mcalzado != null) {
                        mcalzado.setId(postSnapshot.getKey());
                    }

                    lista_calzado.add(mcalzado);

                }
                mCalzadoAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Toast.makeText(ListActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void deleteSwipe() {

        ItemTouchHelper.SimpleCallback touchHelperCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                mDatabaseReference.child(lista_calzado.get(viewHolder.getAdapterPosition()).getId()).setValue(null);
                mCalzadoAdapter.deleteCalzado(viewHolder.getAdapterPosition());
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(touchHelperCallback);
        itemTouchHelper.attachToRecyclerView(mRecyclerView);
    }
}