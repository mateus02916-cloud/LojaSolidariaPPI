import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class Emprestimos {
    
    private String nome;
    private String cpf;
    private String telefone;
    private int quantidade;
    private String categoria;
    private boolean emprestado;
    private LocalDate dataEmprestimo;

    public Emprestimos(String nome, String cpf, String telefone, int quantidade, String categoria, boolean emprestado, LocalDate dataEmprestimo){
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.quantidade = quantidade;
        this.categoria = categoria;
        this.emprestado = emprestado;
        this.dataEmprestimo = dataEmprestimo;
        

    }


    public String getNome(){
        return nome;
    }

    public String getCpf(){
        return cpf;
    }

    public String getTelefone(){
        return telefone;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public String getCategoria() {
        return categoria;

    }

    public boolean isEmprestado(){
        return emprestado;
    }
    
    public LocalDate getDataEmpretimo(){
        return dataEmprestimo;

    }

    public void setNome (String nome) {
        this.nome = nome;

    }

    public void setCpf (String cpf){
        this.cpf = cpf;

    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;

    }

    public void setQuantidade(int quantidade){
        this.quantidade = quantidade;

    }

    public void setCategoria ( String categoria){
        this.categoria = categoria;

    }

    public void setEmprestado (boolean emprestado){
        this.emprestado = emprestado;

    }

    public void setDataEmprestimo (LocalDate dataEmprestimo){

        this.dataEmprestimo = dataEmprestimo;
    }

        //FORMATAR DATA

    public String getDataFormatada(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return dataEmprestimo.format(formatter);
    }


    @Override

    public String toString(){
        return nome + "," + cpf + "," + telefone + "," + quantidade + "," + categoria + "," + emprestado + "," + getDataFormatada();

    }

}
