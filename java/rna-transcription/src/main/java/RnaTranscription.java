import java.util.Map;
import java.util.HashMap;

class RnaTranscription {
    private static Map<Character, Character> map;
    {
        map = new HashMap<>();
        map.put('G', 'C');
        map.put('C', 'G');
        map.put('T', 'A');
        map.put('A', 'U');
    }

    String transcribe(String dnaStrand) {
        // create copy to avoid modifying original String object
        StringBuilder dna = new StringBuilder(dnaStrand.length());
        for (int i = 0; i < dnaStrand.length(); ++i) {
            char c = dnaStrand.charAt(i);
            if (map.containsKey(c)) {
                c = map.get(c);
            }
            dna.append(c);
        }
        return dna.toString();
    }

}
