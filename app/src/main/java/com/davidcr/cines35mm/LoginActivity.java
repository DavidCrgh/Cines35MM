package com.davidcr.cines35mm;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.davidcr.cines35mm.dominio.Pelicula;
import com.davidcr.cines35mm.dominio.PeliculaSimple;
import com.davidcr.cines35mm.dominio.User;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import java.util.List;
import java.util.ArrayList;
import org.w3c.dom.Text;

import java.io.BufferedReader;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener
{
    private Button Ingresar;
    private EditText email;
    private EditText contrasena;
    private TextView Signin;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //Para saltar directo al inicio, quitar despues //TODO
        //Intent intent = new Intent(this, HomeActivity.class);
        //startActivity(intent);
        ///////////////////////////////////////////////////////////////
        firebaseAuth = FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser() != null){

        }
        progressDialog = new ProgressDialog(this);
        Ingresar = (Button) findViewById(R.id.button);
        email = (EditText) findViewById(R.id.editText);
        contrasena = (EditText) findViewById(R.id.editText2);
        Signin = (TextView) findViewById(R.id.textView3);
        Ingresar.setOnClickListener(this);
        Signin.setOnClickListener(this);
    }
    public void LogIn(){
        String con = contrasena.getText().toString().trim();
        final String  em  = email.getText().toString().trim();

        if (TextUtils.isEmpty(em)){
            Toast.makeText(this,"Por favor ingrese su correo",Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(con)){
            Toast.makeText(this,"Por favor ingrese su contraseña",Toast.LENGTH_SHORT).show();
            return;
        }
        progressDialog.setMessage("Verificando usuario...");
        progressDialog.show();
        firebaseAuth.signInWithEmailAndPassword(em,con)
                .addOnCompleteListener(this,new OnCompleteListener<AuthResult>()
                {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            finish();
                            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                            if(firebaseAuth.getCurrentUser().getDisplayName().equals("true"))
                            {
                                //abrir administrador
                                finish();
                                startActivity(new Intent(getApplicationContext(), HomeAdminActivity.class));
                            }
                        }
                        else{
                            finish();
                            startActivity(new Intent(getApplicationContext(), HomeActivity.class));

                        }
                    }

                });
    }

    @Override
    public void onClick(View v) {
        if(v == Ingresar){
            LogIn();
        }

        if (v == Signin){
            finish();
            startActivity(new Intent(this, SingIn.class));
        }
    }
}

