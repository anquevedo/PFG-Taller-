package com.TFG.app.dto;

import javax.persistence.Column;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class IncidenciaDto {

        @NotBlank
        private String numeroIncidencia;
        @NotBlank
        private String tipo;
        @Min(0)
        private float tiempoEst;
        @NotBlank
        private String descripcion;
        @NotNull
        @Column(nullable = false)
        private boolean solucionada;


        public IncidenciaDto() {
        }

    public IncidenciaDto(@NotBlank String numeroIncidencia, @NotBlank String tipo, @Min(0) float tiempoEst, @NotBlank String descripcion, @NotNull boolean solucionada) {
        this.numeroIncidencia = numeroIncidencia;
        this.tipo = tipo;
        this.tiempoEst = tiempoEst;
        this.descripcion = descripcion;
        this.solucionada = solucionada;
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

    public Float getTiempoEst() {
        return tiempoEst;
    }

    public void setTiempoEst(Float tiempoEst) {
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

