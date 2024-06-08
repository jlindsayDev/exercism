import java.util.Collection;
import java.util.HashSet;
import java.util.stream.Collectors;

class SumOfMultiples {
    private Collection<Integer> multiples;

    SumOfMultiples(int number, int ... set) {
        this.multiples = getMultiples(number, set);
    }

    Collection<Integer> getMultiples(final int MAX, final int[] nums) {
        Collection<Integer> multiples = new HashSet<>();

        for (int n : nums) {
            if (multiples.contains(n)) {
                continue;
            }

            for (int m = n; m < MAX; m += n) {
                multiples.add(m);
            }
        }

        return multiples;
    }

    int getSum() {
        return this.multiples.stream().reduce(0, Integer::sum);
    }
}
