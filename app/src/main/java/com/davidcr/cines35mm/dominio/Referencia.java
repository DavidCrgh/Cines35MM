package com.davidcr.cines35mm.dominio;

import java.util.Date;

public class Referencia {
    private String usuario_alias;
    private int pelicula_id;
    private String cometario;
    private int calificacion;
    private Date fecha;


    public String getUsuario_alias() {
        return usuario_alias;
    }

    public void setUsuario_alias(String usuario_alias) {
        this.usuario_alias = usuario_alias;
    }

    public int getPelicula_id() {
        return pelicula_id;
    }

    public void setPelicula_id(int pelicula_id) {
        this.pelicula_id = pelicula_id;
    }

    public String getCometario() {
        return cometario;
    }

    public void setCometario(String cometario) {
        this.cometario = cometario;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}
