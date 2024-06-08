class MicroBlog {
    public final int LENGTH = 5;

    public String truncate(String input) {
        if (input == null) {
            return "";
        }
        return new String(input.codePoints().limit(LENGTH).toArray(), 0, LENGTH);
    }

    public static void main(String args[]) {
        MicroBlog b = new MicroBlog();
        System.out.println(b.truncate(System.console().readLine()));
    }
}
