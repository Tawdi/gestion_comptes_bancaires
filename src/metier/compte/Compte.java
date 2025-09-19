package metier.compte;

import metier.operation.Operation;
import metier.operation.Versement;

import java.util.ArrayList;
import java.util.UUID;

public abstract   class Compte {
    private static int compteur = 4;
    protected UUID id;
    protected String code;
    protected double solde;
    protected ArrayList<Operation> listOperation;

    public Compte(String code , double solde){
        this.id = UUID.randomUUID();
        setCode(code);
        setSolde(solde);
        this.listOperation = new ArrayList<>();
    }

    public abstract boolean retirer(double montant,String destination);
    public abstract double calculerInteret() ;
    public abstract void afficheDetails();

    public final void verser(double montant , String source) {
        this.solde += montant;
        System.out.println("✅ Versement de " + montant + " effectué avec succès  vers compte "+this.code);
        this.listOperation.add(new Versement(montant , source));
    }

    public final void afficheOperations(){

        if(!listOperation.isEmpty()) {
            listOperation.forEach(System.out::println);
        } else {
            System.out.println("\n\n   aucun operation trouvé \n\n");
        }

    }


    @Override
    public  String toString(){
        return "=====================\n" +
                "compte info\n"+
                "=====================\n"+
                "* id : "+ id+ "\n* code : "+ code+ "\n* solde : " + solde + "\n"+
                "* list : "+listOperation+
                "=====================\n";
    }

    public double getSolde() {
        return solde;
    }

    public String getCode() {
        return code;
    }

    public UUID getId() {
        return id;
    }

    public void setSolde(double solde) {
        this.solde = solde;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setCode(String code) {

        this.code = (code == null || code.isEmpty()) ? generateCode() : code;

    }

    private String generateCode(){
        compteur++;
        return String.format("CPT-%05d", compteur);
    }
}
