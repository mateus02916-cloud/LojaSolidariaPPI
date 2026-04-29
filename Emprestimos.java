import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class Emprestimos {
    
    private String nome;
    private String cpf;
    private boolean emprestado;
    private LocalDate data;

    public Emprestimos(String nome, String cpf, boolean emprestado, LocalDate data){
        this.nome = nome;
        this.cpf = cpf;
        this.emprestado = emprestado;
        this.data = data;
        


    }

    public String getNome(){
        return nome;
    }

    public String getCpf(){
        return cpf;
    }

    public Boolean getEmprestado(){
        return emprestado;
    }
    
    public LocalDate getData(){
        return data;

    }

    public String getDataFormatada(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return data.format(formatter);
    }


    @Override

    public String toString(){
        return nome + "," + cpf + "," + emprestado + "," + getDataFormatada();

    }

}
