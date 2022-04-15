package com.mycompany.bibliofx;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="usuarios_livros")
public class Usuarios_Livros implements Serializable {
    @Id
    @GeneratedValue
    @Column(name="idUsuario_Livros")
    private Integer idUsuario_Livros;
    @ManyToMany(name="id_usuario")
    @JoinColumn(name = "id_usuario", referencedColumnName = "idUsuario")
    private Integer id_usuario;
    @ManyToMany(name="id_livro")
    @JoinColumn(name = "id_livro", referencedColumnName = "idLivro")
    private Integer id_livro;
}