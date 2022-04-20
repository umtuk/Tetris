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

    @Test
    void testFindNearestCenter() {
        int[][] value;
        int[] expected = new int[2];

        value = new int[][] {
            {1, 1, 1 ,1}
        };
        expected[0] = 0; expected[1] = 1;

        int[] result = IntMatrixUtil.findNearestCenter(value);

        assertTrue(result[0] == expected[0]);
        assertTrue(result[1] == expected[1]);

        value = new int[][] {
            {1},
            {1},
            {1},
            {1},
        };
        expected[0] = 1; expected[1] = 0;

        result = IntMatrixUtil.findNearestCenter(value);

        assertTrue(result[0] == expected[0]);
        assertTrue(result[1] == expected[1]);

        value = new int[][] {
            {0, 1, 1},
			{1, 1, 0}
        };
        expected[0] = 0; expected[1] = 1;

        result = IntMatrixUtil.findNearestCenter(value);

        assertTrue(result[0] == expected[0]);
        assertTrue(result[1] == expected[1]);

        value = new int[][] {
            {1, 1},
            {1, 1},
        };
        expected[0] = 0; expected[1] = 0;

        result = IntMatrixUtil.findNearestCenter(value);

        assertTrue(result[0] == expected[0]);
        assertTrue(result[1] == expected[1]);
    }

    @Test
    void testFindAllNotZeroValuePos() {
        int[][] value = new int[][] {
            {0, 1, 1},
			{1, 1, 0}
        };

        int[][] result = IntMatrixUtil.findAllNotZeroValuePos(value, 4);
        int[][] expected = new int[][] {
            {0, 1},
            {0, 2},
            {1, 0},
            {1, 1},
        };
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 2; j++) {
                assertTrue(result[i][j] == expected[i][j]);
            }
        }
    }
}
