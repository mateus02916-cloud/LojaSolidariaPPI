import java.time.LocalDate;

public class Doacoes extends Transacao {

    private String tipo; // "ENTRADA" ou "SAIDA"
    private static String SEM_CATEGORIA = "-";

    // Entradas continuam sendo registradas por categoria.
    public Doacoes(String tipo, String categoria, int quantidade, LocalDate dataEvento) {
        super(0, dataEvento);
        this.tipo = tipo;
        adicionarPeca(new Pecas(categoria, quantidade));
    }

    // Saídas agora são registradas apenas pela quantidade total, sem categoria.
    public Doacoes(String tipo, int quantidade, LocalDate dataEvento) {
        this(tipo, SEM_CATEGORIA, quantidade, dataEvento);
    }

    public String getTipo() {
        return tipo;
    }

    public String getCategoria() {
        return getPecas().get(0).getCategoria();
    }

    public int getQuantidade() {
        return getPecas().get(0).getQuantidade();
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setCategoria(String categoria) {
        getPecas().get(0).setCategoria(categoria);
    }

    public void setQuantidade(int quantidade) {
        getPecas().get(0).setQuantidade(quantidade);
    }

    @Override
    public String toString() {
        return tipo + "," + getCategoria() + "," + getQuantidade() + "," + getDataFormatada();
    }
}