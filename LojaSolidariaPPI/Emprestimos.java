import java.time.LocalDate;

public class Emprestimos extends Transacao{
    
   
    private int quantidade;
    private String categoria;
    private boolean emprestado;

    public Emprestimos( int quantidade, String categoria, boolean emprestado, LocalDate dataEmprestimo){
        super(quantidade, dataEmprestimo);
       
        this.quantidade = quantidade;
        this.categoria = categoria;
        this.emprestado = emprestado;
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
    

    public void setQuantidade(int quantidade){
        this.quantidade = quantidade;

    }

    public void setCategoria ( String categoria){
        this.categoria = categoria;

    }

    public void setEmprestado (boolean emprestado){
        this.emprestado = emprestado;

    }

    @Override

    public String toString(){
        return nome + "," + cpf + "," + telefone + "," + quantidade + "," + categoria + "," + emprestado + "," + getDataFormatada();

    }

}
