import java.util.Scanner;

public class Menu {

        private Estoque estoque;
        private Scanner scanner;

        public Menu(){

            estoque = new Estoque();
            scanner = new Scanner(System.in);

        }

        public void exibirMenu(){
            int opcao;
        }

        do{
            System.out.println("=== CONTROLE DE ESTOQUE - LOJA DE ROUPAS ===");
            System.out.println("1. Exibir estoque ");
            System.out.println("2. Adicionar quantidade");
            System.out.println("3. Remover quantidade");
            System.out.println("4. Listar categorias");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();

            scanner.nextLine(); // Limpar o buffer

            switch (opcao){
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
                
                default:
                    System.out.println("Opção inválida! Tente novamente.");
                
            }

            if (opcao != 0){
                System.out.println("Precine Enter para continuar...");
                scanner.nextLine();
                

            }

            
        } while (opcao != 0);
        scanner.close();


}

private void menuAdicionar(){
    System.out.println("\n=== ADICIONAR QUANTIDADE ===");
    estoque.listarCategorias();

    System.out.println("Digite o nome da categoria: ");
    String categoria = scanner.nextLine();

    if (!estoque.categoriaExiste(categoria)){
        System.out.println("Categoria inválida!");
        return;
    }

    System.out.println("Digite a quantidade a dicionar: ");
    int quantidade = scanner.nextInt();
    scanner.nextLine();  //Limpar buffer

    if (quantidade <= 0){
        System.out.println("Quantidade deve ser maior que zero!");
        return;

    }

    estoque.adicionarQuantidade(categoria, quantidade);


}

    private void menuRemover(){
        System.out.println("\n=== REMOVER QUANTIDADE ===");
        estoque.listarCategoria();

        System.out.println("Digite o nome da categoria:");
        String categora = scanner.nextLine();

        if (!estoque.categoriaExiste(categoria)){
            System.out.println("Categoria inválida!");
            return;

        }

        System.out.println("Digitge a quantidade a remover: ");
        int quantidade = scanner.nextInt();
        scanner.nextLine(); // Limpar buffer

        if (quantidade <0){
            System.out.println("Quantidade deve ser maior que zero!");
            return
        }

        estoque.removerQuantidade(categoria, quantidade);
        

    }


