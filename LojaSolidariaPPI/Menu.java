import java.util.Scanner;

public class Menu {
    private CadastroDoacoes estoqueDoacoes;
    private CadastroEmprestimos emprestimos;
    private FiltraEstatisticas filtraEstatisticas;
    private Scanner scanner;

    public Menu() {
        estoqueDoacoes = new CadastroDoacoes();
        emprestimos = new CadastroEmprestimos();
        filtraEstatisticas = new FiltraEstatisticas();
        scanner = new Scanner(System.in);
    }

    //MENU INICIAL
    public void exibirMenu() {
        int opcao;

        do {
            /*limparTerminal();*/

            System.out.println("\n=== SISTEMA LOJA SOLIDÁRIA ===");
            System.out.println("1. Estoque de doações");
            System.out.println("2. Empréstimos");
            System.out.println("3. Estatísticas");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    menuEstoqueDoacoes();
                    break;
                case 2:
                    menuEmprestimos();
                    break;
                case 3:
                    menuEstatisticas();
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

    // SUBMENU DOAÇÕES //
    private void menuEstoqueDoacoes() {
        int opcao;

        do {
            /*limparTerminal();*/

            System.out.println("\n=== ESTOQUE DE DOAÇÕES ===");
            System.out.println("1. Exibir quantidade total em estoque");
            System.out.println("2. Adicionar quantidade (Entrada)");
            System.out.println("3. Remover quantidade (Saída)");
            System.out.println("4. Listar categorias");
            System.out.println("5. Gerar relatório mensal");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    estoqueDoacoes.exibirEstoqueTotal();
                    break;
                case 2:
                    menuAdicionar();
                    break;
                case 3:
                    menuRemover();
                    break;
                case 4:
                    estoqueDoacoes.listarCategorias();
                    break;
                case 5:
                    menuRelatorio();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida!");
            }

            if (opcao != 0) {
                System.out.println("\nPressione Enter para continuar...");
                scanner.nextLine();
            }

        } while (opcao != 0);
    }

    // SUBMENU EMPRÉSTIMOS //
    private void menuEmprestimos() {
        int opcao;

        do {
            /*limparTerminal();*/

            System.out.println("\n=== MENU DE EMPRÉSTIMOS ===");
            System.out.println("1. Adicionar quantidade ao estoque");
            System.out.println("2. Cadastrar pessoa");
            System.out.println("3. Realizar empréstimo");
            System.out.println("4. Exibir empréstimos ativos");
            System.out.println("5. Pesquisar cadastros");
            System.out.println("6. Devolução de empréstimo");
            System.out.println("7. Exibir estoque de empréstimos");
            System.out.println("8. Excluir cadastro");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    menuAdicionarQuantidadeEmprestimos();
                    break;
                case 2:
                    menuCadastrarPessoa();
                    break;
                case 3:
                    menuRealizarEmprestimo();
                    break;
                case 4:
                    emprestimos.listarEmprestimos();
                    break;
                case 5:
                    menuPesquisarCadastro();
                    break;
                case 6:
                    menuDevolucaoEmprestimo();
                    break;
                case 7:
                    emprestimos.exibirEmprestimos();
                    break;
                case 8:
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida!");
            }

            if (opcao != 0) {
                System.out.println("\nPressione Enter para continuar...");
                scanner.nextLine();
            }

        } while (opcao != 0);
    }

    // SUBMENU ESTATÍSTICAS //
    private void menuEstatisticas() {
        int opcao;

        do {
            /*limparTerminal();*/

            System.out.println("\n=== MENU DE ESTATÍSTICAS ===");
            System.out.println("1. Doações recebidas e repassadas mensalmente em determinado ano");
            System.out.println("2. Demonstrativo mensal de doações recebidas em ordem decrescente");
            System.out.println("3. Demonstrativo mensal de doações repassadas aos beneficiários em ordem decrescente");
            System.out.println("4. Atendimentos realizados mensalmente em determinado ano");
            System.out.println("5. Beneficiários com empréstimo ativo a mais de 10 dias");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    menuDoacoesMensaisNoAno();
                    break;
                case 2:
                    menuDoacoesRecebidasDecrescente();
                    break;
                case 3:
                    menuDoacoesRepassadasDecrescente();
                    break;
                case 4:
                    menuAtendimentosMensais();
                    break;
                case 5:
                    filtraEstatisticas.emprestimosAtivosAcimaDe10Dias();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida!");
            }

            if (opcao != 0) {
                System.out.println("\nPressione Enter para continuar...");
                scanner.nextLine();
            }

        } while (opcao != 0);
    }

    private void menuDoacoesMensaisNoAno() {
        int ano = pedirAno();
        if (ano == -1) return;
        filtraEstatisticas.doacoesMensaisNoAno(ano);
    }
 
    private void menuDoacoesRecebidasDecrescente() {
        int ano = pedirAno();
        if (ano == -1) return;
        filtraEstatisticas.doacoesRecebidasDecrescente(ano);
    }
 
    private void menuDoacoesRepassadasDecrescente() {
        int ano = pedirAno();
        if (ano == -1) return;
        filtraEstatisticas.doacoesRepassadasDecrescente(ano);
    }
 
    private void menuAtendimentosMensais() {
        int ano = pedirAno();
        if (ano == -1) return;
        filtraEstatisticas.atendimentosMensaisNoAno(ano);
    }
 
    /** Solicita e valida um ano. Retorna -1 se inválido. */
    private int pedirAno() {
        System.out.print("Digite o ano: ");
        int ano = scanner.nextInt();
        scanner.nextLine();
        if (ano < 2000 || ano > 2999) {
            System.out.println("Ano inválido!");
            return -1;
        }
        return ano;
    }
    //────────────────────────────────────────────────────────────────────────
 

    private String escolherCategoriaEmprestimo() {
        System.out.println("Escolha o categoria: ");
        System.out.println("1. Masculino");
        System.out.println("2. Feminino");

        int escolha = scanner.nextInt();
        scanner.nextLine();

        if (escolha == 1) {
            return "Masculino";
        } else if (escolha == 2) {
            return "Feminino";
        } else {
            System.out.println("Categoria inválido!");
            return "";
        }
    }

    private void menuAdicionarQuantidadeEmprestimos() {
        System.out.println("\n=== ADICIONAR QUANTIDADE AO ESTOQUE DE EMPRÉSTIMOS ===");

        String categoria = escolherCategoriaEmprestimo();

        if (categoria.isEmpty()) {
            return;
        }

        System.out.print("Digite a quantidade: ");
        int quantidade = scanner.nextInt();
        scanner.nextLine();

        emprestimos.adicionarQuantidadeEmprestimos(categoria, quantidade);
    }

    private void menuCadastrarPessoa() {
        System.out.println("\n=== CADASTRAR PESSOA ===");

        System.out.println("Nome: ");
        String nome = scanner.nextLine();

        System.out.println("CPF: ");
        String cpf = scanner.nextLine();

        System.out.println("Telefone: ");
        String telefone = scanner.nextLine();

        String categoria = escolherCategoriaEmprestimo();

        if (categoria.isEmpty()) {
            return;
        }

        emprestimos.cadastrarPessoa(nome, cpf, telefone, categoria);

    }

    private void menuPesquisarCadastro() {
        System.out.println(" === PESQUISAR CADASTRO ===");
        System.out.println(" Digite o CPF: ");
        String cpf = scanner.nextLine();

        emprestimos.pesquisarCadastro(cpf);

    }

    private void menuRealizarEmprestimo() {
        System.out.println("\n === REALIZAR EMPRÉSTIMO === ");

        System.out.println("CPF: ");
        String cpf = scanner.nextLine();

        System.out.println("Quantidade emprestada: ");
        int quantidade = scanner.nextInt();
        scanner.nextLine();

        emprestimos.registrarEmprestimo(cpf, quantidade);

    }

    private void menuDevolucaoEmprestimo() {
        System.out.println("\n=== DEVOLUÇÃO DE EMPRÉSTIMO ===");

        System.out.print("Digite o CPF: ");
        String cpf = scanner.nextLine();

        emprestimos.registrarDevolucao(cpf);
    }

    // ADICIONA QUANTIDADE DE ESTOQUE DOAÇÕES
    private void menuAdicionar() {
        System.out.println("\n=== ADICIONAR QUANTIDADE (REGISTRAR ENTRADA ESTOQUE) ===");
        String[] categorias = estoqueDoacoes.getCategorias();

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

        estoqueDoacoes.adicionarQuantidade(categorias[escolha - 1], quantidade);
    }

    // REMOVE QUANTIDADE DE ESTOQUE DOAÇÕES
    private void menuRemover() {
        System.out.println("\n=== REMOVER QUANTIDADE (REGISTRAR SAÍDA ESTOQUE) ===");
        String[] categorias = estoqueDoacoes.getCategorias();

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

        estoqueDoacoes.removerQuantidade(categorias[escolha - 1], quantidade);
    }

    private void menuRelatorio() {
        System.out.println("\n=== GERAR RELATÓRIO MENSAL ===");

        System.out.print("Digite o mês: ");
        int mes = scanner.nextInt();
        scanner.nextLine();

        if (mes < 1 || mes > 12) {
            System.out.println("Mês inválido!");
            return;
        }

        System.out.print("Digite o ano: ");
        int ano = scanner.nextInt();
        scanner.nextLine();

        if (ano < 2000 || ano > 2999) {
            System.out.println("Ano inválido!");
            return;
        }

        System.out.print("Observações 1: ");
        String obs1 = scanner.nextLine();

        System.out.print("Observações 2: ");
        String obs2 = scanner.nextLine();

        estoqueDoacoes.gerarRelatorioMensal(mes, ano, obs1, obs2);
    }


    /*public static void limparTerminal() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }*/
}