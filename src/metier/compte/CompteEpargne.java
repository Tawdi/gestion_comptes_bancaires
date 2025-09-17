package metier.compte;

public class CompteEpargne extends Compte {

    private double tauxInteret;

    public CompteEpargne(String code, double solde, double tauxInteret) {
        super(code, solde);
        this.tauxInteret = tauxInteret;
    }

    @Override
    public void retirer(double montant) {
        try {
            if (montant <= 0) {
                throw new IllegalArgumentException("Le montant doit être positif !");
            }

            if (this.solde <= montant) {
                throw new Exception("Solde insuffisant pour effectuer le retrait !");
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
            System.out.println(" Compte Épargne ");
            System.out.println("=========================");
            System.out.println("Code         : " + this.code);
            System.out.println("Solde        : " + this.solde);
            System.out.println("Taux intérêt : " + this.tauxInteret + " %");
            System.out.println("=========================");
        } catch (Exception e) {
            System.out.println("❌ Erreur lors de l'affichage des détails: " + e.getMessage());
        }
    }

    public void calculerInteret() {
        try {
            double interet = this.solde * (tauxInteret / 100);
            this.solde += interet;
            System.out.println("✅ Intérêts appliqués: " + interet + " | Nouveau solde : " + this.solde);
        } catch (Exception e) {
            System.out.println("❌ Erreur lors du calcul des intérêts: " + e.getMessage());
        }
    }
}
