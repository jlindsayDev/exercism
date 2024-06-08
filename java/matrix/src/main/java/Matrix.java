import java.util.Arrays;

class Matrix {
    private int[][] cells;

    Matrix(String matrixAsString) {
        this.cells = buildMatrix(matrixAsString);
    }

    int[][] buildMatrix(String matrixStr) {
        String[] rows = matrixStr.split("\n");
        int[][] cells = new int[rows.length][];

        for (int i = 0; i < rows.length; ++i) {
            String row = rows[i];
            int[] cols = Arrays.stream(row.split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
            cells[i] = cols;
        }

        return cells;
    }

    int[] getRow(int rowNumber) {
        return this.cells[rowNumber - 1];
    }

    int[] getColumn(int columnNumber) {
        int[] cols = new int[this.cells.length];

        for (int i = 0; i < this.cells.length; ++i) {
            cols[i] = this.cells[i][columnNumber - 1];
        }

        return cols;
    }

    public static void main(String[] args) {
        Matrix m = new Matrix("1 2 3\n4 5 6\n7 8 9");
        System.out.println(m.getRow(3));
        System.out.println(m.getColumn(3));
    }
}
