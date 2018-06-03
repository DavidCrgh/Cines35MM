package com.davidcr.cines35mm.dominio;

public class Favorito {
    private String usuario_alias;
    private Integer pelicula_id;

    public String getUsuario_alias() {
        return usuario_alias;
    }

    public void setUsuario_alias(String usuario_alias) {
        this.usuario_alias = usuario_alias;
    }

    public Integer getPelicula_id() {
        return pelicula_id;
    }

    public void setPelicula_id(Integer pelicula_id) {
        this.pelicula_id = pelicula_id;
    }

    @Override
    public String toString() {
        return "Favorito{" +
                "usuario_alias='" + usuario_alias + '\'' +
                ", pelicula_id=" + pelicula_id +
                '}';
    }
}
