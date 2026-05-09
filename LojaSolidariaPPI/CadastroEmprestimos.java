import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class CadastroEmprestimos {

    private static String ARQUIVO_EMPRESTIMOS = "Emprestimos.csv";
    private static String ARQUIVO_ESTOQUE_EMPRESTIMOS = "EstoqueParaEmprestimos.csv";

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private Map<String, Integer> lerArquivoEmprestimos() {
        Map<String, Integer> emprestimos = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(ARQUIVO_ESTOQUE_EMPRESTIMOS))) {
            String linha;

            while ((linha = br.readLine()) != null) {
                String[] i = linha.split(",");

                if (i.length == 2) {
                    emprestimos.put(i[0].trim(), Integer.parseInt(i[1].trim()));
                }
            }

        } catch (IOException e) {
            System.out.println("Erro ao ler estoque de empréstimos: " + e.getMessage());
        }

        return emprestimos;
    }

    private void salvarArquivoEmprestimos(Map<String, Integer> emprestimos) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(ARQUIVO_ESTOQUE_EMPRESTIMOS))) {
            pw.println("Masculino," + emprestimos.getOrDefault("Masculino", 0));
            pw.println("Feminino," + emprestimos.getOrDefault("Feminino", 0));

        } catch (IOException e) {
            System.out.println("Erro ao salvar estoque de empréstimos: " + e.getMessage());
        }
    }

    public void exibirEmprestimos() {
        Map<String, Integer> emprestimos = lerArquivoEmprestimos();

        System.out.println("\n=== ESTOQUE DE EMPRÉSTIMOS ===");
        System.out.println("Masculino: " + emprestimos.getOrDefault("Masculino", 0));
        System.out.println("Feminino: " + emprestimos.getOrDefault("Feminino", 0));
    }

    public void adicionarQuantidadeEmprestimos(String tipo, int quantidade) {
        if (quantidade <= 0) {
            System.out.println("Quantidade deve ser maior que zero!");
            return;
        }

        Map<String, Integer> emprestimos = lerArquivoEmprestimos();

        emprestimos.put(tipo, emprestimos.getOrDefault(tipo, 0) + quantidade);

        salvarArquivoEmprestimos(emprestimos);

        System.out.println("✓ Estoque de empréstimos atualizado!");
    }

    public void registrarEmprestimo(String nome, String cpf, String tipo, int quantidade) {
        Map<String, Integer> emprestimos = lerArquivoEmprestimos();

        int atual = emprestimos.getOrDefault(tipo, 0);

        if (quantidade <= 0 || atual < quantidade) {
            System.out.println("Estoque insuficiente!");
            return;
        }
        //retira quantidade do que tem em estoque
        emprestimos.put(tipo, atual - quantidade);
        salvarArquivoEmprestimos(emprestimos);

         //atualiza as imformações no arquivo
        try (PrintWriter pw = new PrintWriter(new FileWriter(ARQUIVO_EMPRESTIMOS, true))) {
            String data = LocalDate.now().format(formatter);
            pw.println(nome + "," + cpf + "," + quantidade + "," + tipo + ",true," + data);

            System.out.println("✓ Empréstimo registrado com sucesso!");

        } catch (IOException e) {
            System.out.println("Erro ao salvar empréstimo: " + e.getMessage());
        }
    }

    public void cadastrarPessoa(String nome, String cpf, String tipo){
        if (verificarCadastro(cpf)){
            System.out.println("Pessoa já possui cadastro!");
            return;
        }

        try (PrintWriter pw = new PrintWriter(new FileWriter(ARQUIVO_EMPRESTIMOS, true))){
            String data = LocalDate.now().format(formatter);

            pw.println(nome + "," + cpf + ",0," + tipo + ",false," + data);

            System.out.println("✓ Pessoa cadastrada com sucesso!");


        }catch (IOException e){
            System.out.println("Erro ao cadastrar pessoa" + e.getMessage());
        }


    }


    public boolean verificarCadastro(String cpf){
        try (BufferedReader br = new BufferedReader(new FileReader(ARQUIVO_EMPRESTIMOS))){

            String linha;

            while ((linha = br.readLine()) != null) {
                String [] i = linha.split(",");

                if (i.length == 6){

                    String cpfArq = i[1].trim();
                    
                    if (cpfArq.equals(cpf)){
                        return true;
                    }


                }
                
            }

        }catch (IOException e){

        }

        return false;


    }

    public void registrarEmprestimo(String cpf, int quantidade){
        
        if (quantidade <= 0){

        System.out.println("Quantidade deve ser maior que zero!");
            return;
        }
        List<String> linhas = new ArrayList<>();
        Map<String, Integer> emprestimos = lerArquivoEmprestimos();

        boolean encontrado = false;
        boolean realizado = false;

        try (BufferedReader br = new BufferedReader(new FileReader(ARQUIVO_EMPRESTIMOS))){
            String linha;

            while ((linha = br.readLine()) != null){
                String [] i = linha.split (",");

                if (i.length == 6){
                    String nome = i[0];
                    String cpfArq = i[1];
                    int qtdAtual = Integer.parseInt(i[2].trim());
                    String tipo = i [3];
                    boolean ativo = Boolean.parseBoolean(i[4].trim());
                    String data = i [5];

                    if (cpfArq.equals(cpf)){
                        encontrado = true;

                        if (ativo){
                            System.out.println("Pessoa já possui empréstimo ativo.");
                                linhas.add(linha);
                                continue;
                        }

                        int estoqueAtual = emprestimos.getOrDefault(tipo, 0);

                        if (estoqueAtual < quantidade) {
                            System.err.println("Estoque insuficiente para o tipo: " + tipo);
                            linhas.add(linha);
                            continue;
                        }

                    }

                }


            }


        }
    
    }

    public void listarEmprestimos() {
        System.out.println("\n=== EMPRÉSTIMOS ATIVOS ===");

        try (BufferedReader br = new BufferedReader(new FileReader(ARQUIVO_EMPRESTIMOS))) {
            String linha;
            boolean vazio = true;

            while ((linha = br.readLine()) != null) {
                String[] i = linha.split(",");

                if (i.length == 6 && Boolean.parseBoolean(i[4].trim())) {
                    System.out.printf(
                        "Nome: %s | CPF: %s | Qtd: %s | Tipo: %s | Data: %s%n",
                        i[0], i[1], i[2], i[3], i[5]
                    );

                    vazio = false;
                }
            }

            if (vazio) {
                System.out.println("Nenhum empréstimo ativo.");
            }

        } catch (IOException e) {
            System.out.println("Erro ao ler empréstimos: " + e.getMessage());
        }
    }

    public void registrarDevolucao(String cpf) {
        List<String> linhas = new ArrayList<>();
        Map<String, Integer> emprestimos = lerArquivoEmprestimos();

        boolean encontrado = false;

        try (BufferedReader br = new BufferedReader(new FileReader(ARQUIVO_EMPRESTIMOS))) {
            String linha;

            while ((linha = br.readLine()) != null) {
                String[] i = linha.split(",");

                if (i.length == 6) {
                    String nome = i[0];
                    String cpfArq = i[1];
                    int qtd = Integer.parseInt(i[2].trim());
                    String tipo = i[3];
                    boolean ativo = Boolean.parseBoolean(i[4].trim());
                    String data = i[5];

                    if (cpfArq.equals(cpf) && ativo) {
                        ativo = false;
                        encontrado = true;

                        emprestimos.put(tipo, emprestimos.getOrDefault(tipo, 0) + qtd);

                        System.out.println("✓ Devolução de " + nome);
                    }

                    linhas.add(nome + "," + cpfArq + "," + qtd + "," + tipo + "," + ativo + "," + data);
                }
            }

        } catch (IOException e) {
            System.out.println("Erro ao processar devolução: " + e.getMessage());
            return;
        }
            //atualiza o arquivo de de emprestimos devolve o emprestimo 
        try (PrintWriter pw = new PrintWriter(new FileWriter(ARQUIVO_EMPRESTIMOS))) {
            for (String linha : linhas) {
                pw.println(linha);
            }
        } catch (IOException e) {
            System.out.println("Erro ao atualizar empréstimos: " + e.getMessage());
        }
        //atualiza oq tem em estoque
        salvarArquivoEmprestimos(emprestimos);

        if (!encontrado) {
            System.out.println("CPF não encontrado ou empréstimo já devolvido.");
        }
    }
}