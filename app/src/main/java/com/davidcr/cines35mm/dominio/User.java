package com.davidcr.cines35mm.dominio;
import android.support.annotation.NonNull;

import java.util.concurrent.atomic.AtomicInteger;

import com.davidcr.cines35mm.SingIn;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class User  {
    private static  AtomicInteger count = new AtomicInteger(0);
    public String username ;
    public String email;
    public String password;
    public boolean bloqueado;
    public String admin;

    public boolean isBloqueado() {
        return bloqueado;
    }

    public void setBloqueado(boolean bloqueado) {
        this.bloqueado = bloqueado;
    }

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        admin = admin;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User(String username, String email, String password, String admin) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.bloqueado = false;
        this.admin = admin;
    }
}
