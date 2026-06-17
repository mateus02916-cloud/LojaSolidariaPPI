import java.io.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public abstract class GerenciadorArquivos {

    protected DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    // Cada filha informa qual arquivo usa
    protected abstract String getNomeArquivo();

    // Cada filha converte a linha para seu objeto
    protected abstract Object converterLinha(String linha);

    protected void adicionarLinha(String arquivo, String conteudo) {

        try (PrintWriter pw = new PrintWriter(
                new FileWriter(arquivo, true))) {

            pw.println(conteudo);

        } catch (IOException e) {
            System.out.println("Erro ao salvar arquivo: "
                    + e.getMessage());
        }
    }

    // Método herdado por todas as filhas
    protected List<String> lerLinhasArquivo(String arquivo) {

        List<String> linhas = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {

            String linha;

            while ((linha = br.readLine()) != null) {
                linhas.add(linha);
            }

        } catch (IOException e) {
            System.out.println("Erro ao ler arquivo: " + e.getMessage());
        }

        return linhas;
    }

}