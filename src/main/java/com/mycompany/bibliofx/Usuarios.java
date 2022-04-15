package com.mycompany.bibliofx;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="usuarios")
public class Usuarios implements Serializable {
    @Id
    @GeneratedValue
    @Column(name="idUsuario")
    private Integer idUsuario;
    @Column(name="usuario")
    private String usuario;
    @Column(name="senha")
    private String senha;
}