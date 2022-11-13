package _00_leetcode._00_array;

public class FindAimInSortedMatrix {
    public static void main(String[] args) {
        int[][] matrix = {{4,7,9,13}, {6,8,14,28}, {8,11,25,79}, {15,23,73,95}};

        System.out.println(findAimInSortedMatrix(matrix, 23));
    }

    public static boolean findAimInSortedMatrix(int[][] matrix, int aim){
        if (matrix == null || matrix.length == 0) return false;

        //从右上角开始
        int curRow = 0;
        int curCol = matrix[0].length - 1;

        while (curRow < matrix.length && curCol > -1){
            if (matrix[curRow][curCol] == aim) return true;
            else if (matrix[curRow][curCol] > aim) curCol--;
            else curRow++;
        }

        return false;

    }
}
