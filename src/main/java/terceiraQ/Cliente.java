package terceiraQ;

class Cliente {
    private int numero;
    private String nome;
    private char sexo;

    public Cliente(int numero, String nome, char sexo) {
        this.numero = numero;
        this.nome = nome;
        this.sexo = sexo;
    }

    public int getNumero() {
        return numero;
    }

    public String getNome() {
        return nome;
    }

    public char getSexo() {
        return sexo;
    }

    // Desconto adicional se for feminino
    public double getDescontoAdicional() {
        if (sexo == 'F' || sexo == 'f') {
            return 0.05; // 5%
        }
        return 0.0;
    }
}