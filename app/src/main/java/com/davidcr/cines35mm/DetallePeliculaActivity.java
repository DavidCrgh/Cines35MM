package com.davidcr.cines35mm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.davidcr.cines35mm.dominio.Pelicula;
import com.davidcr.cines35mm.dominio.PeliculaSimple;

public class DetallePeliculaActivity extends AppCompatActivity {
    private Pelicula pelicula;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_pelicula);
        getIncomingIntent();
        llenarDetallesPelicula();
    }

    private void getIncomingIntent(){
        if(getIntent().hasExtra("peliculaSimple")){
            PeliculaSimple peliculaSimple =
                    (PeliculaSimple) getIntent().getSerializableExtra("peliculaSimple");
            pelicula = peliculaSimple.getPelicula();

        }
    }

    private void llenarDetallesPelicula(){
        TextView titulo = findViewById(R.id.tv_titulo);
        titulo.setText(pelicula.getTitulo());

        TextView anno_generos = findViewById(R.id.tv_anno_generos);
        anno_generos.setText(pelicula.getAnno()+ " " + Pelicula.arrayToString(pelicula.getGeneros()));

        TextView sinopsis = findViewById(R.id.tv_sinopsis);
        sinopsis.setText("Sinopsis: " + pelicula.getSinopsis());

        TextView directores = findViewById(R.id.tv_directores);
        directores.setText("Directores: " + Pelicula.arrayToString(pelicula.getDirectores()));

        TextView actores = findViewById(R.id.tv_actores);
        actores.setText("Actores: " + Pelicula.arrayToString(pelicula.getActores()));

        TextView keywords = findViewById(R.id.tv_keywords);
        keywords.setText("Keywords: " + Pelicula.arrayToString(pelicula.getKeywords()));
    }
}
