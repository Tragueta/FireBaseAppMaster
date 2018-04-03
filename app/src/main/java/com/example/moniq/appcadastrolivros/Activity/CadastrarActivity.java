package com.example.moniq.appcadastrolivros.Activity;

import android.app.Activity;
import android.content.Intent;
import android.preference.EditTextPreference;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.moniq.appcadastrolivros.Classes.Livro;
import com.example.moniq.appcadastrolivros.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

public class CadastrarActivity extends Activity {

    private EditText editNome;
    private EditText editGenero;
    private EditText editAutor;
    private Button btnCadastrar;
    private Button btnVoltar;
    private Livro livro;
    private DatabaseReference databaseReference;
    private FirebaseDatabase firebaseDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar);

        editNome = (EditText) findViewById(R.id.editNome);
        editGenero = (EditText) findViewById(R.id.editGenero);
        editAutor = (EditText) findViewById(R.id.editAutor);
        btnCadastrar = (Button) findViewById(R.id.btnCadastrar);
        btnVoltar = (Button) findViewById(R.id.btnVoltar);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("livro");


       btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String id = UUID.randomUUID().toString();
                Livro livro = new Livro();


                String nome = editNome.getText().toString();
                String genero = editGenero.getText().toString();
                String autor = editAutor.getText().toString();

                livro.setId(id);
                livro.setNome(nome);
                livro.setGenero(genero);
                livro.setAutor(autor);

                databaseReference.child(id).setValue(livro);
                Intent voltar = new Intent(CadastrarActivity.this, MainActivity.class);
                Toast.makeText(CadastrarActivity.this, "Livro cadastrado com sucesso!", Toast.LENGTH_SHORT);
                startActivity(voltar);


            }
        });

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent voltar = new Intent(CadastrarActivity.this, MainActivity.class);
                startActivity(voltar);
            }
        });

    }
}
