package segundaQ;

public class Pessoa {
    private String nome;
    private int idade;

    public Pessoa(String nome, int idade) {
        this.nome = nome;
        this.idade = idade;
    }

    public String getNome() { return nome; }
    public int getIdade() { return idade; }

    public int getIdadeEmMeses() {
        return idade * 12;
    }

    public int getIdadeEm2050(int anoAtual) {
        return idade + (2050 - anoAtual);
    }
}
