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
                    case 7: afficheComptes(); break;
                    case 0: active = false; break;
                    default: System.out.println("Choix invalide !");
                }
            } catch (Exception e) {
                System.out.println("Erreur saisie : " + e.getMessage());
                sc.nextLine();
            }
        }

    }

    private void afficheComptes() {
        System.out.println("=== Les Comptes ===");
        banque.afficherComptes();
    }

    private void afficheMenu() {
        System.out.println("==== Menu ====");
        System.out.println("1. Créer un compte");
        System.out.println("2. Verser argent");
        System.out.println("3. Retirer argent");
        System.out.println("4. Virement");
        System.out.println("5. Consulter solde");
        System.out.println("6. Historique opérations");
        System.out.println("7. affiches comptes");
        System.out.println("0. Quitter");
    }

    private void retirer() {
        System.out.println("=== Retrait d'un compte ===");

        Compte compte = demanderCompte();
        double montant = Helper.lireDouble("Entrez le montant à retirer : ");
        String destination = Helper.lireString("Entrez le destination de retrait : ");

        if (Helper.confirmer("Voulez-vous retirer " + montant + " du compte " + compte.getCode() +
                " pour : " + destination + " ?")) {
            if (compte.retirer(montant, destination)) {
                System.out.println("✅ Retrait de " + montant + " effectué avec succès du compte " + compte.getCode() + ".");
            } else {
                System.out.println("❌ Retrait impossible (solde insuffisant ou autre problème).");
            }
        } else {
            System.out.println("❌ Retrait annulé.");
        }
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
        System.out.println("=== Virement ===");

        System.out.println("= Source =");
        Compte source = demanderCompte();

        System.out.println("= destination =");
        Compte destination = demanderCompte();

        if (source.equals(destination)) {
            System.out.println("❌ Impossible de faire un virement vers le même compte !");
            return;
        }

        double montant = Helper.lireDouble("Entrez le montant à transférer : ");

//        if( source.retirer(montant, "Virement vers " + destination.getCode()) ){
//            destination.verser(montant, "Virement de " + source.getCode());
//            System.out.println("✅ Virement effectué avec succès !");
//        }else{
//            System.out.println("❌ Virement refusé  !");
//        }

        if (Helper.confirmer("Voulez-vous effectuer un virement de " + montant +
                " du compte " + source.getCode() +
                " vers le compte " + destination.getCode() + " ?")) {

            if (banque.virement(source, destination, montant)) {
                System.out.println("✅ Virement de " + montant +
                        " effectué avec succès du compte " + source.getCode() +
                        " vers le compte " + destination.getCode() + ".");
            } else {
                System.out.println("❌ Virement refusé (solde insuffisant ou problème technique).");
            }

        } else {
            System.out.println("❌ Virement annulé.");
        }


    }


    private void verser() {
        System.out.println("=== Verser d'un compte ===");

        Compte compte = demanderCompte();

        double montant = Helper.lireDouble("Entrez le montant à verser : ");
        String source = Helper.lireString("Entrez le source de Versement : ");

        if (Helper.confirmer("Voulez-vous continuer le versement de " + montant + " au compte " + compte.getCode() + " ?")) {
            compte.verser(montant, source);
        } else {
            System.out.println("Versement annulé.");
        }

    }
    private void creerCompte() {
        System.out.println("=== Création d'un compte ===");


        int typeCompte = Helper.lireInt(
                "Choisissez le type de compte :\n 1 - Courant\n 2 - Épargne\n : ", 1, 2);

        String code = null;

        int choix;
        do {
            choix = Helper.lireInt(
                    "Souhaitez-vous : \n 1 - Entrer un code manuellement\n 2 - Générer un code automatiquement\n : ", 1, 2);

            if (choix == 1) {
                do {
                    code = Helper.lireString("Entrez le code du compte (CPT-XXXXX) : ");
                    if (!Helper.isCodeCompteValid(code)) {
                        System.out.println("❌ Code invalide. Format attendu : CPT-12345");
                    } else if (banque.isCompteInBanque(code)) {
                        System.out.println("❌ Ce code existe déjà !");
                        code = null;
                    }
                } while (code == null || !Helper.isCodeCompteValid(code));

            } else if (choix == 2) {
                code="";
            }

        } while (code == null);


        double solde = Helper.lireDouble("Entrez le solde initial : ");

        // courant ou épargne
        if (typeCompte == 1) {
            double decouvert = Helper.lireDouble("Entrez le découvert autorisé : ");
            if (Helper.confirmer("Voulez-vous créer un compte courant avec le code " + code +
                    ", solde initial " + solde + " et découvert autorisé " + decouvert + " ?")) {
                banque.ajouterCompte(new CompteCourant(code, solde, decouvert));
                System.out.println("✅ Compte courant créé avec succès !");
            } else {
                System.out.println("❌ Création du compte courant annulée.");
            }
        } else {
            double taux = Helper.lireDouble("Entrez le taux d'intérêt (%) : ");
            if (Helper.confirmer("Voulez-vous créer un compte épargne avec le code " + code +
                    ", solde initial " + solde + " et taux d'intérêt " + taux + "% ?")) {
                banque.ajouterCompte(new CompteEpargne(code, solde, taux));
                System.out.println("✅ Compte épargne créé avec succès !");
            } else {
                System.out.println("❌ Création du compte épargne annulée.");
            }
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
