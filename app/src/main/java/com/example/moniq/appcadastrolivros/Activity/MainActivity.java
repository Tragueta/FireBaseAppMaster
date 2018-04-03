package com.example.moniq.appcadastrolivros.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.moniq.appcadastrolivros.R;

public class MainActivity extends Activity {

    private Button btnCadastrar;
    private Button btnListar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCadastrar = (Button) findViewById(R.id.btn_CadastrarLivros);
        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cadastrarLivros = new Intent(MainActivity.this, CadastrarActivity.class);
                startActivity(cadastrarLivros);
            }
        });

        btnListar = (Button) findViewById(R.id.btn_ListarLivros);
        btnListar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent listarLivros = new Intent(MainActivity.this, ListarActivity.class);
                startActivity(listarLivros);
            }
        });


    }
}
