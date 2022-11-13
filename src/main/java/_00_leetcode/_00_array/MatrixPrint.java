package _00_leetcode._00_array;

/**
 * 蛇形打印
 * 对角线穿插打印
 * a,b点到达后，会改变运动方向，从横到竖，或从竖到横
 * 方向，a--> b , 或 b --> a， 通过boolean 状态来控制方向。
 * 通过控制坐标范围来
 */
public class MatrixPrint {
    public static void main(String[] args) {

        int[][] matrix = {{1,2,3,4}, {5,6,7,8}, {9,10,11,12}};
        matrixPrint(matrix);

    }

    public static void matrixPrint(int[][] matrix){
        if (matrix == null) return;

        // 1. 设置 a, b 指针来分别控制，行，列
        int aR = 0; //表示行
        int aC = 0; //表示列
        int bR = 0;
        int bC = 0;

        //设置好终点，控制打印结束的时刻
        int endR = matrix.length - 1;
        int endC = matrix[0].length - 1;

        //设置flab, 控制打印方向
        boolean fromUp = false;

        // a 先控行，横着走 b 先控列竖着走
        while (aR != endR + 1){//此处条件也可以设置为aR <= endR, 只是为了缩减对比的步骤

            printLevel(matrix, aR, aC, bR, bC, fromUp);
            aR = aC == endC ? aR + 1 : aR;
            aC = aC == endC ? aC : aC + 1;
            bC = bR == endR ? bC + 1 : bC;
            bR = bR == endR ? bR : bR + 1;
            fromUp = !fromUp;
        }
    }

    private static void printLevel(int[][] matrix, int tR, int tC, int dR, int dC, boolean fromUp) {
        //从上向下打印
        if (fromUp){ // 相当与fromUp = true
            while (tR != dR + 1) System.out.print(matrix[tR++][tC--] + " ");
        }else { // 从下向上打印
            while (dR != tR - 1) System.out.print(matrix[dR--][dC++] + " ");
        }
    }


}























