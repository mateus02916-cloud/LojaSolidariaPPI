import java.io.*;
import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Estoque {
    private List<Itens> itens;
    private static final String ARQUIVO_CSV = "Estoque.csv";

    private List<Retiradas> retiradas;
    private static final String RELATORIO_CSV = "Relatorio.csv";

    private static final String RetiradasPorDia_CSV = "RetiradasPorDia.csv";
    private List<RetiradasPorDia> retiradasPorDia;

    private static final String[] CATEGORIAS = { "Masculinos", "Femininos", "Infantil", "Calçados", "Diversos" };
    
    public Estoque() {
        itens = new ArrayList<>();
        lerEstoque();
        addCategoriasPadrao();
    }

   
    public String[] getCategorias() {
        return CATEGORIAS;
    }

    
    private void addCategoriasPadrao() {
        for (String nome : CATEGORIAS) {
            if (!buscarItemPorNome(nome)) {
                itens.add(new Itens(nome, 0));
            }
        }
        salvarEstoque();
    }

    private boolean buscarItemPorNome(String nome) {
        for (Itens item : itens) {
            if (item.getCategoria().equalsIgnoreCase(nome)) {
                return true;
            }
        }
        return false;
    }

    private void lerEstoque() {
        File arquivo = new File(ARQUIVO_CSV);
        if (!arquivo.exists()) return; 

        try (BufferedReader br = new BufferedReader(new FileReader(ARQUIVO_CSV))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(",");
                if (dados.length == 2) {
                    String categoria = dados[0].trim();
                    int quantidade = Integer.parseInt(dados[1].trim());
                    itens.add(new Itens(categoria, quantidade));
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar dados: " + e.getMessage());
        }
    }

    public void salvarEstoque() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(ARQUIVO_CSV))) {
            for (Itens item : itens) {
                pw.println(item.toString());
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar arquivo: " + e.getMessage()); 
        }
    }

    public void exibirEstoque() {
        System.out.println("\n=== ESTOQUE ATUAL ===");
        for (Itens item : itens) {
            System.out.println(item.getCategoria() + ": " + item.getQuantidade() + " unidades"); 
        }
    }

    public void listarCategorias() {
        System.out.println("\n=== CATEGORIAS DISPONÍVEIS ===");
        for (String categoria : CATEGORIAS) {
            System.out.println("- " + categoria);
        }
    }

    public void adicionarQuantidade(String categoria, int quantidade) {
        for (Itens item : itens) {
            if (item.getCategoria().equalsIgnoreCase(categoria)) {
                item.setQuantidade(item.getQuantidade() + quantidade);
                salvarEstoque();
                System.out.println("Adicionado com sucesso!");
                return;
            }
        }
    }

    public void removerQuantidade(String categoria, int quantidade) {
        for (Itens item : itens) {
            if (item.getCategoria().equalsIgnoreCase(categoria)) {
                if (item.getQuantidade() >= quantidade) { 
                    item.setQuantidade(item.getQuantidade() - quantidade);
                    salvarEstoque();
                    System.out.println("Removido com sucesso!");
                } else {
                    System.out.println("Erro: Estoque insuficiente (" + item.getQuantidade() + " disponíveis).");
                }
                return;
            }
        }
    }


    public boolean comparaData(List<Retiradas> retiradas) {
        int totalRetiradasDia = 0;
        int temp = 0;
        String data = String.valueOf(retiradas.get(0)); 
        String[] partes = data.split("/");             
        String dia = partes[0];   
        
        for (Retiradas retirada : retiradas) {
            int i = 1;
            while (i <=31) {
                if (dia.equals(String.valueOf(i))){
                    temp = totalRetiradasDia += totalRetiradasDia;

                    totalRetiradasDia = temp;

                    retiradasPorDia.add(new retiradasPorDia(totalRetiradasDia));
                }
            }
            
        }

        //percorre a lista de retirada e compara as datas
        //tera que ter um contador de entradas
    }

    public void getData(String opcaoData) {
        LocalDate capturaData;
        DateTimeFormatter formata = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        if (opcaoData.equals("1")) {
            capturaData = LocalDate.now();
            String data = capturaData.format(formata);
            
        }  else {

            try {
                capturaData = LocalDate.parse(opcaoData, formata);
                String data = capturaData.format(formata);
            } catch (DateTimeParseException e) {
                System.out.println("A data '" + opcaoData + "' NÃO é válida no formato dd/MM/yyyy.");
            }
        }

    }

    

    public void gerarRelatorioMensal(int mes, int ano) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String mesAno = String.format("%02d/%d", mes, ano);
        System.out.println("\n=== RELATÓRIO DE ESTOQUE - " + mesAno + " ===");
        exibirEstoque();

        

        File relatorio = new File(RELATORIO_CSV);
        try (BufferedReader br = new BufferedReader(new FileReader(RELATORIO_CSV))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] retirada = linha.split(",");

                if (retirada.length == 3) {
                    String dia = retirada[0].trim();
                    int itens_saida = Integer.parseInt(retirada[1].trim());
                    itens.add(new Itens(dia, itens_saida));

                }
            }
        }
        catch (IOException e) {

            System.out.println("Erro ao carregar dados do relatório: " + e.getMessage());
        }
    }

    public void salvarRelatorio() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(RELATORIO_CSV))) {
            for (Itens item : itens) {
                pw.println(item.toString());
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar arquivo: " + e.getMessage()); 
        }
    }

    
}