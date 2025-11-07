package quartaQ;

class Disciplina {
    private int codigo;
    private String nome;
    private int cargaHorariaGeral;

    public Disciplina(int codigo, String nome, int chGeral) {
        this.codigo = codigo;
        this.nome = nome;
        this.cargaHorariaGeral = chGeral;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public int getCargaHorariaGeral() {
        return cargaHorariaGeral;
    }

    public boolean isPratica() {
        return false;
    }

    public int getCargaHorariaPratica() {
        return 0;
    }
}