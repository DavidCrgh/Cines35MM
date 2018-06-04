package com.davidcr.cines35mm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.davidcr.cines35mm.dominio.Pelicula;
import java.lang.String;

public class Form_pelicula extends AppCompatActivity implements View.OnClickListener{
    private EditText titulo;
    private EditText directores;
    private EditText ano;
    private EditText generos;
    private EditText actores;
    private EditText sipnosis;
    private EditText keywords;
    private Button Registrar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_pelicula);

        titulo  =(EditText)  findViewById(R.id.nombre);
        directores  =(EditText)  findViewById(R.id.directores);
        ano  =(EditText)  findViewById(R.id.anos);
        generos  =(EditText)  findViewById(R.id.genero);
        actores  =(EditText)  findViewById(R.id.Actores);
        sipnosis  =(EditText)  findViewById(R.id.Sipnosis);
        keywords  =(EditText)  findViewById(R.id.Palabras_claves);
        Registrar = (Button) findViewById(R.id.Registrar);
        Registrar.setOnClickListener(this);
        }

    @Override
    public void onClick(View v) {
        if (v==Registrar){
            String tit = titulo.getText().toString().trim();
            String anno = ano.getText().toString().trim();
            String sip = sipnosis.getText().toString().trim();
            String actor = actores.getText().toString().trim();
            String director = directores.getText().toString().trim();
            String genero = generos.getText().toString().trim();
            String kw = keywords.getText().toString().trim();

            ArrayList<String> act = new ArrayList<String>(Arrays.asList(actor.split(",")));
            ArrayList<String> dir = new ArrayList<String>(Arrays.asList(director.split(",")));
            ArrayList<String> gen = new ArrayList<String>(Arrays.asList(genero.split(",")));
            ArrayList<String> kws = new ArrayList<String>(Arrays.asList(kw.split(",")));

            Pelicula pelicula = new Pelicula(
                    tit,
                    anno,
                    sip,
                    kws,
                    gen,
                    dir,
                    act);
            DatabaseReference mBasedatos = FirebaseDatabase.getInstance().getReference();
            mBasedatos.child("peliculas").push().setValue(pelicula);
        }
    }
}
