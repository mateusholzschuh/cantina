/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author Mateus
 */
public class Produto {
    private Integer codigo;
    private String nome;
    private Double preco;
    private TipoProduto tipo;

    public Produto() {
    }

    public Produto(Integer codigo, String nome, Double preco, TipoProduto tipo) {
        this.codigo = codigo;
        this.nome = nome;
        this.preco = preco;
        this.tipo = tipo;
    }

    public TipoProduto getTipo() {
        return tipo;
    }

    public void setTipo(TipoProduto tipo) {
        this.tipo = tipo;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }
    
    
}
