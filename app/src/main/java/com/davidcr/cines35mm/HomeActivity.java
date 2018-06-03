package com.davidcr.cines35mm;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.davidcr.cines35mm.dominio.Favorito;
import com.davidcr.cines35mm.dominio.Pelicula;
import com.davidcr.cines35mm.adapters.PeliculaSimpleAdapter;
import com.davidcr.cines35mm.dominio.PeliculaSimple;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.net.SocketPermission;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private FirebaseAuth firebaseAuth;

    private ArrayList<PeliculaSimple> peliculasBusqueda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Hola mundo
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                ArrayList<String> keywords = new ArrayList<>();
                ArrayList<String> generos = new ArrayList<>();
                ArrayList<String> directores = new ArrayList<>();
                ArrayList<String> actores = new ArrayList<>();
                keywords.add("k1");
                keywords.add("k2");
                generos.add("g1");
                directores.add("d1");
                actores.add("a1");
                actores.add("a2");
                actores.add("a3");
                Pelicula pelicula = new Pelicula(
                        "Scarface",
                        "1987",
                        "Sinopsis de scarface",
                        keywords,
                        generos,
                        directores,
                        actores);
                DatabaseReference mBasedatos = FirebaseDatabase.getInstance().getReference();
                mBasedatos.child("peliculas").child("2").setValue(pelicula);

            }
        });*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_home_cliente);

        //Mejora rendimiento
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        obtenerPeliculasInicio();

        /*ArrayList<PeliculaSimple> peliculaSimples = new ArrayList<>();
        peliculaSimples.add(new PeliculaSimple("Titanic","1980"));
        peliculaSimples.add(new PeliculaSimple("John Wick", "2013"));
        peliculaSimples.add(new PeliculaSimple("Scarface", "1987"));*/
/*
        mAdapter = new PeliculaSimpleAdapter(
                obtenerPeliculasInicio()
        );
        mRecyclerView.setAdapter(mAdapter);*/
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_inicio) {
            // Handle the camera action
        } else if (id == R.id.nav_recomendaciones) {

        } else if (id == R.id.nav_favoritas) {
            obtenerPeliculasFavoritas();
        } else if (id == R.id.nav_comentarios) {

        } else if (id == R.id.nav_salir) {
           // firebaseAuth.signOut();
            finish();
            startActivity(new Intent(getApplicationContext(),LoginActivity.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void configurarBotonBuscar(){
        ImageView imagenBuscar = findViewById(R.id.img_buscar);
        imagenBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Snackbar.make(v, "HOLA MUNDO", Snackbar.LENGTH_LONG).setAction("Action", null). show();
                obtenerPeliculasBusqueda();
            }
        });
        peliculasBusqueda = new ArrayList<>();
    }

    public void obtenerPeliculasBusqueda(){
        peliculasBusqueda.clear();
        EditText entrada_termino = findViewById(R.id.txt_termino_busqueda);
        String termino = entrada_termino.getText().toString();

        DatabaseReference mBasedatos =
                FirebaseDatabase.getInstance().getReference().child("peliculas");
        //mBasedatos.push().setValue()

        Query consulta;
        String[] atributos = {"titulo", "anno", "sinopsis"};

        for(String atributo : atributos){
            consulta = mBasedatos.orderByChild(atributo).startAt(termino).endAt(termino + "\uf8ff"); //Unicode requerido por Firebase
            consulta.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    desplegarPeliculasBusqueda(dataSnapshot);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
    }

    public void obtenerPeliculasInicio(){
        DatabaseReference mBasedatos =
                FirebaseDatabase.getInstance().getReference().child("peliculas");

        mBasedatos.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                desplegarPeliculasSimples(dataSnapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

public void obtenerPeliculasFavoritas(){

    final List<PeliculaSimple> peliculasFavoritas = new ArrayList<>();
    //todas las peliculas
    final DatabaseReference peliculas = FirebaseDatabase.getInstance().getReference().child("peliculas");

    //Peliculas favoritas de usuario
    Query mBasedatos = FirebaseDatabase.getInstance().getReference().child("favorito").orderByChild("usuario_alias").equalTo("Matilda".toString());
    mBasedatos.addListenerForSingleValueEvent(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull final DataSnapshot dataSnapshotF) {
            //todas las peliculas
            DatabaseReference peliculas = FirebaseDatabase.getInstance().getReference().child("peliculas");
            //poder leer peliculas
            peliculas.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    ArrayList<PeliculaSimple> peliculasFavoritas = new ArrayList<>();
                    for (DataSnapshot snapshotF : dataSnapshotF.getChildren()) {
                        String llaveF = snapshotF.getKey();
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            String llave = snapshot.getKey();
                            System.out.println("llave: "+llave);
                            Pelicula pelicula = snapshot.getValue(Pelicula.class);
                            if (llaveF.equals(llave)) {
                                peliculasFavoritas.add(new PeliculaSimple(
                                        llave,
                                        pelicula
                                ));
                            }

                           // mRecyclerView.setAdapter(new PeliculaSimpleAdapter(peliculasFavoritas,PeliculaSimpleAdapter.)));

                            mRecyclerView.setAdapter(new PeliculaSimpleAdapter(peliculasFavoritas, getApplicationContext()));
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    });

}

    private void desplegarPeliculasSimples(DataSnapshot dataSnapshot){
        ArrayList<PeliculaSimple> peliculasSimples = new ArrayList<>();

        for(DataSnapshot snapshot : dataSnapshot.getChildren()){
            String llave = snapshot.getKey();
            Pelicula pelicula = snapshot.getValue(Pelicula.class);
            peliculasSimples.add(new PeliculaSimple(
                    llave,
                    pelicula
            ));
        }

        mRecyclerView.setAdapter(new PeliculaSimpleAdapter(peliculasSimples, this));
    }


    private void desplegarPeliculasBusqueda(DataSnapshot dataSnapshot){
        for(DataSnapshot snapshot : dataSnapshot.getChildren()){
            String llave = snapshot.getKey();
            Pelicula pelicula = snapshot.getValue(Pelicula.class);
            agregarPeliculaBusqueda(llave, pelicula);
        }
        mRecyclerView.setAdapter(new PeliculaSimpleAdapter(peliculasBusqueda, this));
    }

    private void agregarPeliculaBusqueda(String llave, Pelicula pelicula) {
        for(PeliculaSimple peliculaSimple : peliculasBusqueda){
            if(peliculaSimple.getLlave().equals(llave)){
                return;
            }
        }
        peliculasBusqueda.add(new PeliculaSimple(llave, pelicula));
    }
}
