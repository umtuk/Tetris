package Tetris.global.matrix;


/**
 * int 배열을 갖는 행렬의 계산 모음
 */
public class IntMatrixUtil {
    
    /**
     * 행렬을 시계 방향으로 90도 회전합니다.
     */
    public static int[][] rotateClockwise(int[][] matrix) {
        if (matrix.length == 0)
            return new int[][]{};

        int row = matrix.length;
        int col = matrix[0].length;

        int[][] res = new int[col][row];

        for (int r = 0; r < row; r++)
            for (int c = 0; c < col; c++)
            res[c][r] = matrix[row - r - 1][c];

        return res;
    }

    public static int[] findNearestCenter(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;

        int sumR = 0;
        int sumC = 0;

        for (int r = 0; r < row; r++)
            for (int c = 0; c < col; c++) {
                if (matrix[r][c] != 0) {
                    sumR += r;
                    sumC += c;
                }
            }

        int avgR = sumR / 4;
        int avgC = sumC / 4;

        return new int[]{ avgR, avgC };
    }

    public static int lengthCenter2Bottom(int[][] matrix, int[] center) {

        return matrix.length - center[0];
    }

    public static int lengthCenter2Bottom(int[][] matrix) {

        return lengthCenter2Bottom(matrix, findNearestCenter(matrix));
    }

    public static int[][] findAllNotZeroValuePos(int[][] matrix, int count) {
        int row = matrix.length;
        int col = matrix[0].length;

        int[][] ret = new int[count][2];

        int i = 0;
        for (int r = 0; r < row; r++) {
            for (int c = 0 ; c < col; c++) {
                if (matrix[r][c] != 0) {
                    ret[i][0] = r;
                    ret[i][1] = c;
                    i++;
                }
            }
        }

        return ret;
    }
}
