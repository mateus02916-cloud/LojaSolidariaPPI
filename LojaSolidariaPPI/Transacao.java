import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public abstract class Transacao {
    
    private int id;
    private LocalDate data;
    private List<Pecas> pecas = new ArrayList<>();


    public Transacao(int id, LocalDate data){
        this.id = id;
        this.data = data;

    }

    public int getId() {
        return id;
    }

    public LocalDate getData() {
        return data;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public List<Pecas> getPecas() {
        return pecas;
    }

    public void setPecas(List<Pecas> pecas) {
        this.pecas = pecas;
    }

    public void adicionarPeca(Pecas peca) {
        this.pecas.add(peca);
    }

    public String getDataFormatada(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return data.format(formatter);
    }
}
