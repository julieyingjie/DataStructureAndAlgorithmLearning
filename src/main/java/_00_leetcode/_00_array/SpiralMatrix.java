package _00_leetcode._00_array;


import java.util.ArrayList;
import java.util.List;

/**
 * 54. Spiral Matrix
 * https://leetcode.com/problems/spiral-matrix/
 */
public class SpiralMatrix {
    public List spiralOrder(int[][] matrix) {
        if (matrix == null) return null;

        List<Integer> res = new ArrayList<>();

        int top = 0;
        int left = 0;
        int bottom = matrix.length - 1;
        int right = matrix[0].length - 1;


        while (top <= bottom && left <= right){
            // leftTop -- > rightTop 列变，行不变
            for (int i = left; i <= right; i++)  res.add(matrix[top][i]);
            top++;

            // rightTop -- > rightBottom 列不变，行变
            for (int i = top; i <= bottom; i++) res.add(matrix[i][right]);
            right--;

            if (top > bottom || left > right) break;
            // rightBottom --> leftBottom  列变， 行不变
            for (int i = right; i >= left ; i--) res.add(matrix[bottom][i]);
            bottom--;

            // leftBottom -- > leftTop 行变，列不变
            for (int i = bottom; i >= top; i--) res.add(matrix[i][left]);
            left++;

        }

        return res;
    }
}
