package Tetris.global.matrix;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

public class IntMatrixUtilTest {

    @Test
    void testIBlockRotateClockwise() {
        int[][] value;
        int[][] expected;
    
        value = new int[][] {
            {1, 1, 1 ,1}
        };
        expected = new int[][] {
            {1}, 
            {1},
            {1},
            {1}
        };
        assertTrue(Arrays.deepEquals(expected, IntMatrixUtil.rotateClockwise(value)));
        assertTrue(Arrays.deepEquals(value, IntMatrixUtil.rotateClockwise(expected)));
    }

    @Test
    void testJBlockRotateClockwise() {
        int[][] value;
        int[][] expected;

        value = new int[][] {
            {1, 1, 1},
            {0, 0, 1},
        };
        expected = new int[][] {
            {0, 1},
            {0, 1},
            {1, 1}
        };
        assertTrue(Arrays.deepEquals(expected, IntMatrixUtil.rotateClockwise(value)));

        value = expected;
        expected = new int[][] {
            {1, 0, 0},
            {1, 1, 1}
        };
        assertTrue(Arrays.deepEquals(expected, IntMatrixUtil.rotateClockwise(value)));

        value = expected;
        expected = new int[][] {
            {1, 1},
            {1, 0},
            {1, 0}
        };
        assertTrue(Arrays.deepEquals(expected, IntMatrixUtil.rotateClockwise(value)));

        value = expected;
        expected = new int[][] {
            {1, 1, 1},
            {0, 0, 1}
        };
        assertTrue(Arrays.deepEquals(expected, IntMatrixUtil.rotateClockwise(value)));
    }

    @Test
    void testLBlockRotateClockwise() {
        int[][] value;
        int[][] expected;

        value = new int[][] {
            {1, 1, 1},
            {1, 0, 0},
        };
        expected = new int[][] {
            {1, 1},
            {0, 1},
            {0, 1}
        };
        assertTrue(Arrays.deepEquals(expected, IntMatrixUtil.rotateClockwise(value)));

        value = expected;
        expected = new int[][] {
            {0, 0, 1},
            {1, 1, 1}
        };
        assertTrue(Arrays.deepEquals(expected, IntMatrixUtil.rotateClockwise(value)));

        value = expected;
        expected = new int[][] {
            {1, 0},
            {1, 0},
            {1, 1}
        };
        assertTrue(Arrays.deepEquals(expected, IntMatrixUtil.rotateClockwise(value)));

        value = expected;
        expected = new int[][] {
            {1, 1, 1},
            {1, 0, 0}
        };
        assertTrue(Arrays.deepEquals(expected, IntMatrixUtil.rotateClockwise(value)));
    }

    @Test
    void testOBlockRotateClockwise() {
        int[][] value;
        int[][] expected;

        value = new int[][] {
            {1, 1},
            {1, 1},
        };
        expected = new int[][] {
            {1, 1},
            {1, 1},
        };

        assertTrue(Arrays.deepEquals(expected, IntMatrixUtil.rotateClockwise(value)));
        assertTrue(Arrays.deepEquals(value, IntMatrixUtil.rotateClockwise(expected)));
    }

    @Test
    void testSBlockRotateClockwise() {
        int[][] value;
        int[][] expected;

        value = new int[][] {
            {0, 1, 1},
			{1, 1, 0}
        };
        expected = new int[][] {
            {1, 0},
            {1, 1},
            {0, 1}
        };

        assertTrue(Arrays.deepEquals(expected, IntMatrixUtil.rotateClockwise(value)));
        assertTrue(Arrays.deepEquals(value, IntMatrixUtil.rotateClockwise(expected)));
    }

    @Test
    void testTBlockRotateClockwise() {
        int[][] value;
        int[][] expected;

        value = new int[][] {
            {0, 1, 0},
			{1, 1, 1}
        };
        expected = new int[][] {
            {1, 0},
            {1, 1},
            {1, 0}
        };
        assertTrue(Arrays.deepEquals(expected, IntMatrixUtil.rotateClockwise(value)));

        value = expected;
        expected = new int[][] {
            {1, 1, 1},
            {0, 1, 0}
        };
        assertTrue(Arrays.deepEquals(expected, IntMatrixUtil.rotateClockwise(value)));

        value = expected;
        expected = new int[][] {
            {0, 1},
            {1, 1},
            {0, 1}
        };
        assertTrue(Arrays.deepEquals(expected, IntMatrixUtil.rotateClockwise(value)));

        value = expected;
        expected = new int[][] {
            {0, 1, 0},
            {1, 1, 1}
        };
        assertTrue(Arrays.deepEquals(expected, IntMatrixUtil.rotateClockwise(value)));
    }

    @Test
    void testZBlockRotateClockwise() {
        int[][] value;
        int[][] expected;

        value = new int[][] {
            {1, 1, 0},
			{0, 1, 1}
        };
        expected = new int[][] {
            {0, 1},
            {1, 1},
            {1, 0}
        };

        assertTrue(Arrays.deepEquals(expected, IntMatrixUtil.rotateClockwise(value)));
        assertTrue(Arrays.deepEquals(value, IntMatrixUtil.rotateClockwise(expected)));
    }
}
