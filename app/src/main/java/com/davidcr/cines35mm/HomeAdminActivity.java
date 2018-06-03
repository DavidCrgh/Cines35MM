package com.davidcr.cines35mm;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;

import com.davidcr.cines35mm.adapters.PeliculaSimpleAdapter;
import com.davidcr.cines35mm.dominio.Pelicula;
import com.davidcr.cines35mm.dominio.PeliculaSimple;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HomeAdminActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private ArrayList<PeliculaSimple> peliculasBusqueda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_admin);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_home_admin);

        //Mejora rendimiento
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        obtenerPeliculasInicio();
        configurarBotonBuscar();
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
        getMenuInflater().inflate(R.menu.home_admin, menu);
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

        } else if (id == R.id.nav_comentarios) {

        } else if (id == R.id.nav_salir) {

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
        DatabaseReference mBasedatos = FirebaseDatabase.getInstance().getReference().child("peliculas");

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
