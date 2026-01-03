import java.io.*;
import java.util.*;

public class Estoque {
    private List<Itens> itens;
    private static final String ARQUIVO_CSV = "estoque.csv";
    private static final String[] CATEGORIAS = { "Masculinos", "Femininos", "Infantil", "Calçados", "Diversos" };
    
    public Estoque() {
        itens = new ArrayList<>();
        lerEstoque(); 
    }

    private void lerEstoque() {
        try (BufferedReader br = new BufferedReader(new FileReader(ARQUIVO_CSV))) {
            String linhas;
            while ((linhas = br.readLine()) != null) {
                String[] dados = linhas.split(",");
                if (dados.length == 2) {
                    String categoria = dados[0].trim();
                    int quantidade = Integer.parseInt(dados[1].trim());
                    itens.add(new Itens(categoria, quantidade));
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }
    }

    public void salvarEstoque() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(ARQUIVO_CSV))) {
            for (Itens item : itens) {
                pw.println(item.toString());
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar o arquivo: " + e.getMessage()); 
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

    public boolean categoriaExiste(String categoria) {
        for (String cat : CATEGORIAS) {
            if (cat.equalsIgnoreCase(categoria)) {
                return true;
            }
        }
        return false;
    }

    public void adicionarQuantidade(String categoria, int quantidade) {
        for (Itens item : itens) {
            if (item.getCategoria().equalsIgnoreCase(categoria)) {
                int novaQuantidade = item.getQuantidade() + quantidade;
                item.setQuantidade(novaQuantidade);
                salvarEstoque();
                System.out.println("Quantidade adicionada com sucesso!");
                System.out.println("Novo total de " + categoria + ": " + novaQuantidade);
                return;
            }
        }
        System.out.println("Categoria não encontrada!");
    }

    public void removerQuantidade(String categoria, int quantidade) {
        for (Itens item : itens) {
            if (item.getCategoria().equalsIgnoreCase(categoria)) {
                if (item.getQuantidade() >= quantidade) { 
                    int novaQuantidade = item.getQuantidade() - quantidade;
                    item.setQuantidade(novaQuantidade);
                    salvarEstoque();
                    System.out.println("Quantidade removida com sucesso!");
                    System.out.println("Novo total de " + categoria + ": " + novaQuantidade);
                } else {
                    System.out.println("Quantidade insuficiente em estoque!");
                }
                return;
            }
        }
        System.out.println("Categoria não encontrada!");
    }
}