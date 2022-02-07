package com.TFG.app.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.Id;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.Date;

@Entity
public class Incidencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String tipo;
    private String descripcion;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd" , timezone = "GMT+8")
    private Date dateInicio;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd" , timezone = "GMT+8")
    private Date dateFin;

    private String estado;


    public Incidencia (){

    }

    public Incidencia(String tipo, String descripcion, Date dateInicio, Date dateFin, String estado) {
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.dateInicio = dateInicio;
        this.dateFin = dateFin;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getDateInicio() {
        return dateInicio;
    }

    public void setDateInicio(Date dateInicio) {
        this.dateInicio = dateInicio;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
