/*
1. Doações recebidas e repassadas mensalmente em determinado ano.
2. Demonstrativo mensal de doações recebidas em ordem decrescente.
3. Demonstrativo mensal de doações repassadas aos beneficiários em ordem decrescente
4. Atendimentos realizados mensalmente em determinado ano
5. Beneficiários com empréstimo ativo a mais de 10 dias.
*/

import java.time.LocalDate;
import java.util.*;

public class FiltraEstatisticas {

    
    private CadastroDoacoes cadastroDoacoes;
    private CadastroEmprestimos cadastroEmprestimos;

    public FiltraEstatisticas() {
        cadastroDoacoes = new CadastroDoacoes();
        cadastroEmprestimos = new CadastroEmprestimos();
    }

    // 1. Doações recebidas e repassadas mensalmente em determinado ano
    public void doacoesMensaisNoAno(int ano) {
        List<Doacoes> lista = cadastroDoacoes.lerListaEstoque();; 

        System.out.println("\n" + "=".repeat(55));
        System.out.printf("📊 DOAÇÕES RECEBIDAS E REPASSADAS POR MÊS — %d%n", ano);
        System.out.println("=".repeat(55));
        System.out.printf("%-6s | %17s | %17s%n", "MÊS", "ENTRADAS (Receb.)", "SAÍDAS (Repass.)");
        System.out.println("-".repeat(55));

        // Acumula por mês
        Map<Integer, Integer> entradasPorMes = new TreeMap<>();
        Map<Integer, Integer> saidasPorMes   = new TreeMap<>();

        for (Doacoes est : lista) {
            if (est.getData().getYear() != ano) continue;

            int mes = est.getData().getMonthValue();

            if (est.getTipo().equalsIgnoreCase("ENTRADA")) {
                entradasPorMes.merge(mes, est.getQuantidade(), Integer::sum);
            } else if (est.getTipo().equalsIgnoreCase("SAIDA")) {
                saidasPorMes.merge(mes, est.getQuantidade(), Integer::sum);
            }
        }

        String[] nomesMeses = {
            "Jan","Fev","Mar","Abr","Mai","Jun",
            "Jul","Ago","Set","Out","Nov","Dez"
        };

        int totalEntradas = 0, totalSaidas = 0;

        for (int mes = 1; mes <= 12; mes++) {
            int e = entradasPorMes.getOrDefault(mes, 0);
            int s = saidasPorMes.getOrDefault(mes,   0);
            totalEntradas += e;
            totalSaidas   += s;
            System.out.printf("%-6s | %,17d | %,17d%n", nomesMeses[mes - 1], e, s);
        }

        System.out.println("-".repeat(55));
        System.out.printf("%-6s | %,17d | %,17d%n", "TOTAL", totalEntradas, totalSaidas);
        System.out.println("=".repeat(55));
    }
    
    // 2. Doações RECEBIDAS mensalmente em ordem decrescente
    public void doacoesRecebidasDecrescente(int ano) {
        List<Doacoes> lista = cadastroDoacoes.lerListaEstoque();

        Map<Integer, Integer> entradasPorMes = new HashMap<>();

        for (Doacoes est : lista) {
            if (est.getDataEvento().getYear() != ano) continue;
            if (!est.getTipo().equalsIgnoreCase("ENTRADA")) continue;

            int mes = est.getDataEvento().getMonthValue();
            entradasPorMes.merge(mes, est.getQuantidade(), Integer::sum);
        }

        List<Map.Entry<Integer, Integer>> ordenado = new ArrayList<>(entradasPorMes.entrySet());
        ordenado.sort((a, b) -> b.getValue().compareTo(a.getValue())); 

        String[] nomesMeses = {
            "Janeiro","Fevereiro","Março","Abril","Maio","Junho",
            "Julho","Agosto","Setembro","Outubro","Novembro","Dezembro"
        };

        System.out.println("\n" + "=".repeat(45));
        System.out.printf("📈 DOAÇÕES RECEBIDAS EM %d — DECRESCENTE%n", ano);
        System.out.println("=".repeat(45));

        if (ordenado.isEmpty()) {
            System.out.println("Nenhum registro encontrado para " + ano + ".");
        } else {
            int pos = 1;
            for (Map.Entry<Integer, Integer> entry : ordenado) {
                System.out.printf("%2dº %-12s: %,d unidades%n",
                    pos++, nomesMeses[entry.getKey() - 1], entry.getValue());
            }
        }
        System.out.println("=".repeat(45));
    }

    // 3. Doações REPASSADAS mensalmente em ordem decrescente
    
    public void doacoesRepassadasDecrescente(int ano) {
        List<Doacoes> lista = cadastroDoacoes.lerListaEstoque();

        Map<Integer, Integer> saidasPorMes = new HashMap<>();

        for (Doacoes est : lista) {
            if (est.getDataEvento().getYear() != ano) continue;
            if (!est.getTipo().equalsIgnoreCase("SAIDA")) continue;

            int mes = est.getDataEvento().getMonthValue();
            saidasPorMes.merge(mes, est.getQuantidade(), Integer::sum);
        }

        List<Map.Entry<Integer, Integer>> ordenado = new ArrayList<>(saidasPorMes.entrySet());
        ordenado.sort((a, b) -> b.getValue().compareTo(a.getValue())); // decrescente

        String[] nomesMeses = {
            "Janeiro","Fevereiro","Março","Abril","Maio","Junho",
            "Julho","Agosto","Setembro","Outubro","Novembro","Dezembro"
        };

        System.out.println("\n" + "=".repeat(47));
        System.out.printf("📦 DOAÇÕES REPASSADAS EM %d — DECRESCENTE%n", ano);
        System.out.println("=".repeat(47));

        if (ordenado.isEmpty()) {
            System.out.println("Nenhum registro encontrado para " + ano + ".");
        } else {
            int pos = 1;
            for (Map.Entry<Integer, Integer> entry : ordenado) {
                System.out.printf("%2dº %-12s: %,d unidades%n",
                    pos++, nomesMeses[entry.getKey() - 1], entry.getValue());
            }
        }
        System.out.println("=".repeat(47));
    }

    // 4. Atendimentos realizados mensalmente em um ano
    public void atendimentosMensaisNoAno(int ano) {
        List<Doacoes> lista = cadastroDoacoes.lerListaEstoque();

        // Cada registro de SAIDA = 1 atendimento (conforme lógica de gerarRelatorioMensal)
        Map<Integer, Integer> atendimentosPorMes = new TreeMap<>();

        for (Doacoes est : lista) {
            if (est.getDataEvento().getYear() != ano) continue;
            if (!est.getTipo().equalsIgnoreCase("SAIDA")) continue;

            int mes = est.getDataEvento().getMonthValue();
            atendimentosPorMes.merge(mes, 1, Integer::sum);
        }

        String[] nomesMeses = {
            "Jan","Fev","Mar","Abr","Mai","Jun",
            "Jul","Ago","Set","Out","Nov","Dez"
        };

        System.out.println("\n" + "=".repeat(40));
        System.out.printf("👥 ATENDIMENTOS MENSAIS EM %d%n", ano);
        System.out.println("=".repeat(40));

        int total = 0;
        for (int mes = 1; mes <= 12; mes++) {
            int qtd = atendimentosPorMes.getOrDefault(mes, 0);
            total += qtd;
            System.out.printf("%-6s: %,d atendimentos%n", nomesMeses[mes - 1], qtd);
        }

        System.out.println("-".repeat(40));
        System.out.printf("TOTAL: %,d atendimentos%n", total);
        System.out.println("=".repeat(40));
    }

    // 5. Beneficiários com empréstimo ativo há mais de 10 dias
    public void emprestimosAtivosAcimaDe10Dias() {

        List<Emprestimos> lista = cadastroEmprestimos.lerListaEmprestimos();;

        LocalDate hoje = LocalDate.now();

        System.out.println("\n" + "=".repeat(60));
        System.out.println("⏰ EMPRÉSTIMOS ATIVOS HÁ MAIS DE 10 DIAS");
        System.out.println("=".repeat(60));
        System.out.printf("%-20s | %-14s | %-12s | %s%n",
            "NOME", "CPF", "DATA EMPR.", "DIAS");
        System.out.println("-".repeat(60));

        boolean encontrou = false;

        for (Emprestimos emp : lista) {
            if (!emp.isEmprestado()) continue;

            long dias = java.time.temporal.ChronoUnit.DAYS.between(
                emp.getDataEmprestimo(), hoje);

            if (dias > 10) {
                System.out.printf("%-20s | %-14s | %-12s | %d dias%n",
                    emp.getNome(), emp.getCpf(),
                    emp.getDataFormatada(), dias);
                encontrou = true;
            }
        }

        if (!encontrou) {
            System.out.println("Nenhum empréstimo ativo há mais de 10 dias.");
        }

        System.out.println("=".repeat(60));
    }

}

