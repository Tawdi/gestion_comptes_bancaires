package metier.compte;

import metier.operation.Operation;
import metier.operation.Versement;

import java.util.ArrayList;
import java.util.UUID;

public abstract   class Compte {
    protected UUID id;
    protected String code;
    protected double solde;
    protected ArrayList<Operation> listOperation;

    public Compte(String code , double solde){
        this.id = UUID.randomUUID();
        this.code = code;
        this.solde = solde;
        this.listOperation = new ArrayList<>();
    }

    public abstract void retirer(double montant,String destination);

    public abstract void afficheDetails();

    public final void verser(double montant , String source) {
        this.solde += montant;
        System.out.println("✅ Versement de " + montant + " effectué avec succès  vers compte "+this.code);
        this.listOperation.add(new Versement(montant , source));
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
}
