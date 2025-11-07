package quintaQ;

class Dependente {
    private Funcionario funcionario; // referência ao objeto Funcionario
    private String nomeDependente;

    public Dependente(Funcionario funcionario, String nomeDependente) {
        this.funcionario = funcionario;
        this.nomeDependente = nomeDependente;
    }

    // getters
    public Funcionario getFuncionario() {
        return funcionario;
    }

    public String getNomeDependente() {
        return nomeDependente;
    }

    // setters
    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public void setNomeDependente(String nomeDependente) {
        this.nomeDependente = nomeDependente;
    }
}