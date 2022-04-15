package com.mycompany.bibliofx;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="livros")
public class Livros implements Serializable {
    @Id
    @GeneratedValue
    @Column(name="idLivros")
    private Integer idLivros;
    @Column(name="nome")
    private String nome;
    @Column(name="autor")
    private String autor;
    @Column(name="ano")
    private Integer ano;
}