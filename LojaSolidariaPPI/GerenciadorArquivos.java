import java.io.*;
import java.time.format.DateTimeFormatter;

public abstract class GerenciadorArquivos {

    protected DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("dd/MM/yyyy");

    protected void adicionarLinha(String arquivo, String conteudo) {

        try (PrintWriter pw =
                new PrintWriter(
                        new FileWriter(arquivo, true))) {

            pw.println(conteudo);

        } catch (IOException e) {
            System.out.println("Erro ao salvar arquivo: "
                    + e.getMessage());
        }
    }

    protected abstract Object converterLinha(String linha);
}