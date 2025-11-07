package quartaQ;

class Matricula {
    private int anoLetivo;
    private String serie;
    private Aluno aluno; // referência
    private Disciplina disciplina; // referência
    private double[] notas = new double[4]; // 0..3 => 1º..4º bimestres

    public Matricula(int anoLetivo, String serie, Aluno aluno, Disciplina disciplina) {
        this.anoLetivo = anoLetivo;
        this.serie = serie;
        this.aluno = aluno;
        this.disciplina = disciplina;
        // por regra: inicia com 0.0
        for (int i = 0; i < 4; i++)
            notas[i] = 0.0;
    }

    public int getAnoLetivo() {
        return anoLetivo;
    }

    public String getSerie() {
        return serie;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public double getNota(int bimestre1a4) {
        return notas[bimestre1a4 - 1];
    }

    public void setNota(int bimestre1a4, double valor) {
        notas[bimestre1a4 - 1] = valor;
    }

    public double mediaFinal() {
        if (disciplina.isPratica()) {
            // 2º e 4º bimestres peso 2; 1º e 3º peso 1 → pesos [1,2,1,2]
            double soma = notas[0] * 1 + notas[1] * 2 + notas[2] * 1 + notas[3] * 2;
            double pesos = 1 + 2 + 1 + 2;
            return soma / pesos;
        } else {
            // média aritmética
            return (notas[0] + notas[1] + notas[2] + notas[3]) / 4.0;
        }
    }
}
