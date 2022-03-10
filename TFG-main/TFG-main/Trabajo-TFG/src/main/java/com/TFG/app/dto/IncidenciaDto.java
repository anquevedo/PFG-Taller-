package com.TFG.app.dto;

import javax.persistence.Column;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class IncidenciaDto {


    @NotBlank
    private String descripcion;

    @NotBlank
    private Date dateInicio;

    private Date dateFin;

    @NotNull
    private String estado;

    @NotNull
    private String nombreUsuario;

    @NotNull
    private Boolean seleccionada;

    private String nombreMecanico;

    public IncidenciaDto() {
        }

    public IncidenciaDto(@NotBlank String descripcion, @NotBlank Date dateInicio, Date dateFin, @NotNull String estado,
                         @NotNull String nombreUsuario,  @NotNull Boolean seleccionada, String nombreMecanico) {
        this.descripcion = descripcion;
        this.dateInicio = dateInicio;
        this.dateFin = dateFin;
        this.estado = estado;
        this.nombreUsuario=nombreUsuario;
        this.seleccionada=seleccionada;
        this.nombreMecanico=nombreMecanico;
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

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;


    }

    public Boolean getSeleccionada() {
        return seleccionada;
    }

    public void setSeleccionada(Boolean seleccionada) {
        this.seleccionada = seleccionada;
    }

    public String getNombreMecanico() {
        return nombreMecanico;
    }

    public void setNombreMecanico(String nombreMecanico) {
        this.nombreMecanico = nombreMecanico;
    }
}

