import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Estoque {
    private String tipo; // "ENTRADA" ou "SAIDA"
    private String categoria;
    private int quantidade;
    private LocalDate dataEvento;
    
    public Estoque(String tipo, String categoria, int quantidade, LocalDate dataEvento) {
        this.tipo = tipo;
        this.categoria = categoria;
        this.quantidade = quantidade;
        this.dataEvento = dataEvento;
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
    public LocalDate getDataEvento() { 
        return dataEvento; 
    }
    
    public String getDataFormatada() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return dataEvento.format(formatter);
    }
    
    public String getMesAno() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yyyy");
        return dataEvento.format(formatter);
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

    public void setDataEvento(LocalDate dataEvento){
        this.dataEvento = dataEvento;
    }


    @Override
    public String toString() {
        return tipo + "," + categoria + "," + quantidade + "," + getDataFormatada();
    }
}