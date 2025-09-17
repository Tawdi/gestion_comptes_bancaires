import metier.banque.Banque;
import metier.compte.Compte;
import metier.compte.CompteCourant;
import metier.compte.CompteEpargne;
import ui.Menu;

public class Main {
    public static void main(String[] args) {

        Banque banque = new Banque("CIH");
        banque.ajouterCompte(new CompteCourant("CPT-22222",10000,300));
        banque.ajouterCompte(new CompteEpargne("CPT-33333",10000,3));
        Menu menu = new Menu(banque);
        menu.show();

    }
}