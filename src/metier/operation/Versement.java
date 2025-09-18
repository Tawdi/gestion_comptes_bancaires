package metier.operation;

import java.time.format.DateTimeFormatter;

public class Versement extends  Operation{

    private String source;

    public Versement(double montant, String source) {
        super(montant);
        this.source = source;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return "Versement{" +
                "numero= " + numero +
                ", date= " + date.format(formatter) +
                ", montant= " + montant +
                ", source= " + source +
                '}';
    }
}
