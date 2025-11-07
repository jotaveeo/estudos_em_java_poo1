package quintaQ;

class Funcionario {
    private int numeroFuncionario;
    private String nomeFuncionario;
    private String cargo;
    private double salario;

    public Funcionario(int numeroFuncionario, String nomeFuncionario, String cargo, double salario) {
        this.numeroFuncionario = numeroFuncionario;
        this.nomeFuncionario = nomeFuncionario;
        this.cargo = cargo;
        this.salario = salario;
    }

    // getters
    public int getNumeroFuncionario() {
        return numeroFuncionario;
    }

    public String getNomeFuncionario() {
        return nomeFuncionario;
    }

    public String getCargo() {
        return cargo;
    }

    public double getSalario() {
        return salario;
    }

    // setters
    public void setNumeroFuncionario(int numeroFuncionario) {
        this.numeroFuncionario = numeroFuncionario;
    }

    public void setNomeFuncionario(String nomeFuncionario) {
        this.nomeFuncionario = nomeFuncionario;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }
}