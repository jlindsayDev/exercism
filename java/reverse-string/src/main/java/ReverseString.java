class ReverseString {

    String reverse(String stringToReverse) {
        StringBuilder str = new StringBuilder(stringToReverse.length());
        for (int i = 0; i < stringToReverse.length(); ++i) {
            str.append(stringToReverse.charAt(i));
        }
        return str.toString();
    }

}
