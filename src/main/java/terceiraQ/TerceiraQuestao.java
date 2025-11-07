package terceiraQ;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TerceiraQuestao {
  public static void run(Scanner sc) {
    System.out.println("\n[Q03] Compra de Produtos");

    // Carregar 3 produtos
    List<Produto> produtos = new ArrayList<Produto>();
    System.out.println("=== Cadastro de Produtos ===");
    for (int i = 1; i <= 3; i++) {
      System.out.print("Número do produto " + i + ": ");
      int num = sc.nextInt();

      double preco;
      do {
        System.out.print("Preço do produto " + i + " (entre 20 e 350): ");
        preco = sc.nextDouble();
      } while (preco < 20 || preco > 350);

      produtos.add(new Produto(num, preco));
    }

    // Carregar 3 clientes
    List<Cliente> clientes = new ArrayList<Cliente>();
    sc.nextLine(); // limpar buffer
    System.out.println("\n=== Cadastro de Clientes ===");
    for (int i = 1; i <= 3; i++) {
      System.out.print("Número do cliente " + i + ": ");
      int num = sc.nextInt();
      sc.nextLine();

      System.out.print("Nome do cliente " + i + ": ");
      String nome = sc.nextLine();

      char sexo;
      do {
        System.out.print("Sexo do cliente " + i + " (M/F): ");
        sexo = sc.next().charAt(0);
        sc.nextLine();
      } while (sexo != 'M' && sexo != 'm' && sexo != 'F' && sexo != 'f');

      clientes.add(new Cliente(num, nome, sexo));
    }

    // Carregar compra
    System.out.println("\n=== Realizar Compra ===");
    System.out.print("Número do produto: ");
    int numProduto = sc.nextInt();

    System.out.print("Número do cliente: ");
    int numCliente = sc.nextInt();

    System.out.print("Quantidade: ");
    int qtd = sc.nextInt();

    Compra compra = new Compra(numProduto, numCliente, qtd);

    // Encontrar produto e cliente correspondentes
    Produto produtoSelecionado = null;
    for (Produto p : produtos) {
      if (p.getNumero() == numProduto) {
        produtoSelecionado = p;
        break;
      }
    }

    Cliente clienteSelecionado = null;
    for (Cliente c : clientes) {
      if (c.getNumero() == numCliente) {
        clienteSelecionado = c;
        break;
      }
    }

    // Calcular valor total
    compra.calcularValorTotal(produtoSelecionado, clienteSelecionado);

    System.out.println("\n=== Nota da Compra ===");
    System.out.println("Cliente: " + clienteSelecionado.getNome());
    System.out.println("Produto: " + produtoSelecionado.getNumero());
    System.out.println("Quantidade: " + qtd);
    System.out.printf("Valor Total: R$ %.2f\n", compra.getValorTotal());
  }
}
