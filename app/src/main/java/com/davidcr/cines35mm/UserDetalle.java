package com.davidcr.cines35mm;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import com.davidcr.cines35mm.dominio.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.ValueEventListener;
import android.widget.*;
import java.util.ArrayList;
import android.*;
import  android.content.Intent;
import android.view.View.OnClickListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.Query;

public class UserDetalle extends AppCompatActivity {
    FirebaseDatabase firebaseDatabase;
    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;
    ArrayList<String> arrayList = new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;
    ListView lista;
    Boolean currentBloq;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detalle);
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference commandsRef = rootRef.child("Usuario");
        lista = (ListView) findViewById(R.id.listView);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                 String email = lista.getItemAtPosition(position).toString();
                 DatabaseReference UserRef = FirebaseDatabase.getInstance().getReference("Usuario");
                 Query query = UserRef.orderByChild("email").equalTo(email);
                 query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot child : dataSnapshot.getChildren()) {
                            currentBloq = child.child("bloqueado").getValue(Boolean.class);
                            if(currentBloq == true){
                                child.getRef().child("bloqueado").setValue(false);
                                Toast.makeText(UserDetalle.this,"Se ha desbloqueado al usuario",Toast.LENGTH_LONG).show();

                            }
                            else{
                                child.getRef().child("bloqueado").setValue(true);
                                Toast.makeText(UserDetalle.this,"Se ha bloqueado al usuario",Toast.LENGTH_LONG).show();
                            }

                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
                });
            }
        });
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    String em = ds.child("email").getValue(String.class);
                    if(!em.isEmpty()){
                         arrayList.add(em);}
                }
                arrayAdapter = new ArrayAdapter<String>(UserDetalle.this,android.R.layout.simple_list_item_1,arrayList);
                lista.setAdapter(arrayAdapter);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {}
        };
        commandsRef.addListenerForSingleValueEvent(eventListener);
    }
}


