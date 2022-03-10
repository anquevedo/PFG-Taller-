package com.TFG.app.dto;

import com.TFG.app.Security.entity.Usuario;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

    public class CocheDto {

        @NotBlank
        private String matricula;
        @NotBlank
        private String marca;
        @NotBlank
        private String modelo;
        @NotBlank
        private int anio;
        @Min(0)
        private float precio;
        @NotBlank
        private String nombreUsuario;



        public CocheDto() {
        }

        public CocheDto(@NotBlank String matricula, @NotBlank String marca, @NotBlank String modelo, @NotBlank int anio, @Min(0) float precio, @NotBlank String nombreUsuario) {
            this.matricula = matricula;
            this.marca = marca;
            this.modelo = modelo;
            this.anio = anio;
            this.precio = precio;
            this.nombreUsuario= nombreUsuario;
        }

        public String getMatricula() {
            return matricula;
        }

        public void setMatricula(String matricula) {
            this.matricula = matricula;
        }

        public String getMarca() {
            return marca;
        }

        public void setMarca(String marca) {
            this.marca = marca;
        }

        public String getModelo() {
            return modelo;
        }

        public void setModelo(String modelo) {
            this.modelo = modelo;
        }

        public int getAnio() { return anio; }

        public void setAnio(int anio) {
            this.anio = anio;
        }

        public Float getPrecio() {
            return precio;
        }

        public void setPrecio(Float precio) {
            this.precio = precio;
        }

        public String getNombreUsuario() {
            return nombreUsuario;
        }

        public void setNombreUsuario(String nombreUsuario) {
            this.nombreUsuario = nombreUsuario;
        }
    }
