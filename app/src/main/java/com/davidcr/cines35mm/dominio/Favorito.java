package com.davidcr.cines35mm.dominio;

public class Favorito {
    private String usuario_alias;
    private String pelicula_id;

    public String getUsuario_alias() {
        return usuario_alias;
    }

    public void setUsuario_alias(String usuario_alias) {
        this.usuario_alias = usuario_alias;
    }

    public String getPelicula_id() {
        return pelicula_id;
    }

    public void setPelicula_id(String pelicula_id) {
        this.pelicula_id = pelicula_id;
    }

    @Override
    public String toString() {
        return "Favorito{" +
                "usuario_alias='" + usuario_alias + '\'' +
                ", pelicula_id=" + pelicula_id +
                '}';
    }

    public Favorito(String usuario_alias, String pelicula_id) {
        this.usuario_alias = usuario_alias;
        this.pelicula_id = pelicula_id;
    }
}
