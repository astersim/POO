package org.example;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Menu {
    private static ProdutoDAO produtoDAO = new ProdutoDAO();
    private static UsuarioDAO usuarioDAO = new UsuarioDAO();
    private static PedidoDAO pedidoDAO = new PedidoDAO();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        menuPrincipal();
    }

    private static void menuPrincipal() {
        int opcao;
        do {
            System.out.println("\n--- Menu Principal ---");
            System.out.println("1. Cadastrar Produto");
            System.out.println("2. Cadastrar Usuário");
            System.out.println("3. Gerar Pedido");
            System.out.println("4. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha

            switch (opcao) {
                case 1:
                    menuProdutos();
                    break;
                case 2:
                    cadastrarUsuario();
                    break;
                case 3:
                    menuPedido();
                    break;
                case 4:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 4);
    }

    private static void menuProdutos() {
        int opcao;
        do {
            System.out.println("\n--- Menu de Produtos ---");
            System.out.println("1. Cadastrar Produto");
            System.out.println("2. Consultar Produto");
            System.out.println("3. Alterar Preço");
            System.out.println("4. Inativar Produto");
            System.out.println("5. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha

            switch (opcao) {
                case 1:
                    cadastrarProduto();
                    break;
                case 2:
                    consultarProduto();
                    break;
                case 3:
                    alterarPrecoProduto();
                    break;
                case 4:
                    inativarProduto();
                    break;
                case 5:
                    System.out.println("Voltando ao Menu Principal...");
                    menuPrincipal();
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 5);
    }

    private static void menuPedido() {
        int opcao;
        do {
            System.out.println("\n--- Menu de Pedido ---");
            System.out.println("1. Gerar Pedido");
            System.out.println("2. Consultar Pedido");
            System.out.println("3. Inativar Pedido");
            System.out.println("4. Menu Principal");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha

            switch (opcao) {
                case 1:
                    gerarPedido();
                    break;
                case 2:
                    consultarPedido();
                    break;
                case 3:
                    inativarPedido();
                    break;
                case 4:
                    System.out.println("Voltando ao Menu Principal...");
                    menuPrincipal();
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 4);
    }

    private static void cadastrarProduto() {
        System.out.print("Digite o nome do produto: ");
        String nome = scanner.nextLine();
        System.out.print("Digite a marca do produto: ");
        String marca = scanner.nextLine();
        System.out.print("Digite o preço do produto: ");
        double preco = scanner.nextDouble();
        scanner.nextLine(); // Consumir a quebra de linha

        Produto produto = new Produto(nome, preco, marca);
        produtoDAO.cadastrarProduto(produto);
    }

    private static void consultarProduto() {
        System.out.print("Digite o ID do produto que deseja consultar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir a quebra de linha

        Produto produto = produtoDAO.consultarProduto(id);
        if (produto != null) {
            System.out.println("Produto encontrado: " + produto);
        } else {
            System.out.println("Produto com ID " + id + " não encontrado.");
        }
    }

    private static void alterarPrecoProduto() {
        System.out.print("Digite o ID do produto que deseja alterar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir a quebra de linha

        System.out.print("Digite o novo preço do produto: ");
        double preco = scanner.nextDouble();
        scanner.nextLine(); // Consumir a quebra de linha

        Produto produto = new Produto(id, null, preco, null, 1);
        produtoDAO.alterarProduto(produto);
    }

    private static void inativarProduto() {
        System.out.print("Digite o ID do produto: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        produtoDAO.desativarProduto(id);
    }

    private static void cadastrarUsuario() {
        System.out.print("Digite o nome do usuário: ");
        String nome = scanner.nextLine();
        System.out.print("Digite o email do usuário: ");
        String email = scanner.nextLine();
        System.out.print("Digite o endereço do usuário: ");
        String endereco = scanner.nextLine();
        System.out.print("Digite o telefone do usuário: ");
        String telefone = scanner.nextLine();
        System.out.print("Digite a profissão do usuário: ");
        String profissao = scanner.nextLine();

        usuarioDAO.cadastrarUsuario(nome, email, endereco, telefone, profissao);
    }

    private static void gerarPedido() {
        System.out.print("Digite o ID do usuário para o pedido: ");
        int idUsuario = scanner.nextInt();
        scanner.nextLine(); // Consumir a quebra de linha

        Pedido pedido = new Pedido(0, idUsuario, 0, "Ativo");
        pedidoDAO.cadastrarPedido(pedido);

        boolean adicionarMaisProdutos = false;
        do {
            System.out.print("Digite o ID do produto: ");
            int idProduto = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha

            Produto produto = produtoDAO.consultarProduto(idProduto);
            if (produto == null) {
                System.out.println("Produto não encontrado.");
                continue;
            }

            System.out.print("Digite a quantidade para o produto: ");
            int quantidade = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha

            itemPedido item = new itemPedido(pedido.getId(), idProduto, idUsuario, produto.getNome(), quantidade, produto.getPreco());
            itemPedidoDAO.cadastrarItemPedido(item);

            System.out.print("Deseja adicionar mais produtos? (s/n): ");
            String resposta = scanner.nextLine();
            adicionarMaisProdutos = resposta.equalsIgnoreCase("s");
        } while (adicionarMaisProdutos);

        System.out.println("Pedido gerado com sucesso!");
    }

    private static void consultarPedido() {
        System.out.print("Digite o ID do pedido que deseja consultar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir a quebra de linha

        List<Map<String, Object>> pedido = pedidoDAO.consultarPedido(id);
        if (!pedido.isEmpty()) {
            for (Map<String, Object> item : pedido) {
                System.out.println("ID: " + item.get("id") + ", Nome: " + item.get("nome") + ", Quantidade: " + item.get("quantidade") + ", Valor Total: " + item.get("valorTotal"));
            }
        } else {
            System.out.println("Pedido com ID " + id + " não encontrado.");
        }
    }

    private static void inativarPedido() {
        System.out.print("Digite o ID do pedido: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        pedidoDAO.alterarStatusPedido(id, "Inativo");
    }
}