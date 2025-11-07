package terceiraQ;

class Compra {
    private int numeroProduto;
    private int numeroCliente;
    private int quantidade;
    private double valorTotal;

    public Compra(int numeroProduto, int numeroCliente, int quantidade) {
        this.numeroProduto = numeroProduto;
        this.numeroCliente = numeroCliente;
        this.quantidade = quantidade;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    // Calcula o valor total da compra
    public void calcularValorTotal(Produto produto, Cliente cliente) {
        double precoBase = produto.getPreco();
        double precoComDesconto = precoBase * (1 - produto.getDesconto());
        precoComDesconto *= (1 - cliente.getDescontoAdicional());

        valorTotal = quantidade * precoComDesconto;
    }
}