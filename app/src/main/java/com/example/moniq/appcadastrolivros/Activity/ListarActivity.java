package com.example.moniq.appcadastrolivros.Activity;

import android.app.Activity;
import android.support.v4.widget.ListViewAutoScrollHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.moniq.appcadastrolivros.Classes.Livro;
import com.example.moniq.appcadastrolivros.Classes.LivroAdapter;
import com.example.moniq.appcadastrolivros.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ListarActivity extends Activity {

    private ListView listView;
    private ArrayAdapter<Livro> adapterLivros;
    private ArrayList<Livro> listLivros;
    private ValueEventListener valueEventListener;
    private DatabaseReference databaseReference;
    private FirebaseDatabase firebaseDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar);

        listLivros = new ArrayList<>();
        listView = (ListView) findViewById(R.id.listViewLivro);
        adapterLivros = new LivroAdapter(this, listLivros);
        listView.setAdapter(adapterLivros);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("livro");


        valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listLivros.clear();

                for (DataSnapshot dados : dataSnapshot.getChildren()) {
                    Livro novoLivro = dados.getValue(Livro.class);
                    listLivros.add(novoLivro);
                }
                adapterLivros.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

    }

    @Override
    protected void onStop() {
        super.onStop();
        databaseReference.removeEventListener(valueEventListener);
    }

    @Override
    protected void onStart() {
        super.onStart();
        databaseReference.addValueEventListener(valueEventListener);
    }
}
