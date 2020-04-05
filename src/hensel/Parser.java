package hensel;

import java.util.stream.IntStream;

import models.Polynomial;

public class Parser {

    public static HenselLifting parse(String[] args) {
        if (args.length < 2) {
            exitWithError("Input parameters are missing (k a_n ... a_0).");
        }

        // Arguments come with the form "k an a(n-1) ... a0"
        int k = 0;
        Polynomial p = null;

        try {
            k = Integer.parseInt(args[0]);

            int[] coefs = IntStream.range(1, args.length).map(i -> Integer.parseInt(args[args.length - i])).toArray();
            p = new Polynomial(coefs);
        } catch (NumberFormatException ex) {
            exitWithError(ex.getMessage());
        }

        return new HenselLifting(k, p);
    }

    public static void exitWithError(String error) {
        System.out.println("ERROR. " + error);
        System.exit(1);
    }
}
