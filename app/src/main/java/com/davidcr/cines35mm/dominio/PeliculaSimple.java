package com.davidcr.cines35mm.dominio;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class PeliculaSimple implements Serializable{
    private String llave;
    private Pelicula pelicula;

    public PeliculaSimple(String llave, Pelicula pelicula){
        this.llave = llave;
        this.pelicula = pelicula;
    }

    public String getLlave() {
        return llave;
    }

    public void setLlave(String llave) {
        this.llave = llave;
    }

    public String getTitulo() {
        return pelicula.getTitulo();
    }

    public void setTitulo(String titulo) {
        this.pelicula.setTitulo(titulo);
    }

    public String getAnno() {
        return pelicula.getAnno();
    }

    public void setAnno(String anno) {
        this.pelicula.setAnno(anno);
    }

    public Pelicula getPelicula() {
        return pelicula;
    }

    public void setPelicula(Pelicula pelicula) {
        this.pelicula = pelicula;
    }
}
