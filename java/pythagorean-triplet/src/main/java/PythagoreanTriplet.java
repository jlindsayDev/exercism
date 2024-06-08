import java.util.List;
import java.util.ArrayList;

class PythagoreanTripletBuilder {
    private int factorsLessThan;
    private int sumTo;

    public PythagoreanTripletBuilder withFactorsLessThanOrEqualTo(int n) {
        this.factorsLessThan = n;
        return this;
    }

    public PythagoreanTripletBuilder thatSumTo(int n) {
        this.sumTo = n;
        return this;
    }

    public List<PythagoreanTriplet> build() {
        List<PythagoreanTriplet> triplets = new ArrayList<>();
        for (int a = 1; a < this.factorsLessThan - 2; ++a) {
            for (int b = a + 1; b < this.factorsLessThan - 1; ++b) {
                for (int c = b + 1; c < this.factorsLessThan; ++c) {
                    if ((a + b + c) == this.sumTo && (Math.pow(a, 2) + Math.pow(b, 2) == Math.pow(c, 2))) {
                        triplets.add(new PythagoreanTriplet(a, b, c));
                    }
                }
            }
        }
        return triplets;
    }
}

public class PythagoreanTriplet {
    private final int a;
    private final int b;
    private final int c;
    
    public PythagoreanTriplet(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public static PythagoreanTripletBuilder makeTripletsList() {
        return new PythagoreanTripletBuilder();
    }

    public String toString() {
        return "{" + this.a + ", " + this.b + ", " + this.c + "}"; 
    }

    public static void main(String[] args) {
        List<PythagoreanTriplet> triples = PythagoreanTriplet
            .makeTripletsList()
            .withFactorsLessThanOrEqualTo(840)
            .thatSumTo(840)
            .build();
        System.out.println(triples);
    }
}