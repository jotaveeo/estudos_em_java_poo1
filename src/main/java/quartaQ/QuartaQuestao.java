package quartaQ;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QuartaQuestao {
    // limites sugeridos
    private static final int MAX_ALUNOS = 10;
    private static final int MAX_DISCIPLINAS = 5;
    private static final int MAX_MATRICULAS = 30;

    private static final List<Aluno> alunos = new ArrayList<Aluno>();
    private static final List<Disciplina> disciplinas = new ArrayList<Disciplina>();
    private static final List<Matricula> matriculas = new ArrayList<Matricula>();

    public static void run(Scanner sc) {
        System.out.println("\n[Q04] Secretaria Escolar");
        
        while (true) {
            System.out.println("\n=== Secretaria Escolar ===");
            System.out.println("1) Cadastrar disciplina (prática ou não)");
            System.out.println("2) Cadastrar aluno");
            System.out.println("3) Matricular aluno em disciplina");
            System.out.println("4) Lançar nota de um aluno");
            System.out.println("5) Mostrar boletim do aluno");
            System.out.println("0) Sair");
            System.out.print("Opção: ");
            int op = lerInt(sc);

            switch (op) {
                case 1: opcaoCadastrarDisciplina(sc); break;
                case 2: opcaoCadastrarAluno(sc); break;
                case 3: opcaoMatricular(sc); break;
                case 4: opcaoLancamentoNota(sc); break;
                case 5: opcaoBoletim(sc); break;
                case 0: System.out.println("Até mais!"); return;
                default: System.out.println("Opção inválida!");
            }
        }
    }

    // === Opção 1: Cadastrar disciplina ===
    private static void opcaoCadastrarDisciplina(Scanner sc) {
        if (disciplinas.size() >= MAX_DISCIPLINAS) {
            System.out.println("Limite máximo de disciplinas atingido (" + MAX_DISCIPLINAS + ").");
            return;
        }

        System.out.print("Código da disciplina (único): ");
        int cod = lerInt(sc);
        if (buscarDisciplina(cod) != null) {
            System.out.println("Código já cadastrado.");
            return;
        }

        System.out.print("Nome da disciplina: ");
        String nome = sc.nextLine();

        System.out.print("Carga horária geral (CH): ");
        int chGeral = lerInt(sc);

        System.out.print("É disciplina prática? (S/N): ");
        String resp = sc.nextLine().trim();
        boolean pratica = resp.equalsIgnoreCase("S");

        if (pratica) {
            System.out.print("Carga horária prática (CH prática): ");
            int chPratica = lerInt(sc);
            disciplinas.add(new DisciplinaPratica(cod, nome, chGeral, chPratica));
        } else {
            disciplinas.add(new Disciplina(cod, nome, chGeral));
        }

        System.out.println("Disciplina cadastrada com sucesso!");
    }

    // === Opção 2: Cadastrar aluno ===
    private static void opcaoCadastrarAluno(Scanner sc) {
        if (alunos.size() >= MAX_ALUNOS) {
            System.out.println("Limite máximo de alunos atingido (" + MAX_ALUNOS + ").");
            return;
        }

        System.out.print("Código do aluno (único): ");
        int cod = lerInt(sc);
        if (buscarAluno(cod) != null) {
            System.out.println("Código já cadastrado.");
            return;
        }

        System.out.print("Nome do aluno: ");
        String nome = sc.nextLine();

        alunos.add(new Aluno(cod, nome));
        System.out.println("Aluno cadastrado com sucesso!");
    }

    // === Opção 3: Matricular ===
    private static void opcaoMatricular(Scanner sc) {
        if (matriculas.size() >= MAX_MATRICULAS) {
            System.out.println("Limite máximo de matrículas atingido (" + MAX_MATRICULAS + ").");
            return;
        }

        if (alunos.isEmpty() || disciplinas.isEmpty()) {
            System.out.println("Cadastre ao menos 1 aluno e 1 disciplina antes de matricular.");
            return;
        }

        System.out.print("Código do aluno: ");
        int codAluno = lerInt(sc);
        Aluno aluno = buscarAluno(codAluno);
        if (aluno == null) {
            System.out.println("Aluno não encontrado.");
            return;
        }

        System.out.print("Código da disciplina: ");
        int codDisc = lerInt(sc);
        Disciplina disc = buscarDisciplina(codDisc);
        if (disc == null) {
            System.out.println("Disciplina não encontrada.");
            return;
        }

        System.out.print("Ano letivo: ");
        int ano = lerInt(sc);

        System.out.print("Série (ex.: 1A, 2B...): ");
        String serie = sc.nextLine();

        // Não matricular mais de uma vez no mesmo trio (aluno, disciplina, ano)
        if (existeMatricula(aluno.getCodigo(), disc.getCodigo(), ano)) {
            System.out.println("Já existe matrícula deste aluno nesta disciplina para este ano.");
            return;
        }

        Matricula m = new Matricula(ano, serie, aluno, disc);
        matriculas.add(m);
        System.out.println("Matrícula realizada com sucesso! (notas iniciam em 0.0)");
    }

    // === Opção 4: Lançar nota ===
    private static void opcaoLancamentoNota(Scanner sc) {
        System.out.print("Código do aluno: ");
        int codAluno = lerInt(sc);

        System.out.print("Código da disciplina: ");
        int codDisc = lerInt(sc);

        System.out.print("Ano letivo: ");
        int ano = lerInt(sc);

        System.out.print("Bimestre (1..4): ");
        int bim = lerInt(sc);
        if (bim < 1 || bim > 4) {
            System.out.println("Bimestre inválido.");
            return;
        }

        Matricula mat = buscarMatricula(codAluno, codDisc, ano);
        if (mat == null) {
            System.out.println("Matrícula Inválida.");
            return;
        }

        System.out.print("Nota do " + bim + "º bimestre: ");
        double nota = lerDouble(sc);
        mat.setNota(bim, nota);
        System.out.println("Nota lançada com sucesso!");
    }

    // === Opção 5: Boletim ===
    private static void opcaoBoletim(Scanner sc) {
        System.out.print("Código do aluno: ");
        int codAluno = lerInt(sc);

        System.out.print("Ano letivo: ");
        int ano = lerInt(sc);

        Aluno aluno = buscarAluno(codAluno);
        if (aluno == null) {
            System.out.println("Matrícula Inválida.");
            return;
        }

        // filtra matrículas do aluno nesse ano
        List<Matricula> mats = new ArrayList<Matricula>();
        for (int i = 0; i < matriculas.size(); i++) {
            Matricula m = matriculas.get(i);
            if (m.getAluno().getCodigo() == codAluno && m.getAnoLetivo() == ano) {
                mats.add(m);
            }
        }

        if (mats.isEmpty()) {
            System.out.println("Matrícula Inválida.");
            return;
        }

        // Cabeçalho
        System.out.println("Código: " + aluno.getCodigo() + "  Nome: " + aluno.getNome());
        System.out.println("Ano: " + ano);
        System.out.printf("%-20s %-6s %-9s %-7s %-7s %-7s %-7s %-7s%n",
                "Disciplina", "CH", "CH prat.", "1º Bim", "2º Bim", "3º Bim", "4º Bim", "Média");

        // Linhas
        for (int i = 0; i < mats.size(); i++) {
            Matricula m = mats.get(i);
            Disciplina d = m.getDisciplina();
            String nomeDisc = d.getNome();
            int ch = d.getCargaHorariaGeral();
            String chp = d.isPratica() ? String.valueOf(d.getCargaHorariaPratica()) : "-";
            double n1 = m.getNota(1);
            double n2 = m.getNota(2);
            double n3 = m.getNota(3);
            double n4 = m.getNota(4);
            double media = m.mediaFinal();

            System.out.printf("%-20s %-6d %-9s %-7.1f %-7.1f %-7.1f %-7.1f %-7.2f%n",
                    nomeDisc, ch, chp, n1, n2, n3, n4, media);
        }
    }

    // === utilidades ===
    private static Aluno buscarAluno(int codigo) {
        for (int i = 0; i < alunos.size(); i++) {
            if (alunos.get(i).getCodigo() == codigo) return alunos.get(i);
        }
        return null;
    }

    private static Disciplina buscarDisciplina(int codigo) {
        for (int i = 0; i < disciplinas.size(); i++) {
            if (disciplinas.get(i).getCodigo() == codigo) return disciplinas.get(i);
        }
        return null;
    }

    private static boolean existeMatricula(int codAluno, int codDisc, int ano) {
        return buscarMatricula(codAluno, codDisc, ano) != null;
    }

    private static Matricula buscarMatricula(int codAluno, int codDisc, int ano) {
        for (int i = 0; i < matriculas.size(); i++) {
            Matricula m = matriculas.get(i);
            if (m.getAluno().getCodigo() == codAluno
                    && m.getDisciplina().getCodigo() == codDisc
                    && m.getAnoLetivo() == ano) {
                return m;
            }
        }
        return null;
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