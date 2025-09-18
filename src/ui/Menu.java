package ui;

import metier.banque.Banque;
import metier.compte.Compte;
import metier.compte.CompteCourant;
import metier.compte.CompteEpargne;
import utility.Helper;

import java.util.Scanner;
import java.util.UUID;

public class Menu {

    private UUID id;
    private Banque banque;

    public Menu(Banque banque ){
        this.id = UUID.randomUUID() ;
        this.banque = banque;
    }

    public void show(){
        boolean active = true;
        Scanner sc = new Scanner(System.in);

        while (active){

            afficheMenu();
            try {

                int choice = sc.nextInt();


                switch(choice) {
                    case 1: creerCompte(); break;
                    case 2: verser(); break;
                    case 3: retirer(); break;
                    case 4: virement(); break;
                    case 5: consulterSolde(); break;
                    case 6: consulterOperations(); break;
                    case 0: active = false; break;
                    default: System.out.println("Choix invalide !");
                }
            } catch (Exception e) {
                System.out.println("Erreur saisie : " + e.getMessage());
                sc.nextLine();
            }
        }

    }

    private void afficheMenu() {
        System.out.println("==== Menu ====");
        System.out.println("1. Créer un compte");
        System.out.println("2. Verser argent");
        System.out.println("3. Retirer argent");
        System.out.println("4. Virement");
        System.out.println("5. Consulter solde");
        System.out.println("6. Historique opérations");
        System.out.println("0. Quitter");
    }

    private void retirer() {
        System.out.println("=== Retrait d'un compte ===");

        Compte compte = demanderCompte();
        double montant = Helper.lireDouble("Entrez le montant à retirer : ");
        String destination = Helper.lireString("Entrez le destination de retrait : ");

        compte.retirer(montant,destination);
    }


    private void consulterOperations() {
        System.out.println("=== consulter les operations ===");

        Compte compte = demanderCompte();
        System.out.println("Operations Liste :");
        compte.afficheOperations();

    }

    private void consulterSolde() {
        System.out.println("=== consulter le solde ===");

        Compte compte = demanderCompte();

        System.out.println("solde de compte "+compte.getCode()+" : "+ compte.getSolde());
    }

    private void virement() {

    }

    private void verser() {
        System.out.println("=== Verser d'un compte ===");

        Compte compte = demanderCompte();

        double montant = Helper.lireDouble("Entrez le montant à verser : ");
        String source = Helper.lireString("Entrez le source de Versement : ");

        compte.verser(montant,source);
    }
    private void creerCompte() {
        System.out.println("=== Création d'un compte ===");


        int typeCompte = Helper.lireInt(
                "Choisissez le type de compte :\n 1 - Courant\n 2 - Épargne\n : ", 1, 2);

        String code;
        do {
            code = Helper.lireString("Entrez le code du compte (CPT-XXXXX) : ");
            if (!Helper.isCodeCompteValid(code)) {
                System.out.println("❌ Code invalide. Format attendu : CPT-12345");
            } else if (banque.isCompteInBanque(code)) {
                System.out.println("❌ Ce code existe déjà !");
                code = null;
            }
        } while (code == null || !Helper.isCodeCompteValid(code));


        double solde = Helper.lireDouble("Entrez le solde initial : ");

        // courant ou épargne
        if (typeCompte == 1) {
            double decouvert = Helper.lireDouble("Entrez le découvert autorisé : ");
            banque.ajouterCompte(new CompteCourant(code, solde, decouvert));
            System.out.println("✅ Compte courant créé avec succès !");
        } else {
            double taux = Helper.lireDouble("Entrez le taux d'intérêt (%) : ");
            banque.ajouterCompte(new CompteEpargne(code, solde, taux));
            System.out.println("✅ Compte épargne créé avec succès !");
        }
    }

    private Compte demanderCompte() {
        Compte compte = null;
        String code;
        do {
            code = Helper.lireString("Entrez le code du compte : ");
            if (!Helper.isCodeCompteValid(code)) {
                System.out.println("❌ Code invalide. Format attendu : CPT-12345");
                continue;
            }
            compte = banque.chercherCompte(code);
            if (compte == null) {
                System.out.println("❌ Aucun compte trouvé avec ce code !");
            }
        } while (compte == null);
        return compte;
    }
}
