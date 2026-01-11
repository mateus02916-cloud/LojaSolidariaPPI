import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class teste {
 
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String opcaoData = sc.nextLine();


        LocalDate capturaData;
        DateTimeFormatter formata = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        if (opcaoData.equals("1")) {
            capturaData = LocalDate.now();
            String data = capturaData.format(formata);
            
        }  else {

            try {
                capturaData = LocalDate.parse(opcaoData, formata);
                String data = capturaData.format(formata);
            } catch (DateTimeParseException e) {
                System.out.println("A data '" + data + "' NÃO é válida no formato dd/MM/yyyy.");
            }
        }

        System.out.println(data);
        System.out.println(data.getClass().getSimpleName());


       /*  LocalDate data = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");


        System.out.println(data.format(formatter));////
       //System.out.println("teste 2 :" + data.format(formatter).getClass().getSimpleName());  */
       

       
    }
}





