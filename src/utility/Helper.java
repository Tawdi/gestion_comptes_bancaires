package utility;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Helper {

    private static final Scanner scanner = new Scanner(System.in);
    private static final Pattern CODE_PATTERN = Pattern.compile("CPT-\\d{5}");

    // Read a string safely
    public static String lireString(String message) {
        System.out.print(message);
        return scanner.nextLine().trim();
    }

    // Read an integer with min/max bounds
    public static int lireInt(String message, int min, int max) {
        int value = 0;
        boolean valid = false;

        while (!valid) {
            try {
                System.out.print(message);
                value = Integer.parseInt(scanner.nextLine());
                if (value < min || value > max) {
                    System.out.println("❌ Valeur hors limites. Réessayez !");
                } else {
                    valid = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("❌ Entrée invalide. Tapez un nombre entier !");
            }
        }

        return value;
    }

    // Read a positive double
    public static double lireDouble(String message) {
        double value = 0;
        boolean valid = false;

        while (!valid) {
            try {
                System.out.print(message);
                value = Double.parseDouble(scanner.nextLine());
                if (value < 0) {
                    System.out.println("❌ Le montant doit être positif !");
                } else {
                    valid = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("❌ Entrée invalide. Tapez un nombre !");
            }
        }

        return value;
    }

    // Validate account code format CPT-XXXXX
    public static boolean isCodeCompteValid(String code) {
        return CODE_PATTERN.matcher(code).matches();
    }

    // Ask for yes/no confirmation
    public static boolean confirmer(String message) {
        while (true) {
            System.out.print(message + " (O/N) : ");
            String input = scanner.nextLine().trim().toUpperCase();
            if (input.equals("O")) return true;
            if (input.equals("N")) return false;
            System.out.println("❌ Entrée invalide. Tapez O pour Oui ou N pour Non.");
        }
    }
}
