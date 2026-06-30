import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class Transacao {
    
    private int id;
    private LocalDate data;


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

    public String getDataFormatada(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return data.format(formatter);
    }
}
