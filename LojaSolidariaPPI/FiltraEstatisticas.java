/*
1. Doações recebidas e repassadas mensalmente em determinado ano.
2. Demonstrativo mensal de doações recebidas em ordem decrescente.
3. Demonstrativo mensal de doações repassadas aos beneficiários em ordem decrescente
4. Atendimentos realizados mensalmente em determinado ano
5. Beneficiários com empréstimo ativo a mais de 10 dias.
*/

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FiltraEstatisticas {

    CadastraEstoque estoque = new CadastraEstoque();

    public void doacoesRecebidasDescrecente(int ano) {

        List<Estoque> lista = estoque.lerListaEstoque();

        for (Estoque est : lista) {
            LocalDate data = est.getDataEvento();

            if (data.getYear() == ano) {
                String tipo = est.getTipo();
                // String categoria = est.getCategoria();
                int quantidade = est.getQuantidade();
                //String dataStr = est.getDataFormatada();

                if (lista.isEmpty()) {
                    System.out.println("Nenhum registro encontrado!");
                    return;
                }

                for (int i = 1; data.getMonthValue() < 12; i++) {

                    while (data.getMonthValue() == i && data.getYear() == ano) { // ver se esta funcionando ou absoleto

                        // Calcular entradas do mês por categoria
                        Map<Integer, Integer> entradas = new HashMap<>();
                        int totalEntradas = 0;

                        if (tipo.equals("ENTRADA")) {
                            entradas.put(Integer.valueOf(data.getMonthValue()), + quantidade);

                            totalEntradas += quantidade;

                        }
                    }
                }

            }

        }
    }
}
