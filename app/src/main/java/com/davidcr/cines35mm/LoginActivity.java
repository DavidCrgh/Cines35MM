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

import com.google.android.gms.common.SignInButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //Para saltar directo al inicio, quitar despues //TODO
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        ///////////////////////////////////////////////////////////////
        /*firebaseAuth = FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser() != null){

        }
        progressDialog = new ProgressDialog(this);
        Ingresar = (Button) findViewById(R.id.button);
        email = (EditText) findViewById(R.id.editText);
        contrasena = (EditText) findViewById(R.id.editText2);
        Signin = (TextView) findViewById(R.id.textView3);
        Ingresar.setOnClickListener(this);
        Signin.setOnClickListener(this);*/
    }

    public void LogIn(){
        String con = contrasena.getText().toString().trim();
        String em  = email.getText().toString().trim();

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
                        if (task.isSuccessful()){
                            finish();
                            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                        }
                        else {
                            Toast.makeText(LoginActivity.this,"Ingrese de nuevo sus credenciales",Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
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

