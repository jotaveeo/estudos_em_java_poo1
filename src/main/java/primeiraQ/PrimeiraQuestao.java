package primeiraQ;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PrimeiraQuestao {
    public static void run(Scanner sc) {
        System.out.println("\n[Q01] Imposto de Carro");

        List<Carro> carros = new ArrayList<Carro>();
        carros.add(new Carro("ABC-1234", 2012));
        carros.add(new Carro("XYZ-4321", 2009));
        carros.add(new Carro("DEF-5678", 2015));
        carros.add(new Carro("HIJ-8765", 2005));
        carros.add(new Carro("LMN-9999", 2020));

        System.out.print("Informe o ano atual: ");
        int anoAtual = sc.nextInt(); sc.nextLine();

        double total = 0.0;
        int isentos = 0;

        System.out.println("\nPlaca Ano  Imposto");
        for (Carro c : carros) {
            double imp = c.calcularImposto(anoAtual);
            System.out.printf("%-10s  %4d  R$ %.2f%n",
                    c.getPlaca(), c.getAnoFabricacao(), imp);
            total += imp;
            if (imp == 0.0) isentos++;
        }
        System.out.printf("Total de impostos: R$ %.2f%n", total);
        System.out.println("Carros isentos: " + isentos);
    }
}
