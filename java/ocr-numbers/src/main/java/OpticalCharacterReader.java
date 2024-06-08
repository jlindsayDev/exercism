import java.util.Map;
import java.util.HashMap;

public class OpticalCharacterReader {
    private static final Map<String, String> DIGITS = new HashMap<>();
    static {
        DIGITS.put(" _ | ||_|", "0");
        DIGITS.put("     |  |", "1");
        DIGITS.put(" _  _||_ ", "2");
        DIGITS.put(" _  _| _|", "3");
        DIGITS.put("   |_|  |", "4");
        DIGITS.put(" _ |_  _|", "5");
        DIGITS.put(" _ |_ |_|", "6");
        DIGITS.put(" _   |  |", "7");
        DIGITS.put(" _ |_||_|", "8");
        DIGITS.put(" _ |_| _|", "9");
    }

    public String parse(String numStr) {
        String[] lines = validateInput(numStr);
        return flattenDigits(lines);
    }

    String[] validateInput(final String numStr) {
        String[] lines = numStr.split("\n");

        if (lines.length % 4 != 0) {
            String msg = "Number of input rows must be a positive multiple of 4";
            throw new IllegalArgumentException(msg);
        }

        for (int i = 0; i < lines.length; ++i) {
            if (lines[i].length() % 3 != 0) {
                String msg = "Number of input columns must be a positive multiple of 3";
                throw new IllegalArgumentException(msg);
            }

            if (i % 4 == 3 && !lines[i].trim().equals("")) {
                throw new IllegalArgumentException("Number of input rows must be a positive multiple of 4");
            }
        }

        return lines;
    }

    String flattenDigits(final String[] lines) {
        StringBuilder sb = new StringBuilder();

        // each row of digits
        for (int i = 0; i < lines.length; i += 4) {

            // each 3 x 3 digit window
            for (int j = 0; j < lines[i].length(); j += 3) {
                String digit = getDigit(lines, i, j);
                if (this.DIGITS.containsKey(digit)) {
                    sb.append(this.DIGITS.get(digit));
                } else {
                    sb.append("?");
                }
            }
        }

        return sb.toString();
    }

    String getDigit(final String[] lines, final int r, final int c) {
        StringBuilder sb = new StringBuilder();

        sb.append(lines[r].substring(c, c + 3));
        sb.append(lines[r + 1].substring(c, c + 3));
        sb.append(lines[r + 2].substring(c, c + 3));

        return sb.toString();
    }

    public static void main(String[] args) {
        String testInput = String.join("\n",
            "    _  _ ",
            "  | _| _|",
            "  ||_  _|",
            "         ",
            "    _  _ ",
            "|_||_ |_ ",
            "  | _||_|",
            "         ",
            " _  _  _ ",
            "  ||_||_|",
            "  ||_| _|",
            "         ",
            " _  _  _ ",
            "  || || |",
            " _||_||_|",
            "         "
        );
        OpticalCharacterReader ocr = new OpticalCharacterReader();
        System.out.println(ocr.parse(testInput));
    }
}