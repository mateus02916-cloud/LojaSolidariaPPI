import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Itens {
    private String tipo; // "ENTRADA" ou "SAIDA"
    private String categoria;
    private int quantidade;
    private LocalDate data;
    
    public Itens(String tipo, String categoria, int quantidade, LocalDate data) {
        this.tipo = tipo;
        this.categoria = categoria;
        this.quantidade = quantidade;
        this.data = data;
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
    public LocalDate getData() { 
        return data; 
    }
    
    public String getDataFormatada() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return data.format(formatter);
    }
    
    public String getMesAno() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yyyy");
        return data.format(formatter);
    }
    
    @Override
    public String toString() {
        return tipo + "," + categoria + "," + quantidade + "," + getDataFormatada();
    }
}