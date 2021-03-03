package com.TFG.app.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "mecanico")
public class Mecanico implements Serializable{

    private static final long serialVersionUID = 2367115979521893315L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (updatable = false, nullable = false)
    private long dni;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(length = 50, nullable = false)
    private String surname;

    @Column(name= "mail", nullable = false, length = 50, unique = true)
    private String email;

    @Column (nullable = false)
    private String password;

    private boolean enabled;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public long getDni() { return dni; }

    public void setDni(long dni) { this.dni = dni; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
