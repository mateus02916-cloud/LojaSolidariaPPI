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
            limparTerminal();

            System.out.println("\n=== CONTROLE DE ESTOQUE - LOJA DE SOLIDÁRIA ===");
            System.out.println("1. Exibir quantidade total em estoque");
            System.out.println("2. Adicionar quantidade (Entrada)");
            System.out.println("3. Remover quantidade (Saída)");
            System.out.println("4. Listar categorias");
            System.out.println("5. Gerar relatório mensal");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcao) {
                case 1:
                    estoque.exibirEstoqueTotal();  
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
                case 5:
                    menuRelatorio();
                    break;
                case 0:
                    System.out.println("Saindo do sistema...");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }

            if (opcao != 0) {
                System.out.println("\nPressione Enter para continuar...");
                scanner.nextLine();
            }
        } while (opcao != 0);
        scanner.close();
    }

    private void menuAdicionar() {
        System.out.println("\n=== ADICIONAR QUANTIDADE (REGISTRAR ENTRADA) ===");
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
        System.out.println("\n=== REMOVER QUANTIDADE (REGISTRAR SAÍDA) ===");
        String[] categorias = estoque.getCategorias();

        for (int i = 0; i < categorias.length; i++) {
            System.out.println((i + 1) + ". " + categorias[i]);
        }

        System.out.print("Digite o número da categoria: ");
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
        System.out.println("\n=== GERAR RELATÓRIO MENSAL ===");
        System.out.print("Digite o mês: ");
        int mes = scanner.nextInt();

        if (mes < 1 || mes > 12) {
            System.out.println("Mês inválido!");
            return;
        }
        
        System.out.print("Digite o ano: ");
        int ano = scanner.nextInt();
        scanner.nextLine();

        if ( ano < 2000 || ano > 2999) {
            System.out.println("Ano Inválido!");
            return;
        }

        System.out.println();
        
        System.out.print("Observações 1 (ex: peças descartas, doações de empresas/instituições): ");
        String obs1 = scanner.nextLine();
        
        System.out.print("Observações 2 (ex: ações externas): ");
        String obs2 = scanner.nextLine();
        
        estoque.gerarRelatorioMensal(mes, ano, obs1, obs2);
    }
    
    
    public static void limparTerminal() {
    
    System.out.print("\033[H\033[2J");
    System.out.flush();
}

}