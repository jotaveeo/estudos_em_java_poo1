package quartaQ;

class DisciplinaPratica extends Disciplina {
    private int cargaHorariaPratica;

    public DisciplinaPratica(int codigo, String nome, int chGeral, int chPratica) {
        super(codigo, nome, chGeral);
        this.cargaHorariaPratica = chPratica;
    }

    @Override
    public boolean isPratica() {
        return true;
    }

    @Override
    public int getCargaHorariaPratica() {
        return cargaHorariaPratica;
    }
}