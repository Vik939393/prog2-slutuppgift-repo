// PROG2 VT2026, Inlämningsuppgift, del 1
// Grupp 153
// Viktor Spasov visp9819
// Adrian Nötzel adno3118

package se.su.inlupp;

import static java.lang.Integer.parseInt;

public class InputValidator {

    public static String validateString(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException("Input can not be blank.");

        }
        return input.trim();
    }

    public static int validateInt(String input) {
        try {
            int i = parseInt(input);
            if (i < 0) {
                throw new IllegalArgumentException("Weight must be greater than zero");
            }
            return i;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Weight must be integer");
        }

    }
}
