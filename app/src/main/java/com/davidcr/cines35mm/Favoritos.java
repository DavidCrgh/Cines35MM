package com.davidcr.cines35mm;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.davidcr.cines35mm.dominio.Favorito;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import com.google.firebase.database.DataSnapshot;
public class Favoritos extends AppCompatActivity {
    FirebaseDatabase firebaseDatabase;
    FirebaseAuth firebaseAuth;
    ArrayList<String> arrayList = new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;
    ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoritos);
        DatabaseReference databaseReference;


        //DatabaseReference UserRef = FirebaseDatabase.getInstance().getReference("favorito");
        //Query query = UserRef.orderByChild("usuario_alias").equalTo(firebaseAuth.getCurrentUser().getEmail().toString());
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference commandsRef = rootRef.child("favorito");

        lista = (ListView) findViewById(R.id.Lista);
        commandsRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
                Toast.makeText(Favoritos.this,"hola",Toast.LENGTH_LONG);

                if(firebaseAuth.getCurrentUser().getEmail() != null){
                    final String email1 = firebaseAuth.getCurrentUser().getEmail().toString().trim();

                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        String email = snapshot.child("usuario_alias").getValue(String.class);
                        String peli = snapshot.child("pelicula_id").getValue(String.class);
                        if(email != null){
                            if(peli != null ){
                                arrayList.add(peli);
                            }
                        }
                    }}

                arrayAdapter = new ArrayAdapter<String>(Favoritos.this, android.R.layout.simple_list_item_1, arrayList);
                lista.setAdapter(arrayAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        }
}
