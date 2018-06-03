package com.davidcr.cines35mm.dominio;

public class PeliculaSimple {
    private String titulo;
    private String anno;

    public PeliculaSimple(String titulo, String anno) {
        this.titulo = titulo;
        this.anno = anno;
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
}
