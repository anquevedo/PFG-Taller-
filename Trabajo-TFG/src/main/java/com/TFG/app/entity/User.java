package com.TFG.app.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "users")
public class User implements Serializable {

    //Generado con el plugin generateserialversionuid
    private static final long serialVersionUID = 4616887219577529750L;
    @Id
    @Column(nullable = false, unique = true)
    private Long id;

    @Column(length = 50)
    private String name;

    private String surname;

    @Column(name= "mail", nullable = false, length = 50, unique = true)
    private String email;

    @Column(name= "password", nullable = false, length = 50, unique = true)
    private String password;





    private boolean enabled;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPassword() { return password; }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    public void setPassword(String password) {this.password = password; }
}

