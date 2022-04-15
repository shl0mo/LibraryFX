//Padrão de projeto - Builder
package com.mycompany.bibliofx;

public final class Livro {
    private String nome;
    private String autor;
    private int ano;
    
    public String getNome () {
        return this.nome;
    }
    
    public String getAutor () {
        return this.autor;
    }
    
    public int getAno () {
        return this.ano = ano;
    }
    
    static class LivroBuilder {
        
        protected Livro livro;
        
        LivroBuilder() {
            this.livro = new Livro();
        }
        
        public Livro build() {
            return this.livro;
        }
        
        public LivroBuilder buildNome(String nome) {
            this.livro.nome = nome;
            return this;
        }
        
        public LivroBuilder buildAutor(String autor) {
            this.livro.autor = autor;
            return this;
        }
        
        public LivroBuilder buildAno(int ano) {
            this.livro.ano = ano;
            return this;
        }
    }
}