package br.com.beanCafe.itemPedido;

public class ItemPedido {
    private String nomeProduto;
    private double precoUnitario;
    private int quantidade;

    public ItemPedido(String nomeProduto, double precoUnitario, int quantidade) {
        this.nomeProduto = nomeProduto;
        this.precoUnitario = precoUnitario;
        this.quantidade = quantidade;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public double getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(double precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double calcularTotal(){
        return this.precoUnitario * this.quantidade;
    }

    public String getDados(){
        return "\nProduto: " + nomeProduto + "\nQuantidade: " + quantidade + "\nPreço Unitário: " + precoUnitario + "\nSubtotal: " + calcularTotal();
    }
}
