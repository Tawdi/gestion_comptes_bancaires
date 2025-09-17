package metier.operation;

public class Versement extends  Operation{

    private String source;

    public Versement(double montant, String source) {
        super(montant);
        this.source = source;
    }

    @Override
    public String toString() {
        return "Versement{" +
                "numero= " + numero +
                ", date= " + date +
                ", montant= " + montant +
                ", source= " + source +
                '}';
    }
}
