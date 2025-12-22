public class Itens {


    private String categoria;
    private int quantidade;

    public Itens(String categoria, int quantidade){
        this.categoria = categoria;
        this.quantidade = quantidade;

    }

    public String getCategoria(){
        return categoria;
    }

    public void setCategoria(String categoria){
        this.categoria = categoria;

    }

    public int getQuantidade(){
        return quantidade;
    }

    public void setQuantidade(){
        this.quantidade = quantidade;

    }

    @Override
    public String toString(){
        return categoria + "," + quantidade;
    }

    
}
