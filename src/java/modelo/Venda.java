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
public class Venda {
    private Integer codigo;
    private String data;
    private Produto produto;
    private Integer quantidade;
    private Double valorTotal;
    private Cliente cliente;

    public Venda() {
    }

    public Venda(Integer codigo, String data, Produto produto, Integer quantidade, Cliente cliente) {
        this.codigo = codigo;
        this.data = data;
        this.produto = produto;
        this.quantidade = quantidade;
        this.cliente = cliente;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Double getValorTotal() {
        return this.quantidade * this.produto.getPreco();
    }
    
    
}
