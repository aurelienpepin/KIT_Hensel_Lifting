package hensel;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import models.Polynomial;

public class HenselLifting {

    private int kFinal;
    private Polynomial poly;
    private Polynomial derivative;

    private Set<Integer> solutions;

    public HenselLifting(int k, Polynomial p) {
        this.kFinal = k;
        this.poly = p;
        this.derivative = p.derive();

        this.solutions = new HashSet<>();
    }

    public Set<Integer> solve() {
        Set<Integer> solutions = this.solveBaseCase();
        if (solutions.isEmpty()) {
            this.isUnsat();
            this.solutions = solutions;

            return solutions;
        }

        // Compute solutions
        for (int k = 1; k <= kFinal; ++k) {
            solutions = solveNextStep(k - 1, solutions);
        }

        this.solutions = solutions;
        return solutions;
    }

    private Set<Integer> solveNextStep(int k, Set<Integer> solutions) {
        Set<Integer> newSolutions = new HashSet<>();

        for (int x : solutions) {
            if (derivative.evaluateToZeroMod2(x)) {
                newSolutions.addAll(this.computeZeroOrTwoLifting(x, k));
            } else {
                newSolutions.add(this.computeUniqueLifting(x, k));
            }
        }

        return newSolutions;
    }

    private Set<Integer> solveBaseCase() {
        return this.poly.solveMod2();
    }

    private int computeUniqueLifting(int val, int k) {
        return Math.floorMod(val - poly.evaluate(val), (int) Math.pow(2, k + 1));
    }

    private Set<Integer> computeZeroOrTwoLifting(int val, int k) {
        if (poly.evaluateToZeroMod2PowK(val, k + 1)) {
            // Two solutions, val and val + 2^k
            int secondVal = (int) (val + Math.pow(2, k));
            return Stream.of(val, secondVal).collect(Collectors.toSet());
        } else {
            // No solution, empty set
            return new HashSet<>();
        }
    }

    private void isUnsat() {
        System.out.println("UNSAT");
    }

    public void printSolutions() {
        if (solutions.isEmpty()) {
            this.isUnsat();
            return;
        }

        for (int solution : solutions) {
            if (solution < Math.pow(2, kFinal)) {
                System.out.println(solution);
            }
        }
    }

    @Override
    public String toString() {
        return poly + " = 0 (mod 2^" + kFinal + ")";
    }
}
