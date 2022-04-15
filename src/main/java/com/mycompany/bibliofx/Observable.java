//Padrão de projeto - Observable
package com.mycompany.bibliofx;

import java.util.List;
import javafx.collections.ObservableList;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;


public class Observable {
    private static List<ObservableList<Livro>> lista_livros = FXCollections.observableArrayList();
    
    public static void register (ObservableList<Livro> livros) {
        lista_livros.add(livros);
    }
    
    public static void update () {
        for (ObservableList<Livro> livro : lista_livros) {
            IteratorLista.clearIterator(livro);
        }
    }
    
    public static void endSession () {
        lista_livros.clear();
    }
}
