import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Menu.limparTerminal();
        
        System.out.println("*********** LOGIN ***********");
        
        System.out.print("Usuário: ");
        String usuario = scanner.nextLine();
        
        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        
        if (usuario.equals("admin") && senha.equals("1234")) {
            System.out.println("\n Login realizado com sucesso!");
            
            
            Menu menu = new Menu();
            menu.exibirMenu();
        } else {
            System.out.println("\n Erro: Usuário ou senha incorretos.");
            System.out.println("Acesso negado.");
        }

        scanner.close();
    }
}