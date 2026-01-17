import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;


public class Estoque {
    private static String ARQUIVO_REGISTROS = "Registros.csv";
    private static String[] CATEGORIAS = { "Masculinos", "Femininos", "Infantil", "Calçados", "Diversos" };
    
    public Estoque() {
        // Verifica se o arquivo existe
        File arquivo = new File(ARQUIVO_REGISTROS);
        if (!arquivo.exists()) {
            System.out.println(" Sistema de registros inicializado ");
        }
    }
    
    private void salvarRegistro(String tipo, String categoria, int quantidade) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(ARQUIVO_REGISTROS, true))) {
                Registro novoRegistro = new Registro (tipo, categoria, quantidade, LocalDate.now());
                pw.println(novoRegistro.toString());
            

        } catch (IOException e) {
            System.out.println("Erro ao salvar registro: " + e.getMessage());
        }
    }
    
    public String[] getCategorias() {
        return CATEGORIAS;
    }
    
   public void exibirEstoqueTotal() {
    int total = calcularEstoqueAtual();
    
    
    
    
    System.out.println("\n" + "=".repeat(40));
    System.out.println("📦 QUANTIDADE EM ESTOQUE TOTAL");
    System.out.println("=".repeat(40));
    
    System.out.printf("TOTAL == %,d unidades%n", total);
    System.out.println("=".repeat(40));
}
    
    public void listarCategorias() {
        System.out.println("\n=== CATEGORIAS DISPONÍVEIS ===");
        for (int i = 0; i < CATEGORIAS.length; i++) {
            System.out.println((i + 1) + ". " + CATEGORIAS[i]);
        }
    }
    
    public void adicionarQuantidade(String categoria, int quantidade) {
        if (quantidade <= 0) {
            System.out.println("Quantidade deve ser maior que zero!");
            return;
        }
        
        // Salvar como ENTRADA no registro
        salvarRegistro("ENTRADA", categoria, quantidade);
        
        System.out.printf("✓ Adicionadas %,d unidades de %s%n", quantidade, categoria);
        System.out.println("  (Registrado como ENTRADA no sistema)");
    }
    
    public void removerQuantidade(String categoria, int quantidade) {
        
        if (quantidade <= 0) {
            System.out.println("Quantidade deve ser maior que zero!");
            return;
        }
        
        // Primeiro verificar se tem estoque suficiente
        int estoqueAtual = calcularEstoqueAtual();
        if (estoqueAtual < quantidade) {
            System.out.printf("✗ Estoque insuficiente! Disponível: %,d unidades%n", estoqueAtual);
            return;
        }
        
        // Salvar como SAIDA no registro
        salvarRegistro("SAIDA", categoria, quantidade);
        
        System.out.printf("✓ Removidas %,d unidades de %s%n", quantidade, categoria);
        System.out.println("  (Registrado como SAÍDA no sistema)");
    }
    
    private int calcularEstoqueAtual() {
        int totalEstoque = 0;
        
        File arquivo = new File(ARQUIVO_REGISTROS);
        if (!arquivo.exists()) return 0;
        
        try (BufferedReader br = new BufferedReader(new FileReader(ARQUIVO_REGISTROS))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(",");
                if (dados.length == 4) {
                    String tipo = dados[0].trim();
                    int quantidade = Integer.parseInt(dados[2].trim());
                    
                    if (tipo.equals("ENTRADA")) {
                        totalEstoque += quantidade;
                    } else if (tipo.equals("SAIDA")) {
                        totalEstoque -= quantidade;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao calcular estoque: " + e.getMessage());
        }
        
        return totalEstoque;
    }
    
    public void gerarRelatorioMensal(int mes, int ano) {
        System.out.println("\n" + "=".repeat(50));
        System.out.printf("📊 RELATÓRIO MENSAL - %02d/%d%n", mes, ano);
        System.out.println("=".repeat(50));
        
        File arquivo = new File(ARQUIVO_REGISTROS);
        if (!arquivo.exists()) {
            System.out.println("Nenhum registro encontrado!");
            return;
        }
        
        // Calcular entradas do mês por categoria
        Map<String, Integer> entradasPorCategoria = new HashMap<>();
        int totalEntradas = 0;
        
        // Calcular saídas por dia
        Map<String, Integer> saidasPorDia = new TreeMap<>();
        Map<String, Integer> atendimentosPorDia = new HashMap<>();
        int totalSaidas = 0;
        int totalAtendimentos = 0;
        
        try (BufferedReader br = new BufferedReader(new FileReader(ARQUIVO_REGISTROS))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(",");
                if (dados.length == 4) {
                    String tipo = dados[0].trim();
                    String categoria = dados[1].trim();
                    int quantidade = Integer.parseInt(dados[2].trim());
                    String dataStr = dados[3].trim();
                    
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    LocalDate data = LocalDate.parse(dataStr, formatter);
                    
                    // Verificar se é do mês e ano solicitado
                    if (data.getMonthValue() == mes && data.getYear() == ano) {
                        if (tipo.equals("ENTRADA")) {
                            entradasPorCategoria.put(categoria, 
                                entradasPorCategoria.getOrDefault(categoria, 0) + quantidade);
                            totalEntradas += quantidade;
                        } else if (tipo.equals("SAIDA")) {
                            // Somar itens saídos por dia
                            saidasPorDia.put(dataStr, 
                                saidasPorDia.getOrDefault(dataStr, 0) + quantidade);
                            
                            // Contar atendimentos (1 por saída)
                            atendimentosPorDia.put(dataStr, 
                                atendimentosPorDia.getOrDefault(dataStr, 0) + 1);
                            
                            totalSaidas += quantidade;
                            totalAtendimentos++;
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao gerar relatório: " + e.getMessage());
        }
        
        // Entradas por categoria
        System.out.println("\n📈 ENTRADAS (RECEBIDOS) NO MÊS:");
        System.out.println("-".repeat(35));
        
        for (String cat : CATEGORIAS) {
            int qtd = entradasPorCategoria.getOrDefault(cat, 0);
            System.out.printf("• %-12s: %,7d unidades%n", cat, qtd);
        }
        
        System.out.println("-".repeat(35));
        System.out.printf("TOTAL DE ENTRADAS: %,9d unidades%n", totalEntradas);
        
        // Saídas por dia
        System.out.println("\n📅 SAÍDAS POR DIA NO MÊS:");
        System.out.println("-".repeat(35));
        System.out.printf("%-12s | %-15s | %-12s%n", "DATA", "ITENS SAÍDOS", "ATENDIMENTOS");
        System.out.println("-".repeat(35));
        
        if (saidasPorDia.isEmpty()) {
            System.out.println("Nenhuma saída registrada neste mês.");
        } else {
            for (Map.Entry<String, Integer> entry : saidasPorDia.entrySet()) {
                String data = entry.getKey();
                int itensDia = entry.getValue();
                int atendimentosDia = atendimentosPorDia.getOrDefault(data, 0);
                
                System.out.printf("%-12s | %,15d | %,12d%n", 
                                data, itensDia, atendimentosDia);
            }
        }
        
        System.out.println("-".repeat(35));
        System.out.printf("TOTAL DE SAÍDAS: %,11d unidades%n", totalSaidas);
        System.out.printf("TOTAL ATENDIMENTOS: %,8d%n", totalAtendimentos);
        
        // Estoque atual
        int estoqueAtual = calcularEstoqueAtual();
        System.out.println("\n💼 ESTOQUE ATUAL (Geral):");
        System.out.println("-".repeat(35));
        System.out.printf("Quantidade total em estoque == %,d unidades%n", estoqueAtual);
    }
}