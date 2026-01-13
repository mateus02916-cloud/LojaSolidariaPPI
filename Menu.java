import java.util.Scanner;

public class Menu {
    private Estoque estoque;
    private Scanner scanner;

    public Menu() {
        estoque = new Estoque();
        scanner = new Scanner(System.in);
    }

    public void exibirMenu() {
        int opcao;

        do {
            System.out.println("\n=== CONTROLE DE ESTOQUE - LOJA DE ROUPAS ===");
            System.out.println("1. Exibir estoque");
            System.out.println("2. Adicionar quantidade");
            System.out.println("3. Remover quantidade");
            System.out.println("4. Listar categorias");
            System.out.println("5. Gerar relatório");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcao) {
                case 1:
                    estoque.exibirEstoque();
                    break;
                case 2:
                    menuAdicionar();
                    break;
                case 3:
                    menuRemover();
                    break;
                case 4:
                    estoque.listarCategorias();
                    break;
                case 0:
                    System.out.println("Saindo do sistema...");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }

            if (opcao != 0) {
                System.out.println("\n Pressione Enter para continuar...");
                scanner.nextLine();
            }
        } while (opcao != 0);
        scanner.close();
    }

    private void menuAdicionar() {
        System.out.println("\n=== ADICIONAR QUANTIDADE ===");
        String[] categorias = estoque.getCategorias();

        for (int i = 0; i < categorias.length; i++) {
            System.out.println((i + 1) + ". " + categorias[i]);
        }

        System.out.print("Digite o número da categoria: ");
        int escolha = scanner.nextInt();
        scanner.nextLine();

        if (escolha < 1 || escolha > categorias.length) {
            System.out.println("Opção inválida");
            return;
        }

        System.out.print("Digite a quantidade a adicionar: ");
        int quantidade = scanner.nextInt();
        scanner.nextLine();

        if (quantidade <= 0) {
            System.out.println("Quantidade deve ser maior que zero!");
            return;
        }

        estoque.adicionarQuantidade(categorias[escolha - 1], quantidade);
    }

    private void menuRemover() {
        System.out.println("\n=== REMOVER QUANTIDADE ===");
        String[] categorias = estoque.getCategorias();

        // Lista as categorias por índice
        for (int i = 0; i < categorias.length; i++) {
            System.out.println((i + 1) + ". " + categorias[i]);
        }

        System.out.print("Digite o número da categoria para remover: ");
        int escolha = scanner.nextInt();
        scanner.nextLine();

        if (escolha < 1 || escolha > categorias.length) {
            System.out.println("Opção inválida!");
            return;
        }

        System.out.print("Digite a quantidade a remover: ");
        int quantidade = scanner.nextInt();
        scanner.nextLine();

        if (quantidade <= 0) {
            System.out.println("Quantidade deve ser maior que zero!");
            return;
        }

        
        estoque.removerQuantidade(categorias[escolha - 1], quantidade);
    }

    private void menuRelatorio() {
        //data, quantidade 

        System.out.println("Se deseja registar com a data de hoje DIGITE 1, caso deseje registar com outra data, DIGITE AQUI (dd/mm/aaaa): ");
        String opcaoData = scanner.nextLine();

        

    }
}