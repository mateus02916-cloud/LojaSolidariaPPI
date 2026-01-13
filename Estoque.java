import java.io.*;
import java.util.*;

public class Estoque {
    private List<Itens> itens;
    private static final String ARQUIVO_CSV = "Estoque.csv";
    private static final String[] CATEGORIAS = { "Masculinos", "Femininos", "Infantil", "Calçados", "Diversos" };
    
    public Estoque() {
        itens = new ArrayList<>();
        lerEstoque();
        addCategoriasPadrao();
        gerarRelatorioMensal(0, 0);
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
}