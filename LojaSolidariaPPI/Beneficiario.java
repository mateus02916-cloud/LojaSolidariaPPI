public class Beneficiario {
    private String nome;
    private String cpf;
    private String telefone;

    public String getNome(){
        return nome;
    }

    public String getCpf(){
        return cpf;
    }

    public String getTelefone(){
        return telefone;
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
}
