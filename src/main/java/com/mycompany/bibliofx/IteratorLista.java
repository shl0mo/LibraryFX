//Padrão de projeto - Iterator
package com.mycompany.bibliofx;

import javafx.collections.ObservableList;

public final class IteratorLista implements Container {       
    
    private ObservableList<Livro> lista;
        
    IteratorLista (ObservableList<Livro> lista) {
        this.lista = lista;
    }
    
    public static void clearIterator (ObservableList<Livro> lista) {
        IteratorLista iterator = new IteratorLista(lista);
        Iterator iter = iterator.getIterator();
        while (iter.hasNext()) {
            lista.remove(0);
        }
    }
        
    @Override
    public Iterator getIterator() {
       return new NovoIterator();
    }

    private class NovoIterator implements Iterator {
        
        int i;
        
        @Override
        public boolean hasNext() {
            if(i < lista.size()){
                return true;
            }
            return false;
        }

        @Override
        public Object next() {

            if(this.hasNext()){
                return lista.get(i++);
            }
            return null;
        }		
    }
}