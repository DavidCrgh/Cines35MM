package com.davidcr.cines35mm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //Para saltar directo al inicio, quitar despues //TODO
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        ///////////////////////////////////////////////////////////////
    }
}
