package com.TFG.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "incidencia")
public class Incidencia implements Serializable {

        private static final long serialVersionUID = -8665860456782118128L;
        //Generado con el plugin generateserialversionuid

        @Id
        @Column(name= "Id", updatable = false, nullable = false, unique = true, length = 9)
        private Long id;

        @Column(length = 50, nullable = false)
        private String Tipo;

        @Column(length = 50, nullable = false)
        private String tiempoEst;

        @Column(name= "descripcion", nullable = false, length = 200, unique = true)
        private String desc;

        private boolean solucionada;


    public Long getId() {
        return id;
    }

    public String getTipo() {
        return Tipo;
    }

    public String getTiempoEst() {
        return tiempoEst;
    }

    public String getDesc() {
        return desc;
    }

    public boolean getSolucionada() {
        return solucionada;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTipo(String tipo) {
        Tipo = tipo;
    }

    public void setTiempoEst(String tiempoEst) {
        this.tiempoEst = tiempoEst;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setSolucionada(boolean solucionada) {
        this.solucionada = solucionada;
    }
}
