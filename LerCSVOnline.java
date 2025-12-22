import java.io.*;
import java.net.*;
import java.util.*;

public class LerCSVOnline {
    public static void main(String[] args) {
        String urlCSV = "https://docs.google.com/spreadsheets/d/15ifeRL7uKhEZLlQQXc2NpAwZBd7O2XIrMBSYE2lJARc/export?format=csv&gid=0";

        try {
            URL url = new URL(urlCSV);
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(url.openStream(), "UTF-8")
            );

            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(",");

                System.out.println(
                    dados[0] + " | " +
                    dados[1] + " | " +
                    dados[2]
                );
            }

            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}