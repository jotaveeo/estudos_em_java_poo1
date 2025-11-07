import java.util.Scanner;
import primeiraQ.PrimeiraQuestao;
import segundaQ.SegundaQuestao;
import terceiraQ.TerceiraQuestao;
import quartaQ.QuartaQuestao;
import quintaQ.QuintaQuestao;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Menu ===");
            System.out.println("1 - Primeira Questão (Carro)");
            System.out.println("2 - Segunda Questão (Pessoa)");
            System.out.println("3 - Terceira Questão (Compra)");
            System.out.println("4 - Quarta Questão (Escola)");
            System.out.println("5 - Quinta Questão (Departamento)");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");

            int op = sc.nextInt();
            sc.nextLine();

            switch (op) {
                case 1:
                    PrimeiraQuestao.run(sc);
                    break;
                case 2:
                    SegundaQuestao.run(sc);
                    break;
                case 3:
                    TerceiraQuestao.run(sc);
                    break;
                case 4:
                    QuartaQuestao.run(sc);
                    break;
                case 5:
                    QuintaQuestao.run(sc);
                    break;
                case 0:
                    System.out.println("Até mais!");
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }
}
