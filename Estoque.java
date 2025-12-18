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
        this.calcados = calçados; this.diversos = diversos;
        this.quantidade = quantidade;
    }


    public String getMasculinos() {
        return masculinos;
    }
    public String geFemininos() {
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
    
    
    

}