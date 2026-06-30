import java.util.Scanner;

public class Login {

    protected static boolean login() {
        Scanner scanner = new Scanner(System.in);

        /* Menu.limparTerminal(); */

        System.out.println("*********** LOGIN ***********");

        System.out.print("Usuário: ");
        String usuario = scanner.nextLine();

        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        if (usuario.equals("admin") && senha.equals("1234")) {
            System.out.println("\n Login realizado com sucesso!");

            Menu menu = new Menu();
            menu.exibirMenu();
            return true;
        } else {
            System.out.println("\n Erro: Usuário ou senha incorretos.");
            System.out.println("Acesso negado.");
            return false;
        }
    }
}
