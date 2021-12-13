package com.TFG.app.dto;

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
        private int año;
        @Min(0)
        private float precio;


        public CocheDto() {
        }

        public CocheDto(@NotBlank String matricula, @NotBlank String marca, @NotBlank String modelo, @NotBlank int año, @Min(0) float precio) {
            this.matricula = matricula;
            this.marca = marca;
            this.modelo = modelo;
            this.año = año;
            this.precio = precio;
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

        public int getAño() {
            return año;
        }

        public void setAño(int año) {
            this.año = año;
        }

        public Float getPrecio() {
            return precio;
        }

        public void setPrecio(Float precio) {
            this.precio = precio;
        }
    }
