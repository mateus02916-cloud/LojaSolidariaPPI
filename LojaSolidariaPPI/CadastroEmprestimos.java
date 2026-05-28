import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import javax.imageio.IIOException;

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
            //pega a linha do arquivo CSV e conversa em uma objeto da classe Emprestimos
    private Emprestimos converterLinhaParaEmprestimos(String linha){
            String [] i = linha.split(",");

            if (i.length != 7){
                return null;
            }

            String nome = i[0].trim();
            String cpf = i[1].trim();
            String telefone = i[2].trim();
            int quantidade = Integer.parseInt(i[3].trim());
            String categoria = i[4].trim();
            boolean emprestado = Boolean.parseBoolean(i[5].trim());
            LocalDate dataEmprestimo = LocalDate.parse(i[6].trim(), formatter);

            return new Emprestimos(nome, cpf, telefone, quantidade, categoria, emprestado, dataEmprestimo);


    }

    public void salvarListaEmprestimos(List<Emprestimos> lista){
        try (PrintWriter pw = new PrintWriter(new FileWriter(ARQUIVO_EMPRESTIMOS)) ){

            for (Emprestimos emp : lista){
                pw.println(emp.toString());
            }


        }catch (IOException e) {
            System.out.println("Erro ao salvar empréstimos: " + e.getMessage());

        }

        


    }

    private List<Emprestimos> lerListaEmprestimos(){
        List<Emprestimos> lista = new ArrayList<>();

            try (BufferedReader br = new BufferedReader(new FileReader(ARQUIVO_EMPRESTIMOS))){
                String linha;

                while ((linha = br.readLine()) != null) {

                    Emprestimos emp = converterLinhaParaEmprestimos(linha);

                    if (emp != null){
                        lista.add(emp);
                    }
                    
                }



            }catch(IOException e){
                System.out.println("Erro ao ler empréstimos: " + e.getMessage());


            }

            return lista;


    }

    public void exibirEmprestimos() {
        Map<String, Integer> emprestimos = lerArquivoEmprestimos();

        System.out.println("\n=== ESTOQUE DE EMPRÉSTIMOS ===");
        System.out.println("Masculino: " + emprestimos.getOrDefault("Masculino", 0));
        System.out.println("Feminino: " + emprestimos.getOrDefault("Feminino", 0));
    }

    public void adicionarQuantidadeEmprestimos(String categoria, int quantidade) {
        if (quantidade <= 0) {
            System.out.println("Quantidade deve ser maior que zero!");
            return;
        }

        Map<String, Integer> emprestimos = lerArquivoEmprestimos();

        emprestimos.put(categoria, emprestimos.getOrDefault(categoria, 0) + quantidade);

        salvarArquivoEmprestimos(emprestimos);

        System.out.println("✓ Estoque de empréstimos atualizado!");
    }


    public void cadastrarPessoa(String nome, String cpf, String telefone, String categoria){
        if (verificarCadastro(cpf)){
            System.out.println("Pessoa já possui cadastro!");
            return;
        }

        Emprestimos novoCadastro = new Emprestimos(nome,cpf,telefone,0,categoria,false,LocalDate.now());

        try (PrintWriter pw = new PrintWriter(new FileWriter(ARQUIVO_EMPRESTIMOS, true))){
           pw.println(novoCadastro.toString());

            System.out.println("✓ Pessoa cadastrada com sucesso!");


        }catch (IOException e){
            System.out.println("Erro ao cadastrar pessoa" + e.getMessage());
        }


    }


    


    public boolean verificarCadastro(String cpf){
            List<Emprestimos> lista = lerListaEmprestimos();

            for (Emprestimos emp : lista) {
                if (emp.getCpf().equals(cpf)){
                    return true;

                }
            }
           
            return false;

    }

   public void registrarEmprestimo(String cpf, int quantidade) {

    if (quantidade <= 0) {
        System.out.println("Quantidade deve ser maior que zero!");
        return;
    }


    List<Emprestimos> lista = lerListaEmprestimos();
    Map<String, Integer> estoqueEmprestimos = lerArquivoEmprestimos();

    boolean encontrado = false;
    boolean realizado = false;

    for (Emprestimos emp : lista){
        if (emp.getCpf().equals(cpf)){
            encontrado = true;
            
            if (emp.isEmprestado()){
                System.out.println("Pessoa já possui empréstimo ativo.");
                break;
            }

            String categoria = emp.getCategoria();
            int estoqueAtual = estoqueEmprestimos.getOrDefault(categoria, 0);

            if (estoqueAtual < quantidade) {
                System.out.println("Estoque insuficiente para a categoria: " + categoria);
                break;
            }

            estoqueEmprestimos.put(categoria, estoqueAtual - quantidade);

            emp.setQuantidade(quantidade);
            emp.setEmprestado(true);
            emp.setDataEmprestimo(LocalDate.now());

            realizado = true;

            System.out.println("✓ Empréstimo realizado para " + emp.getNome());
            break;

        }
    }

        if (!encontrado){
            System.out.println("Cadastro não encontrado! ");
            return;
        }

        if (realizado) {
            salvarListaEmprestimos(lista);
            salvarArquivoEmprestimos(estoqueEmprestimos);
        }


   
}

    public void listarEmprestimos() {
            List <Emprestimos> lista = lerListaEmprestimos();

            System.out.println("\n === EMPRÉSTIMOS ATIVOS === ");

            boolean vazio = true;

            for (Emprestimos emp : lista){
                  if (emp.isEmprestado()){
                    System.out.printf("Nome: %s | CPF: %s | Telefone: %s | Qtd: %d | Categoria: %s | Data: %s%n", 
                    emp.getNome(), emp.getCpf(), emp.getTelefone(), emp.getQuantidade(), emp.getCategoria(), emp.getDataFormatada());
                    vazio = false;
                  }  

            }
            if (vazio) {
                System.out.println("Nenhum empréstimo ativo!");
            }



    }

    public void registrarDevolucao(String cpf) {
        List<Emprestimos> lista = lerListaEmprestimos();
        Map<String, Integer> estoqueEmprestimos = lerArquivoEmprestimos();

        boolean encontrado = false;

        for (Emprestimos emp : lista){
            if (emp.getCpf().equals(cpf) && emp.isEmprestado()){
                emp.setEmprestado(false);

                estoqueEmprestimos.put(emp.getCategoria(),estoqueEmprestimos.getOrDefault(emp.getCategoria(), 0) + emp.getQuantidade());

                System.out.println("✓ Devolução de " + emp.getNome());

                encontrado = true;
                break;

            }


        }

        salvarListaEmprestimos(lista);
        salvarArquivoEmprestimos(estoqueEmprestimos);

        if (!encontrado){
            System.out.println("CPF não encontrado ou empréstimo já devolvido!" );
        }
    }

     

        public void pesquisarCadastro(String cpf){
                List<Emprestimos> lista = lerListaEmprestimos();

                boolean encontrado = false;

                for (Emprestimos emp : lista){

                    if (emp.getCpf().equals(cpf)){

                    System.out.println("\n === CADASTRO ENCONTRADO === ");
                    System.out.println("Nome:  " + emp.getNome());
                    System.out.println("CPF:  " + emp.getCpf());
                    System.out.println("Telefone:  " + emp.getTelefone());
                    System.out.println("Categoria: " + emp.getCategoria());
                    System.out.println("Quantidade emprestada:  " + emp.getQuantidade());
                    System.out.println("Empréstimo ativo:  " + emp.isEmprestado());
                    System.out.println("Data:  " + emp.getDataFormatada());

                    encontrado = true;
                    break;


                    }

                }
                 if (!encontrado){
                    System.out.println("Cadastro não encontrado!");
                }
                
           
    }

    /* 

    public void excluirCadasgro (String cpf){
        List <Emprestimos> lista = lerListaEmprestimos();

        boolean encontrado = false;

        Iterator<Emprestimos> iterator = lista.iterator();

        while (iterator.hasNext()) {

            Emprestimos emp = iterator.next();

            if (emp.getCpf().equals(cpf)){

                encontrado = true;

                if (emp.isEmprestado()) {
                    System.out.println("Não é possível excluir!");
                    System.out.println("A pessoa possui empréstimo ativo.");
                    return;


                }

                iterator.remove();

                salvarListaEmprestimos(lista);

                System.out.println("✓ Cadastro excluído com sucesso!");

            }
            
        }
            




    }

               
            
*/
   


}