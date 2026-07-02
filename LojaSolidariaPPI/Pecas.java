public class Pecas {

    private int id;
    private int quantidade;
    private static String[] CATEGORIAS = { "Masculinos", "Femininos", "Infantil", "Calçados", "Diversos" };

    public int getId() {
        return id;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public static String[] getCATEGORIAS() {
        return CATEGORIAS;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public static void setCATEGORIAS(String[] cATEGORIAS) {
        CATEGORIAS = cATEGORIAS;
    }
}
