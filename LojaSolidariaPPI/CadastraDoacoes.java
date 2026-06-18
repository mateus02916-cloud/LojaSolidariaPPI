import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class CadastraDoacoes extends GerenciadorArquivos {

    private static String[] CATEGORIAS = { "Masculinos", "Femininos", "Infantil", "Calçados", "Diversos" };

    private static String ARQUIVO_ESTOQUE = "EstoqueDoacoes.csv";

    @Override
    protected String getNomeArquivo() {
        return ARQUIVO_ESTOQUE;
    }

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public CadastraDoacoes() {
        // Verifica se o arquivo existe
        File arquivo = new File(ARQUIVO_ESTOQUE);
        if (!arquivo.exists()) {
            System.out.println(" Sistema de registros inicializado ");
        }
    }

    @Override
    protected Doacoes converterLinha(String linha) {
        String[] dados = linha.split(",");

        if (dados.length != 4) {
            return null;
        }

        String tipo = dados[0].trim();
        String categoria = dados[1].trim();
        int quantidade = Integer.parseInt(dados[2].trim());
        LocalDate dataEvento = LocalDate.parse(dados[3].trim(), formatter);

        return new Doacoes(tipo, categoria, quantidade, dataEvento);

    }

    protected List<Doacoes> lerListaEstoque() {

        List<Doacoes> lista = new ArrayList<>();

        for (String linha : lerLinhasArquivo(ARQUIVO_ESTOQUE)) {

            Doacoes estoque = converterLinha(linha);

            if (estoque != null) {
                lista.add(estoque);
            }
        }

        return lista;
    }

    // /* protected List<Doacoes> lerListaEstoque() {
    // List<Doacoes> lista = new ArrayList<>();

    // try (BufferedReader br = new BufferedReader(new FileReader(ARQUIVO_ESTOQUE)))
    // {
    // String linha;

    // while ((linha = br.readLine()) != null) {
    // Doacoes estoque = (Doacoes) converterLinha(linha);

    // if (estoque != null) {
    // lista.add(estoque);
    // }

    // }

    // } catch (IOException e) {
    // System.out.println("Erro ao ler estoque " + e.getMessage());

    // }

    // return lista;

    // } */

    private void salvarRegistro(Doacoes novoRegistro) {

        adicionarLinha(ARQUIVO_ESTOQUE, novoRegistro.toString());
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

        Doacoes novoRegistro = new Doacoes("ENTRADA", categoria, quantidade, LocalDate.now());

        salvarRegistro(novoRegistro);

        System.out.println(" (Registrado como ENTRADA no sistema");
        System.out.printf("✓ Adicionadas %,d unidades de %s%n ", quantidade, categoria);

    }

    public void removerQuantidade(String categoria, int quantidade) {

        if (quantidade <= 0) {
            System.out.println("Quantidade deve ser maior que zero!");
            return;
        }

        int estoqueAtual = calcularEstoqueAtual();

        if (estoqueAtual < quantidade) {
            System.out.printf("✗ Estoque insuficiente! Disponível: %,d unidade%n", estoqueAtual);
            return;

        }

        Doacoes novoRegistro = new Doacoes("SAIDA", categoria, quantidade, LocalDate.now());

        salvarRegistro(novoRegistro);

        System.out.printf("✓ Removidas %,d unidades de %s%n", quantidade, categoria);
        System.out.println("  (Registrado como SAÍDA no sistema)");
    }

    // mateus
    private int calcularEstoqueAtual() {
        List<Doacoes> lista = lerListaEstoque();

        int totalEstoque = 0;

        for (Doacoes est : lista) {
            if (est.getTipo().equals("ENTRADA")) {
                totalEstoque += est.getQuantidade();

            } else if (est.getTipo().equals("SAIDA")) {
                totalEstoque -= est.getQuantidade();
            }
        }

        return totalEstoque;

    }

    /*
     * private int calcularEstoqueCategoria(String categoria) {
     * List<Estoque> lista = lerListaEstoque();
     * 
     * int estoqueCategoria = 0;
     * 
     * for (Estoque est : lista) {
     * if (est.getCategoria().equals(categoria)) {
     * if (est.getTipo().equals("ENTRADA")) {
     * estoqueCategoria += est.getQuantidade();
     * 
     * } else if (est.getTipo().equals("SAIDA")) {
     * estoqueCategoria -= est.getQuantidade();
     * }
     * }
     * }
     * 
     * return estoqueCategoria;
     * 
     * }
     */

    public void gerarRelatorioMensal(int mes, int ano, String obs1, String obs2) {
        List<Doacoes> lista = lerListaEstoque();

        System.out.println("\n" + "=".repeat(50));
        System.out.printf("📊 RELATÓRIO MENSAL - %02d/%d%n", mes, ano);
        System.out.println("=".repeat(50));

        if (lista.isEmpty()) {
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

        for (Doacoes est : lista) {
            LocalDate data = est.getDataEvento();

            if (data.getMonthValue() == mes && data.getYear() == ano) {
                String tipo = est.getTipo();
                String categoria = est.getCategoria();
                int quantidade = est.getQuantidade();
                String dataStr = est.getDataFormatada();

                if (tipo.equals("ENTRADA")) {
                    entradasPorCategoria.put(categoria, entradasPorCategoria.getOrDefault(categoria, 0) + quantidade);

                    totalEntradas += quantidade;

                } else if (tipo.equals("SAIDA")) {
                    saidasPorDia.put(dataStr, saidasPorDia.getOrDefault(dataStr, 0) + quantidade);

                    atendimentosPorDia.put(dataStr, atendimentosPorDia.getOrDefault(dataStr, 0) + 1);

                    totalSaidas += quantidade;
                    totalAtendimentos++;
                }
            }
        }

        // Entradas por categoria
        System.out.println("\n📈 DOAÇÕES (ENTRADAS) NO MÊS:");
        System.out.println("-".repeat(35));

        for (String cat : CATEGORIAS) {
            int qtd = entradasPorCategoria.getOrDefault(cat, 0);
            System.out.printf("• %-12s: %,7d unidades%n", cat, qtd);
        }

        System.out.println("-".repeat(35));
        System.out.printf("TOTAL DE ENTRADAS: %,9d unidades%n", totalEntradas);

        // Área de observação 1 (ex: "Foram descartados 474 itens...")
        if (!obs1.isEmpty()) {
            System.out.println("");
            System.out.println("-".repeat(35));
            System.out.println(" OBSERVAÇÕES / DESCARTES:");
            System.out.println(" " + obs1);
            System.out.println("-".repeat(35));
        }

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

                System.out.printf("%-12s | %,15d | %,12d%n", data, itensDia, atendimentosDia);
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
        System.out.println("\n");

        // Área de observação 2 (Ações Externas)
        if (!obs2.isEmpty()) {
            System.out.println("-".repeat(35));
            System.out.println(" AÇÕES EXTERNAS / OUTRAS OBSERVAÇÕES:");
            System.out.println(" [ ] " + obs2);
            System.out.println("-".repeat(35));
        }
        System.out.println("\n");
    }
}