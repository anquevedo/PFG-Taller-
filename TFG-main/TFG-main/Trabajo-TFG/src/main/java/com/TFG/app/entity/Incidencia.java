package com.TFG.app.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
public class Incidencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String numeroIncidencia;
    private String tipo;
    private float tiempoEst;
    private String descripcion;
    private boolean solucionada;


    public Incidencia (){

    }

    public Incidencia(String numeroIncidencia, String tipo, float tiempoEst, String descripcion, boolean solucionada) {
        this.numeroIncidencia = numeroIncidencia;
        this.tipo = tipo;
        this.tiempoEst = tiempoEst;
        this.descripcion = descripcion;
        this.solucionada = solucionada;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumeroIncidencia() {
        return numeroIncidencia;
    }

    public void setNumeroIncidencia(String numeroIncidencia) {
        this.numeroIncidencia = numeroIncidencia;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public float getTiempoEst() {
        return tiempoEst;
    }

    public void setTiempoEst(float tiempoEst) {
        this.tiempoEst = tiempoEst;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isSolucionada() {
        return solucionada;
    }

    public void setSolucionada(boolean solucionada) {
        this.solucionada = solucionada;
    }
}
