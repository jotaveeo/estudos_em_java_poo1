package segundaQ;

import java.util.Scanner;

public class SegundaQuestao {
    public static void run(Scanner sc) {
        System.out.println("\n[Q02] Pessoa");

        System.out.print("Nome: ");
        String nome = sc.nextLine();

        System.out.print("Idade (anos): ");
        int idade = sc.nextInt();

        System.out.print("Ano atual: ");
        int anoAtual = sc.nextInt(); sc.nextLine();

        Pessoa p = new Pessoa(nome, idade);

        System.out.println("\n=== Resultado ===");
        System.out.println("Nome: " + p.getNome());
        System.out.println("Idade em meses: " + p.getIdadeEmMeses());
        System.out.println("Idade em 2050: " + p.getIdadeEm2050(anoAtual));
    }
}
