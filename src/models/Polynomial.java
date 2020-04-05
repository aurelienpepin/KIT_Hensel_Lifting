package models;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

public class Polynomial {

    public int[] coefs;

    public Polynomial(int[] coefs) {
        this.coefs = coefs;
    }

    public Polynomial derive() {
        if (coefs.length == 1) {
            return new Polynomial(new int[]{1});
        }

        int dCoefs[] = new int[coefs.length - 1];

        for (int i = 0; i < coefs.length - 1; ++i) {
            dCoefs[i] = coefs[i + 1] * (i + 1);
        }

        return new Polynomial(dCoefs);
    }

    /**
     * Return -1 if there is no solution. 0: the term associated with x^0 is
     * even 1: the sum of all coefs is odd
     *
     * @return
     */
    public Set<Integer> solveMod2() {
        Set<Integer> solutions = new HashSet<>();

        if (coefs[0] % 2 == 0) {
            solutions.add(0);
        }

        if (IntStream.of(coefs).sum() % 2 == 0) {
            solutions.add(1);
        }

        return solutions;
    }

    public int evaluate(int val) {
        int sum = 0;

        for (int i = 0; i < coefs.length; ++i) {
            sum += coefs[i] * Math.pow(val, i);
        }

        return sum;
    }

    public boolean evaluateToZeroMod2PowK(int val, int k) {
        // return evaluate(val) % Math.pow(2, k) == 0;
        return Math.floorMod(evaluate(val), (int) Math.pow(2, k)) == 0;
    }

    public boolean evaluateToZeroMod2(int val) {
        // return evaluate(val) % 2 == 0;
        return evaluateToZeroMod2PowK(val, 1);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        for (int i = coefs.length - 1; i >= 0; --i) {
            builder.append(coefs[i]).append("x^").append(i);

            if (i > 0) {
                builder.append(" + ");
            }
        }

        return builder.toString();
    }
}
