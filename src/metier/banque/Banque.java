package metier.banque;

import metier.compte.Compte;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Banque {

    private UUID id;
    private String nom;
    private Map<String, Compte> comptes;

    public Banque(String nom) {
        this.id = UUID.randomUUID();
        this.nom = nom;
        this.comptes = new HashMap<>();
    }

    public void ajouterCompte(Compte compte) {
        try {
            if (compte == null) {
                throw new IllegalArgumentException("Le compte ne peut pas être null !");
            }
            if (comptes.containsKey(compte.getCode())) {
                throw new Exception("Un compte avec ce code existe déjà !");
            }
            comptes.put(compte.getCode(), compte);
            System.out.println("✅ Compte ajouté : " + compte.getCode());
        } catch (IllegalArgumentException e) {
            System.out.println("❌ Erreur: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("❌ Erreur: " + e.getMessage());
        }
    }

    public Compte chercherCompte(String code) {
        try {
            if (code == null || code.isEmpty()) {
                throw new IllegalArgumentException("Le code du compte est invalide !");
            }
            Compte compte = comptes.get(code);
            if (compte == null) {
                throw new Exception("Aucun compte trouvé avec le code : " + code);
            }
            return compte;
        } catch (IllegalArgumentException e) {
            System.out.println("❌ Erreur: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("❌ Erreur: " + e.getMessage());
        }
        return null;
    }

    public boolean isCompteInBanque(String code){
        Compte compte = comptes.get(code);
        return compte != null;
    }

    public boolean virement(Compte source, Compte destination, double montant) {
        if (source.retirer(montant, "Virement vers " + destination.getCode())) {
            destination.verser(montant, "Virement de " + source.getCode());
            return true;
        }
        return false;
    }

    public void afficherComptes() {
        try {
            if (comptes.isEmpty()) {
                throw new Exception("Aucun compte trouvé dans la banque !");
            }
            System.out.println("=== Liste des comptes de la banque " + nom + " ===");
            comptes.values().forEach(Compte::afficheDetails);
        } catch (Exception e) {
            System.out.println("❌ Erreur: " + e.getMessage());
        }
    }

    public String getNom() {
        return nom;
    }

    public UUID getId() {
        return id;
    }


}
