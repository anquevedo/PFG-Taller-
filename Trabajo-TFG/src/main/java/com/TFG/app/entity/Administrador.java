package com.TFG.app.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "admistrador")
public class Administrador implements Serializable{

    private static final long serialVersionUID = -5543972741600709171L;

    @Id
    @Column (updatable = false, nullable = false, unique = true)
    private Long dni;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(name= "email", nullable = false, length = 50, unique = true)
    private String email;

    @Column (nullable = false)
    private String password;



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
}
