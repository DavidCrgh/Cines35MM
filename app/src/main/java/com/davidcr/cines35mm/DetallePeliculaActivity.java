package com.davidcr.cines35mm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.davidcr.cines35mm.dominio.Pelicula;
import com.davidcr.cines35mm.dominio.PeliculaSimple;
import com.google.firebase.auth.FirebaseAuth;

public class DetallePeliculaActivity extends AppCompatActivity {
    private String llavePelicula;
    private Pelicula pelicula;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_pelicula);
        getIncomingIntent();
        llenarDetallesPelicula();
        //Hace que no despliege el teclado cuando se abre la actividad
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        configurarInterfazAdmin(); //Siempre debe ir al final de onCreate

    }

    private void getIncomingIntent(){
        if(getIntent().hasExtra("peliculaSimple")){
            PeliculaSimple peliculaSimple =
                    (PeliculaSimple) getIntent().getSerializableExtra("peliculaSimple");
            llavePelicula = peliculaSimple.getLlave();
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

    private void configurarInterfazAdmin(){
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser().getDisplayName().equals("true")){
            //Si es admin
            Button boton_favoritos = findViewById(R.id.btn_favorito);
            boton_favoritos.setText("EDIT");

            boton_favoritos.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    abrirPantallaModificar();
                }
            });

            findViewById(R.id.txt_favoritos).setVisibility(View.INVISIBLE);

            findViewById(R.id.pt_comentario).setVisibility(View.GONE);

            findViewById(R.id.btn_comentario).setVisibility(View.GONE);


            //EditText entrada_comentario = findViewById(R.id.pt_comentario);
            //entrada_comentario.setVisibility(View.GONE);
        }
    }

    private void abrirPantallaModificar(){
        Intent intent = new Intent(this, Form_pelicula.class);
        intent.putExtra("IS_EDIT_MODE", true);
        intent.putExtra("LLAVE_PELICULA", llavePelicula);
        intent.putExtra("PELICULA", pelicula);
        startActivity(intent);
    }
}
