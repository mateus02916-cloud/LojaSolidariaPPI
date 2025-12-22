import java.util.Scanner;

public class Menu {

    private Estoque estoque;
    private Scanner scanner;

     public Menu(){

        estoque = new Estoque();
        scanner = new Scanner(System.in);

    }

    public void exibirMenu() {
        int opcao;

        do {
            System.out.println("=== CONTROLE DE ESTOQUE - LOJA DE ROUPAS ===");
            System.out.println("1. Exibir estoque ");
            System.out.println("2. Adicionar quantidade");
            System.out.println("3. Remover quantidade");
            System.out.println("4. Listar categorias");
            System.out.println("5. Exibe todas as famílias que retiraram peças");
            System.out.println("6. Pesquisar pelo nome de um membro familiar");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();

            scanner.nextLine(); // Limpar o buffer

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

                case 5:
                    menuListarFamilias();
                    break;

                case 6:
                    menuPesquisarMembro();      
                    break;

                case 0:
                    System.out.println("Saindo do sistema...");

                default:
                    System.out.println("Opção inválida! Tente novamente.");

            }

            if (opcao != 0) {
                System.out.println("Precine Enter para continuar...");
                scanner.nextLine();

            }

        } while (opcao != 0);
        scanner.close();

    }


    private void menuAdicionar() {
        System.out.println("\n=== ADICIONAR QUANTIDADE ===");
        estoque.listarCategorias();

        System.out.println("Digite o nome da categoria: ");
        String categoria = scanner.nextLine();

        if (!estoque.categoriaExiste(categoria)) {
            System.out.println("Categoria inválida!");
            return;
        }

        System.out.println("Digite a quantidade a dicionar: ");
        int quantidade = scanner.nextInt();
        scanner.nextLine(); // Limpar buffer

        if (quantidade <= 0) {
            System.out.println("Quantidade deve ser maior que zero!");
            return;

        }

        estoque.adicionarQuantidade(categoria, quantidade);

    }

    private void menuRemover(){
            System.out.println("\n=== REMOVER QUANTIDADE ===");
            estoque.listarCategorias();

            System.out.println("Digite o nome da categoria:");
            String categoria = scanner.nextLine();

            if (!estoque.categoriaExiste(categoria)){
                System.out.println("Categoria inválida!");
                return;

            }

            System.out.println("Digitge a quantidade a remover: ");
            int quantidade = scanner.nextInt();
            scanner.nextLine(); // Limpar buffer

            if (quantidade <0){
                System.out.println("Quantidade deve ser maior que zero!");
                return;
            }

            estoque.removerQuantidade(categoria, quantidade);
            

    }

    //em desenvolvimento
    private void menuListarFamilias(){
        System.out.println("Qual mês deseja consultar?");
        int mes = scanner.nextInt();

        if (mes >= 1 && mes <=12){
        System.out.println("\n=== LISTA DE FAMÍLIAS QUE JÁ RETIRARAM PEÇAS NO MÊS " +  mes + "===");
        //condicinal para ver quais famílias retiraram peças em determinado mês 

        } else {

            System.out.println("Mês inválido! Digite um número entre 1 e 12.");
        }
    }

    //em desenvolvimento
    private void menuPesquisarMembro(){
        System.out.println("\n=== PESQUISAR MEMBRO FAMILIAR ===");
        System.out.println("Digite o nome do membro familiar: ");
        String nome = scanner.nextLine();

        //condicional para pesquisar o nome do membro familiar no csv e print para exebir;
    }


}