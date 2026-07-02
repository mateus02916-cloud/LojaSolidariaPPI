import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class DoacaoDAO implements GerenciadorArquivos {

    private static final String ARQUIVO_ESTOQUE = "EstoqueDoacoes.csv";

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @Override
    public String getNomeArquivo() {
        return ARQUIVO_ESTOQUE;
    }

    @Override
    public Doacoes converterLinha(String linha) {
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

    public void salvarRegistro(Doacoes novoRegistro) {

        adicionarLinha(ARQUIVO_ESTOQUE, novoRegistro.toString());
    }
}
