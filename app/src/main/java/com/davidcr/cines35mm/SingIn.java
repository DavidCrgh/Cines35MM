package com.davidcr.cines35mm;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SingIn extends AppCompatActivity  implements View.OnClickListener {
    private Button buttonRegistrarse;
    private EditText editText;
    private EditText editTextUser;
    private EditText editTextcontrasena;
    private TextView sesion;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_in);
        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
        buttonRegistrarse = (Button) findViewById(R.id.Registrarse);
        editText = (EditText) findViewById(R.id.email);
        editTextUser = (EditText) findViewById(R.id.username);
        editTextcontrasena = (EditText) findViewById(R.id.password);
        sesion = (TextView) findViewById(R.id.session);
        buttonRegistrarse.setOnClickListener(this);
        sesion.setOnClickListener(this);

    }

    private void registerUser(){
        final String email = editText.getText().toString().trim();
        final String username = editTextUser.getText().toString().trim();
        final String password = editTextcontrasena.getText().toString().trim();
        if (TextUtils.isEmpty(email)){
            Toast.makeText(this,"Por favor ingrese su correo",Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(username)){
            Toast.makeText(this,"Por favor ingrese su nombre de usuario",Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)){
            Toast.makeText(this,"Por favor ingrese su contraseña",Toast.LENGTH_SHORT).show();
            return;
        }
        progressDialog.setMessage("Registrando al usuario...");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this,new OnCompleteListener<AuthResult>()
        {

            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    progressDialog.dismiss();
                    Toast.makeText(SingIn.this,"Registro exitoso",Toast.LENGTH_SHORT).show();
                    User newUser = new User(username,email,password);
                    mDatabase = FirebaseDatabase.getInstance().getReference();
                    mDatabase.child("Usuario").child(String.valueOf(newUser.getId())).setValue(newUser);
                    startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                }
                else {
                    Toast.makeText(SingIn.this,"Registro no fue exitoso",Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }
        });

    }

    @Override
    public void onClick(View v) {
        if(v == buttonRegistrarse){
            registerUser();

        }

        if (v == sesion){
            finish();
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        }
    }
}
