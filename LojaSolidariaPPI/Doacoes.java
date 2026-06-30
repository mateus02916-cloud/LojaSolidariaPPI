import java.time.LocalDate;

public class Doacoes extends Transacao{
    private String tipo; // "ENTRADA" ou "SAIDA"
    private String categoria;
    private int quantidade;
    
    public Doacoes(String tipo, String categoria, int quantidade, LocalDate dataEvento) {
        super(quantidade, dataEvento);
        this.tipo = tipo;
        this.categoria = categoria;
        this.quantidade = quantidade;
    }
    
    
    public String getTipo() { 
        return tipo; 
    }
    public String getCategoria() { 
        return categoria; 
    }
    public int getQuantidade() { 
        return quantidade; 
    }

    public void setTipo(String tipo){
        this.tipo = tipo;
    }

    public void setCategoria(String categoria){
        this.categoria = categoria;
    }

    public void setQuantidade(int quantidade){
        this.quantidade = quantidade;
    }


    @Override
    public String toString() {
        return tipo + "," + categoria + "," + quantidade + "," + getDataFormatada();
    }
}