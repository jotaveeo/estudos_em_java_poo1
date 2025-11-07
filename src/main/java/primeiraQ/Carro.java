package primeiraQ;

public class Carro {
    private String placa;
    private int anoFabricacao;

    public Carro(String placa, int anoFabricacao) {
        this.placa = placa;
        this.anoFabricacao = anoFabricacao;
    }

    public String getPlaca() { return placa; }
    public int getAnoFabricacao() { return anoFabricacao; }

    public double calcularImposto(int anoAtual) {
        int anosDeUso = anoAtual - anoFabricacao;
        if (anosDeUso < 0) return 0.0;
        if (anosDeUso == 0) return 500.0;
        if (anosDeUso >= 10) return 0.0;
        double imposto = 500.0 - (anosDeUso * 100.0);
        return (imposto < 100.0) ? 100.0 : imposto;
    }
}
