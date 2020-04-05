package hensel;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class LiftingTest {

    public static void main(String[] args) {
        Map<String[], Integer[]> instances = new HashMap<>();

        // Examples of equations and their solutions
        instances.put(new String[]{"4", "1", "8", "-6", "-13"}, new Integer[]{15}); 	// exercise 16
        instances.put(new String[]{"5", "1", "-1", "8"}, new Integer[]{8, 25});		// exercise 16
        instances.put(new String[]{"6", "1", "0", "0"}, new Integer[]{0, 8, 16, 24, 32, 40, 48, 56});
        instances.put(new String[]{"6", "1", "0", "8"}, new Integer[]{});
        instances.put(new String[]{"8", "30", "29", "28", "27"}, new Integer[]{});
        instances.put(new String[]{"10", "1", "0", "0", "1", "0", "0", "510"}, new Integer[]{513});
        instances.put(new String[]{"1", "1", "0"}, new Integer[]{0});
        instances.put(new String[]{"1", "1", "1"}, new Integer[]{1});
        instances.put(new String[]{"2", "1", "1"}, new Integer[]{3});
        instances.put(new String[]{"3", "1", "1"}, new Integer[]{7});
        instances.put(new String[]{"4", "1", "2", "1"}, new Integer[]{3, 7, 11, 15});
        instances.put(new String[]{"4", "1", "-2", "1"}, new Integer[]{1, 5, 9, 13});
        instances.put(new String[]{"11", "1", "-2", "1"}, new Integer[]{1, 65, 129, 193, 257, 321, 385, 449, 513, 577, 641, 705, 769, 833, 897, 961, 1025, 1089, 1153, 1217, 1281, 1345, 1409, 1473, 1537, 1601, 1665, 1729, 1793, 1857, 1921, 1985});

        for (Entry<String[], Integer[]> instance : instances.entrySet()) {
            HenselLifting lifting = Parser.parse(instance.getKey());

            Set<Integer> solutions = lifting.solve();
            // lifting.printSolutions();

            if (!solutions.equals(new HashSet<Integer>(Arrays.asList(instance.getValue())))) {
                // System.out.println(Arrays.asList(instance.getKey()));
                // System.out.println(solutions);

                throw new AssertionError();
            }
        }
    }
}
