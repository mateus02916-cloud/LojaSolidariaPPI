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
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcao) {
                case 1: estoque.exibirEstoque(); break;
                case 2: menuAdicionar(); break;
                case 3: menuRemover(); break;
                case 4: estoque.listarCategorias(); break;
                case 0: System.out.println("Saindo..."); break;
                default: System.out.println("Opção inválida!");
            }

            if (opcao != 0) {
                System.out.println("\nPressione Enter para continuar...");
                scanner.nextLine();
            }
        } while (opcao != 0);
    }

    private void menuAdicionar() {
        System.out.println("\n=== ADICIONAR QUANTIDADE ===");
        String[] categorias = estoque.getCategorias();

        for (int i = 0; i < categorias.length; i++) {
            System.out.println((i + 1) + ". " + categorias[i]);
        }

        System.out.print("Escolha o número da categoria: ");
        int escolha = scanner.nextInt();
        scanner.nextLine();

        if (escolha < 1 || escolha > categorias.length) {
            System.out.println("Opção inválida!");
            return;
        }

        System.out.print("Digite a quantidade: ");
        int quantidade = scanner.nextInt();
        scanner.nextLine();

        if (quantidade <= 0) {
            System.out.println("Quantidade deve ser positiva!");
            return;
        }

        // Passamos o nome da categoria baseado na escolha numérica
        estoque.adicionarQuantidade(categorias[escolha - 1], quantidade);
    }

    private void menuRemover() {
        System.out.println("\n=== REMOVER QUANTIDADE ===");
        estoque.listarCategorias();

        System.out.print("Digite o nome da categoria: ");
        String categoria = scanner.nextLine();

        if (!estoque.categoriaExiste(categoria)) {
            System.out.println("Categoria inválida!");
            return;
        }

        System.out.print("Digite a quantidade a remover: ");
        int quantidade = scanner.nextInt();
        scanner.nextLine();

        estoque.removerQuantidade(categoria, quantidade);
    }
}