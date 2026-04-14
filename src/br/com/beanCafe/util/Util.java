package br.com.beanCafe.util;

import br.com.beanCafe.cliente.Cliente;
import br.com.beanCafe.itemPedido.ItemPedido;
import br.com.beanCafe.pedido.Pedido;

import static javax.swing.JOptionPane. *;
import static java.lang.Integer.parseInt;
import static java.lang.Double.parseDouble;
import java.text.DecimalFormat;

public class Util {
    private final Pedido[] pedidos = new  Pedido[100];
    private int index = 0;

    public void menu(){
        int opcao;
        do {

            String aux = """
                    \nBean & Code Café
                    1 - Registrar novo pedido
                    2 - Adicionar item a um pedido
                    3 - Finalizar pedido
                    4 - Listar pedidos em aberto
                    5 - Exibir faturamento do dia
                    6 - Sair
                    """;
            opcao =  parseInt(showInputDialog(aux));
            switch (opcao) {
                case 1 -> registrar();
                case 2 -> adicionar();
                case 3 -> finalizar ();
                case 4 -> listar();
                case 5 -> faturamentoDia();
                case 6 -> sair();
                default -> showMessageDialog(null,"Entrada invalida");
            }

        }while (opcao != 6);
    }

    private Pedido buscarPedido(int id){
        for(int i = 0; i < index; i++){
            if (id == pedidos[i].getNumeroPedido()){
                return pedidos[i];
            }
        }
        return null;
    }

    private void registrar(){
        int numeroPedido = parseInt(showInputDialog("Digite o numero do pedido: "));

        if (buscarPedido(numeroPedido) != null) {
            showMessageDialog(null,"ERRO 01 - Pedido já existente!");
        }else {
            String nomeCliente = showInputDialog("Digite o nome do Cliente: ");
            String cpfCliente = showInputDialog("Digite o CPF do cliente: ");

            Cliente cliente = new Cliente(nomeCliente,cpfCliente);
            Pedido pedido = new Pedido(numeroPedido,cliente);

            // Armazena o pedido no array e incrementa o index
            pedidos[index] = pedido;
            index++;

            showMessageDialog(null,"Pedido registrado com sucesso!");
        }
    }

    private void adicionar(){
        int numeroPedido = parseInt(showInputDialog("Digite o numero do pedido: "));
        Pedido aux = buscarPedido(numeroPedido);

        if (aux == null) {
            showMessageDialog(null,"ERRO 02 - Pedido não existente!" );
        } else if (aux.getStatus().equalsIgnoreCase("FINALIZADO")) {
            showMessageDialog(null,"Pedido finalizado não é possivel adicionar itens." );
        }else {
            String nomeProduto = showInputDialog("Digite o nome do produto: ");
            double valorProduto = parseDouble(showInputDialog("Digite o valor do produto: "));
            int quantidadeProduto = parseInt(showInputDialog("Digite o quantidade do produto: "));

            // Criando produto e adicionando ao carrinho
            ItemPedido itemPedido = new ItemPedido(nomeProduto,valorProduto,quantidadeProduto);
            aux.adicionarItem(itemPedido);
            showMessageDialog(null,"Pedido adicionado com sucesso!");
        }
    }

    private void finalizar(){
        int numeroPedido = parseInt(showInputDialog("Informe o numero do pedido: "));
        Pedido aux = buscarPedido(numeroPedido);

        if (aux == null) {
            showMessageDialog(null,"ERRO 02 - Pedido não existente!");
        } else if (aux.getStatus().equalsIgnoreCase("FINALIZADO")) {
            showMessageDialog(null,"Não foi possível finalizar pois o pedido já está finalizado!");
        }else  {
            aux.setStatus("FINALIZADO");
            showMessageDialog(null,"Dados do pedido finalizado:" + aux.getDados());
        }
    }

    private void listar(){
        int aux = 0;
        String listaPedidosAbertos = "";
        for(int i = 0; i < index; i++){
            if(pedidos[i].getStatus().equalsIgnoreCase("ABERTO")){
                listaPedidosAbertos += pedidos[i].getDados();
                aux++;
            }
        }
        if(aux == 0){
            showMessageDialog(null, "Nenhum pedido em aberto no momento.");
        }else  {
            showMessageDialog(null,"Pedidos em aberto:\n" + listaPedidosAbertos);
        }
    }

    private void faturamentoDia(){
        DecimalFormat df = new DecimalFormat("R$0.00");
        int aux = 0;
        double faturamento = 0;
        for(int i = 0; i < index; i++){
            if(pedidos[i].getStatus().equalsIgnoreCase("FINALIZADO")){
                faturamento += pedidos[i].calcularTotal();
                aux++;
            }
        }
        if(aux == 0){
            showMessageDialog(null,"R$ 0,00.");
        }else{
            showMessageDialog(null,df.format(faturamento));
        }
    }

    private void sair(){
        showMessageDialog(null,"Sistema Bean & Code encerrado. Até logo!");
    }
}
