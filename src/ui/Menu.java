package ui;

import metier.banque.Banque;
import metier.compte.Compte;
import metier.compte.CompteCourant;
import metier.compte.CompteEpargne;
import utility.Helper;

import java.util.Scanner;

public class Menu {

    private Banque banque;

    public Menu(Banque banque ){
        this.banque = banque;
    }

    public void show(){
        boolean active = true;
        Scanner sc = new Scanner(System.in);

        while (active){

            System.out.println("==== Menu ====");
            System.out.println("1. Créer un compte");
            System.out.println("2. Verser argent");
            System.out.println("3. Retirer argent");
            System.out.println("4. Virement");
            System.out.println("5. Consulter solde");
            System.out.println("6. Historique opérations");
            System.out.println("0. Quitter");
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

    private void retirer() {
    }

    private void consulterOperations() {
    }

    private void consulterSolde() {

    }

    private void virement() {

    }

    private void verser() {

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

}
