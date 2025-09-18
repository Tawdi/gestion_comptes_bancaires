package metier.operation;

import java.time.format.DateTimeFormatter;

public class Retrait extends Operation{

    private String destination;

    public Retrait(double montant , String destination) {
        super(montant);
        this.destination = destination;
    }
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return "Retrait{" +
                "numero= " + numero +
                ", date= " + date.format(formatter) +
                ", montant= " + montant +
                ", destination= " + destination +
                '}';
    }
}
