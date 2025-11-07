package terceiraQ;

class Produto {
    private int numero;
    private double preco;

    public Produto(int numero, double preco) {
        this.numero = numero;
        this.preco = preco;
    }

    public int getNumero() {
        return numero;
    }

    public double getPreco() {
        return preco;
    }

    // Desconto do produto
    public double getDesconto() {
        if (preco > 100.0) {
            return 0.15; // 15%
        } else {
            return 0.05; // 5%
        }
    }
}