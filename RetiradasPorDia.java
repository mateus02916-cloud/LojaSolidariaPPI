public class RetiradasPorDia {
    private String data;
    private int quantidade;

    public RetiradasPorDia(String data, int quantidade){
        this.data = data;
        this.quantidade = quantidade;

    }

    public String getData(){
        return data;
    }

    public void setData(String data){
        this.data = data;

    }

    public int getQuantidade(){
        return quantidade;
    }

    public void setQuantidade(int quantidade){
        this.quantidade = quantidade;

    }

    @Override
    public String toString(){
        return data + "," + quantidade;
    }

}
