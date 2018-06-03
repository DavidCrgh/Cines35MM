package com.davidcr.cines35mm;
import java.util.concurrent.atomic.AtomicInteger;

public class User {
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

    public User(String username, String email, String password,boolean bloqueado, boolean Admin) {
        this.id = count.incrementAndGet();
        this.username = username;
        this.email = email;
        this.password = password;
        this.bloqueado = bloqueado;
        this.Admin = Admin;
    }
}
