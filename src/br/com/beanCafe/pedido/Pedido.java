package br.com.beanCafe.pedido;

import br.com.beanCafe.cliente.Cliente;
import br.com.beanCafe.itemPedido.ItemPedido;

import java.text.DecimalFormat;

public class Pedido {
    private int numeroPedido;
    private Cliente cliente;
    private ItemPedido[] itens;
    private int index;
    private String status;

    public Pedido(int numeroPedido, Cliente cliente) {
        this.numeroPedido = numeroPedido;
        this.cliente = cliente;
        this.itens = new ItemPedido[20];
        this.index = 0;
        this.status = "ABERTO";
    }

    public int getNumeroPedido() {
        return numeroPedido;
    }

    public void setNumeroPedido(int numeroPedido) {
        this.numeroPedido = numeroPedido;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public ItemPedido[] getItens() {
        return itens;
    }

    public void setItens(ItemPedido[] itens) {
        this.itens = itens;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void adicionarItem(ItemPedido item){
        if (isFull()){
            System.out.println("Não foi possível adicionar, carrinho cheio");
        }
        else {
            itens[index] = item;
            index++;
        }
    }

    public double calcularTotal(){
        double total = 0;
        for(int i = 0; i < index; i++){
            total += itens[i].calcularTotal();
        }
        return total;
    }

    public String getDados(){
        DecimalFormat df = new DecimalFormat("R$0.00");
        return "\nNumero pedido: " + numeroPedido + "\nCliente: " + cliente.getDados() + "\n\n Item   Preço   Quantidade\n" + getItensPedido() + "\nSubtotal: " + df.format(calcularTotal());
    }

    public String getItensPedido(){
        String lista = "";
        for(int i = 0; i < index; i++){
            lista += "• " + itens[i].getNomeProduto() + "   R$" +itens[i].getPrecoUnitario() + "    " + itens[i].getQuantidade() + " x\n";
        }
        return lista;
    }

    public boolean isFull(){
        return index == itens.length;
    }
}
