package com.mycompany.bibliofx;

import java.io.IOException;
import java.sql.*;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javax.swing.JOptionPane;

import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.*;


public class UserEmprestaLivroController implements Initializable {
    
    @FXML
    private TableView <Livro> tabela;
    
    @FXML
    private TableColumn <Livro, String> colunaNome;
    
    @FXML
    private TableColumn <Livro, String> colunaAutor;
    
    @FXML
    private TableColumn <Livro, Integer> colunaAno;
    
    private ObservableList<Livro> livros = FXCollections.observableArrayList();
    
    public void exibe_livros_emprestar () {
        Connection conn = ConectarDB.conn;
        try {
            Statement statement = conn.createStatement();
            Statement statement_2 = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet livros = statement.executeQuery("SELECT * FROM livros");
            while (livros.next()) {
                boolean emprestou = false;
                ResultSet usuarios_livros = statement_2.executeQuery("SELECT * FROM usuarios_livros WHERE id_usuario = " + Sessao.id + ";");
                while (usuarios_livros.next()) {
                    if (Integer.parseInt(livros.getString(1)) == Integer.parseInt(usuarios_livros.getString(3))) {
                        emprestou = true;
                    }
                }
                if (!emprestou) {
                    this.livros.add(new Livro.LivroBuilder().buildNome(livros.getString(2)).buildAutor(livros.getString(3)).buildAno(Integer.parseInt(livros.getString(4))).build());
                }
                usuarios_livros.first();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        colunaNome.setCellValueFactory(new PropertyValueFactory<Livro, String>("nome"));
        colunaAutor.setCellValueFactory(new PropertyValueFactory<Livro, String>("autor"));
        colunaAno.setCellValueFactory(new PropertyValueFactory<Livro, Integer>("ano"));
        
        tabela.setItems(this.livros);
    }
    
    @FXML
    public void handleEmprestarLivro() throws IOException {
        Livro livro = this.tabela.getSelectionModel().getSelectedItem();
        if (livro == null) {
            JOptionPane.showMessageDialog(null, "Selecione um livro para emprestar");
            return;            
        }
        Connection conn = ConectarDB.conn;
        try {
            Statement statement = conn.createStatement();
            Statement statement_2 = conn.createStatement();
            ResultSet result = statement.executeQuery("SELECT id FROM livros WHERE nome = '" + livro.getNome() + "' AND autor = '" + livro.getAutor() + "' AND ano = '" + livro.getAno() + "';");
            int id_livro = 0;
            while (result.next()) {
                id_livro = Integer.parseInt(result.getString(1));
            }
            statement_2.execute("INSERT INTO usuarios_livros(id_usuario, id_livro) VALUES ('" + String.valueOf(Sessao.id) + "', '" + String.valueOf(id_livro) + "');");
            Observable.update();
            exibe_livros_emprestar();
        } catch (Exception e) {
            e.printStackTrace();
        }
        JOptionPane.showMessageDialog(null, "Livro emprestado");
    }
    
    
    @FXML
    public void handleVoltar() throws IOException {
        App.setRoot("mainUser");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Observable.register(this.livros);
        exibe_livros_emprestar();
    }
}
