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
            System.out.println("1. Exibir estoque atual");
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
            }
            
        }


}
