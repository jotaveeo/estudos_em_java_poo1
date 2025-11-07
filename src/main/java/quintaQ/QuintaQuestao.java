package quintaQ;

import java.util.Scanner;

public class QuintaQuestao {
    // limites (pode ajustar)
    private static final int MAX_FUNCIONARIOS = 50;
    private static final int MAX_DEPENDENTES = 200;

    // “vetores” e contadores
    private static final Funcionario[] funcionarios = new Funcionario[MAX_FUNCIONARIOS];
    private static int qtdFuncionarios = 0;

    private static final Dependente[] dependentes = new Dependente[MAX_DEPENDENTES];
    private static int qtdDependentes = 0;

    public static void run(Scanner sc) {
        while (true) {
            System.out.println("\n=== Departamento Pessoal ===");
            System.out.println("1) Cadastrar funcionário (e seus dependentes)");
            System.out.println("2) Mostrar bônus mensal de cada funcionário");
            System.out.println("3) Excluir funcionário (e seus dependentes)");
            System.out.println("4) Alterar salário de um funcionário");
            System.out.println("0) Voltar");
            System.out.print("Opção: ");

            int op = lerInt(sc);

            switch (op) {
                case 1:
                    cadastrarFuncionario(sc);
                    break;
                case 2:
                    mostrarBonus();
                    break;
                case 3:
                    excluirFuncionario(sc);
                    break;
                case 4:
                    alterarSalario(sc);
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    /* ---------- Opção 1 ---------- */
    private static void cadastrarFuncionario(Scanner sc) {
        if (qtdFuncionarios >= MAX_FUNCIONARIOS) {
            System.out.println("Sem espaço para novos funcionários.");
            return;
        }

        System.out.print("Código do funcionário (único): ");
        int codigo = lerInt(sc);
        if (buscarFuncionarioIndexPorCodigo(codigo) != -1) {
            System.out.println("Código já cadastrado.");
            return;
        }

        System.out.print("Nome do funcionário: ");
        String nome = sc.nextLine();

        System.out.print("Cargo: ");
        String cargo = sc.nextLine();

        System.out.print("Salário: ");
        double salario = lerDouble(sc);

        Funcionario f = new Funcionario(codigo, nome, cargo, salario);
        funcionarios[qtdFuncionarios++] = f;

        // dependentes
        System.out.print("Quantidade de dependentes (0 ou mais): ");
        int qtd = lerInt(sc);
        if (qtd < 0)
            qtd = 0;

        // checa espaço no vetor de dependentes
        if (qtdDependentes + qtd > MAX_DEPENDENTES) {
            System.out.println(
                    "Sem espaço para todos os dependentes. Cadastro do funcionário foi feito, mas dependentes não.");
            return;
        }

        for (int i = 1; i <= qtd; i++) {
            System.out.print("Nome do dependente " + i + ": ");
            String nomeDep = sc.nextLine();
            dependentes[qtdDependentes++] = new Dependente(f, nomeDep);
        }

        System.out.println("Funcionário e dependentes cadastrados com sucesso!");
    }

    /* ---------- Opção 2 ---------- */
    private static void mostrarBonus() {
        if (qtdFuncionarios == 0) {
            System.out.println("Nenhum funcionário cadastrado.");
            return;
        }

        System.out.printf("%-6s %-20s %-12s %-12s%n", "Cod", "Nome", "Qtde Dep.", "Bônus (R$)");
        for (int i = 0; i < qtdFuncionarios; i++) {
            Funcionario f = funcionarios[i];
            int qDep = contarDependentes(f);
            double bonus = f.getSalario() * 0.02 * qDep;
            System.out.printf("%-6d %-20s %-12d R$ %-10.2f%n",
                    f.getNumeroFuncionario(), f.getNomeFuncionario(), qDep, bonus);
        }
    }

    /* ---------- Opção 3 ---------- */
    private static void excluirFuncionario(Scanner sc) {
        System.out.print("Informe o código do funcionário a excluir: ");
        int codigo = lerInt(sc);

        int idx = buscarFuncionarioIndexPorCodigo(codigo);
        if (idx == -1) {
            System.out.println("Funcionário Inexistente");
            return;
        }

        Funcionario alvo = funcionarios[idx];

        // Remove funcionário (shift para esquerda)
        for (int i = idx; i < qtdFuncionarios - 1; i++) {
            funcionarios[i] = funcionarios[i + 1];
        }
        funcionarios[--qtdFuncionarios] = null;

        // Remove dependentes ligados a esse funcionário (varre e shift quando bate)
        int i = 0;
        while (i < qtdDependentes) {
            if (dependentes[i].getFuncionario() == alvo) {
                // remove este dependente deslocando
                for (int j = i; j < qtdDependentes - 1; j++) {
                    dependentes[j] = dependentes[j + 1];
                }
                dependentes[--qtdDependentes] = null;
                // não incrementa i, pois após o shift caiu um novo dep nesta posição
            } else {
                i++;
            }
        }

        System.out.println("Funcionário e seus dependentes foram excluídos.");
    }

    /* ---------- Opção 4 ---------- */
    private static void alterarSalario(Scanner sc) {
        System.out.print("Informe o código do funcionário: ");
        int codigo = lerInt(sc);

        int idx = buscarFuncionarioIndexPorCodigo(codigo);
        if (idx == -1) {
            System.out.println("Funcionário Inexistente");
            return;
        }

        Funcionario f = funcionarios[idx];
        System.out.println("Funcionário: " + f.getNomeFuncionario() + " | Salário atual: R$ "
                + String.format("%.2f", f.getSalario()));
        System.out.print("Novo salário: ");
        double novo = lerDouble(sc);
        f.setSalario(novo);
        System.out.println("Salário alterado com sucesso!");
    }

    /* ---------- Auxiliares ---------- */
    private static int buscarFuncionarioIndexPorCodigo(int codigo) {
        for (int i = 0; i < qtdFuncionarios; i++) {
            if (funcionarios[i].getNumeroFuncionario() == codigo)
                return i;
        }
        return -1;
    }

    private static int contarDependentes(Funcionario f) {
        int c = 0;
        for (int i = 0; i < qtdDependentes; i++) {
            if (dependentes[i].getFuncionario() == f)
                c++;
        }
        return c;
    }

    private static int lerInt(Scanner sc) {
        while (true) {
            try {
                String s = sc.nextLine().trim();
                return Integer.parseInt(s);
            } catch (Exception e) {
                System.out.print("Valor inválido. Digite um inteiro: ");
            }
        }
    }

    private static double lerDouble(Scanner sc) {
        while (true) {
            try {
                String s = sc.nextLine().trim().replace(",", ".");
                return Double.parseDouble(s);
            } catch (Exception e) {
                System.out.print("Valor inválido. Digite um número: ");
            }
        }
    }
}
