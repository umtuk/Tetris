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

        // 

        return res;
    }
}
