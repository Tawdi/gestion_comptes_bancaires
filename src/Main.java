import metier.compte.Compte;
import metier.compte.CompteCourant;
import ui.Menu;

public class Main {
    public static void main(String[] args) {

        System.out.println("!!");

        Menu menu = new Menu();
        menu.show();
        Compte compte = new CompteCourant("ggg",3333,444);
        compte.verser(444,"salaire");
        System.out.println(compte);

    }
}