import java.io.*;
import java.util.*;


public class Estoque {

    private String masculinos;
    private String femininos;
    private String infantil;
    private String calcados;
    private String diversos;
    private int quantidade;


    public Estoque(String Maculinos, String Femininos, String Infantil, String Calçados, String Diversos, int quantidade){
        
        this.masculinos = masculinos; this.femininos = femininos; this.infantil = infantil;
        this.calcados = calcados; this.diversos = diversos;
        this.quantidade = quantidade;
    }


    public String getMasculinos() {
        return masculinos;
    }
    public String getFemininos() {
        return femininos;
    }
    public String getInfantil() {
        return infantil;
    }
    public String getCacados() {
        return calcados;
    }
    public String getDiversos() {
        return diversos;
    }
    public int getQuantidade() {
        return quantidade;
    }
    
    private void exibirEstoque() {

        System.out.println("\n=== ESTOQUE ATUAL ===");
        System.out.println("Masculinos: " + getMasculinos());
        System.out.println("Femininos: " + getFemininos());
        System.out.println("Infantil: " + getInfantil());
        System.out.println("Calçados: " + getCacados());
        System.out.println("Diversos: " + getDiversos());
        System.out.println("Quantidade total: " + getQuantidade());
    }

    public void listarCategorias() {

        System.out.println("\n=== CATEGORIAS DISPONÍVEIS ===");
        System.out.println("1. Masculinos");
        System.out.println("2. Femininos");
        System.out.println("3. Infantil");
        System.out.println("4. Calçados");
        System.out.println("5. Diversos");
    }
    
    public boolean categoriaExiste(String categoria) {
        switch (categoria.toLowerCase()) {
            case "masculinos":
            case "femininos":
            case "infantil":
            case "calçados":
            case "diversos":
                return true;
            default:
                return false;
        }
    }

}