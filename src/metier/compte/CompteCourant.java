package metier.compte;

public class CompteCourant extends Compte {

    private double decouvert;

    public CompteCourant(String code, double solde, double decouvert) {
        super(code, solde);
        this.decouvert = decouvert;
    }

    @Override
    public void retirer(double montant) {
        try {
            if (montant <= 0) {
                throw new IllegalArgumentException("Le montant doit être positif !");
            }

            if (this.solde - montant < -decouvert) {
                throw new Exception("Retrait refusé : dépassement du découvert autorisé !");
            }

            this.solde -= montant;
            System.out.println("✅ Retrait de " + montant + " effectué. Nouveau solde : " + solde);

        } catch (IllegalArgumentException e) {
            System.out.println("❌ Erreur: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("❌ Erreur: " + e.getMessage());
        }
    }

    @Override
    public void afficheDetails() {
        try {
            System.out.println("=========================");
            System.out.println(" Compte Courant");
            System.out.println("=========================");
            System.out.println("Code       : " + this.code);
            System.out.println("Solde      : " + this.solde);
            System.out.println("Découvert  : " + this.decouvert);
            System.out.println("=========================");
        } catch (Exception e) {
            System.out.println("❌ Erreur lors de l'affichage des détails: " + e.getMessage());
        }
    }
}
