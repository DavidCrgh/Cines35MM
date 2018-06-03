package com.davidcr.cines35mm.adapters;

import java.util.ArrayList;

public class Pelicula {
    private String titulo;
    private String anno;
    private String sinopsis;
    private ArrayList<String> keywords;
    private ArrayList<String> generos;
    private ArrayList<String> directores;
    private ArrayList<String> actores;

    public Pelicula(){

    }

    public Pelicula(
            String titulo,
            String anno,
            String sinopsis,
            ArrayList<String> keywords,
            ArrayList<String> generos,
            ArrayList<String> directores,
            ArrayList<String> actores) {
        this.titulo = titulo;
        this.anno = anno;
        this.sinopsis = sinopsis;
        this.keywords = keywords;
        this.generos = generos;
        this.directores = directores;
        this.actores = actores;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAnno() {
        return anno;
    }

    public void setAnno(String anno) {
        this.anno = anno;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public ArrayList<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(ArrayList<String> keywords) {
        this.keywords = keywords;
    }

    public ArrayList<String> getGeneros() {
        return generos;
    }

    public void setGeneros(ArrayList<String> generos) {
        this.generos = generos;
    }

    public ArrayList<String> getDirectores() {
        return directores;
    }

    public void setDirectores(ArrayList<String> directores) {
        this.directores = directores;
    }

    public ArrayList<String> getActores() {
        return actores;
    }

    public void setActores(ArrayList<String> actores) {
        this.actores = actores;
    }
}
