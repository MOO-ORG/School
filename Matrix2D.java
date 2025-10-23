public class Matrix2D {

    // Print matrix row by row
    public static void printRowWise(int[][] matrix) {
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                System.out.print(matrix[row][col] + " ");
            }
            System.out.println();
        }
    }

    // Print matrix column by column (supports jagged arrays)
    public static void printColWise(int[][] matrix) {

        // Find the maximum number of columns among all rows
        int maxCols = 0;
        for (int row = 0; row < matrix.length; row++) {
            if (matrix[row].length > maxCols) {
                maxCols = matrix[row].length;
            }
        }

        // Print column-wise
        for (int col = 0; col < maxCols; col++) {
            for (int row = 0; row < matrix.length; row++) {
                if (col < matrix[row].length) { // avoid index out of bounds
                    System.out.print(matrix[row][col] + " ");
                }
            }
            System.out.println();
        }
    }

    //additon of two matrices
    public static void addMatrices(int[][] m1, int[][] m2) {
        int rows = m1.length;
        int cols = m1[0].length;
        int[][] result = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[i][j] = m1[i][j] + m2[i][j];
            }
        }

        System.out.println("Resultant Matrix after Addition:");
        printRowWise(result);
    }

    //matrix multiplication
    public static void multiplyMatrices(int[][] m1, int[][] m2) {
        int rows1 = m1.length;
        int cols1 = m1[0].length;
        int rows2 = m2.length;
        int cols2 = m2[0].length;

        if (cols1 != rows2) {
            System.out.println("Matrix multiplication not possible");
            return;
        }

        int[][] result = new int[rows1][cols2];

        for (int i = 0; i < rows1; i++) {
            for (int j = 0; j < cols2; j++) {
                for (int k = 0; k < cols1; k++) {
                    result[i][j] += m1[i][k] * m2[k][j];
                }
            }
        }

        System.out.println("Resultant Matrix after Multiplication:");
        printRowWise(result);
    }

    // matrix subtraction
    public static void subtractMatrices(int[][] m1, int[][] m2) {
        int rows = m1.length;
        int cols = m1[0].length;
        int[][] result = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[i][j] = m1[i][j] - m2[i][j];
            }
        }

        System.out.println("Resultant Matrix after Subtraction:");
        printRowWise(result);
    }

    //



    public static void main(String[] args) {
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        }; // matrix.length = 3, matrix[0].length = 3

        int[][] jaggedMatrix = {
                {1, 2},
                {3, 4, 5},
                {6, 7, 8, 9}
        }; // jaggedMatrix.length = 3, jaggedMatrix[0].length = 2

        int [][] m1 =   {{1, 2},
                        {3, 4}};

        int [][] m2 =   {{5, 6},
                        {7, 8}};

        System.out.println("\n======= Row-Wise Print =======");
        printRowWise(matrix);
        System.out.println();
        printRowWise(jaggedMatrix);

        System.out.println("\n======= Column-Wise Print =======");
        printColWise(matrix);
        System.out.println();
        printColWise(jaggedMatrix);

        System.out.println("\n======= Matrix Addition =======");
        addMatrices(m1, m2);
        System.out.println();


    }
}
