import java.io.*;
import java.util.*;


public class Estoque {
        private List<Itens> itens;
        private static String ARQUIVO_CSV ="estoque.csv";
        private static String [] CATEGORIAS = { "Masculinos","Femininos","Infantil","Calçados","Diversos"};
    
    public Estoque() {
        itens = new ArrayList<>();  
    }

    private void lerEstoque(){
        try (BufferedReader br = new BufferedReader(new FileReader(ARQUIVO_CSV))){
            String linhas;

            while ((linhas = br.readLine()) != null){
                String [] dados = linhas.split (",");

                if (dados.length == 2) {
                    String categoria = dados[0].trim();
                    int quantidade = Integer.parseInt(dados[1].trim());
                
                    itens.add(new Itens(categoria, quantidade));
                }

            }
        } catch (IOException e){
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }

    }
    public void salvarEstoque(){
        try (PrintWriter pw = new PrintWriter(new FileWriter(ARQUIVO_CSV))){
            
            for (Itens itens : itens){
                pw.println(itens.toString());
            }

        } catch (IOException e){
            System.out.println("Erro ao salver o arquivo" + e.getMessage());

        }


    }
  

}







        
    








    





    
    
   

