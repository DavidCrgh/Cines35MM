package com.davidcr.cines35mm.dominio;
import android.support.annotation.NonNull;

import java.util.concurrent.atomic.AtomicInteger;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class User  {
    private static  AtomicInteger count = new AtomicInteger(0);
    public int id;
    public String username ;
    public String email;
    public String password;
    public boolean bloqueado;
    public boolean Admin;

    public boolean isBloqueado() {
        return bloqueado;
    }

    public void setBloqueado(boolean bloqueado) {
        this.bloqueado = bloqueado;
    }

    public boolean isAdmin() {
        return Admin;
    }

    public void setAdmin(boolean admin) {
        Admin = admin;
    }

    public int getId() {
        return id;
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

    public User(String username, String email, String password) {
        this.id = count.incrementAndGet();
        this.username = username;
        this.email = email;
        this.password = password;
        this.bloqueado = false;
        this.Admin = true;
    }
}
