package com.davidcr.cines35mm.dominio;

public class PeliculaSimple {
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
}
