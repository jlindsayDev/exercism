public class PangramChecker {

    public boolean isPangram(String input) {
        boolean[] chars = new boolean['z' - 'a'];
        for (char c : input.toCharArray()) {
            c = Character.toLowerCase(c);
            chars['z' - c] = true;
        }
        boolean ret = true;
        for (int i = 0; ret && i < chars.length; ++i) {
            ret &= chars[i];
        }
        return ret;
    }

}
