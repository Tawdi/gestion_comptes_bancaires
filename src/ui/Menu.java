package ui;

import metier.banque.Banque;

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
    }
}
