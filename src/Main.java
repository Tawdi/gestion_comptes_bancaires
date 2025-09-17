import metier.banque.Banque;
import metier.compte.Compte;
import metier.compte.CompteCourant;
import ui.Menu;

public class Main {
    public static void main(String[] args) {

        Banque banque = new Banque("CIH");
        Menu menu = new Menu(banque);
        menu.show();

    }
}