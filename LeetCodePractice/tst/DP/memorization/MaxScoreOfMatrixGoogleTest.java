package DP.memorization;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

public class MaxScoreOfMatrixGoogleTest {
    private MaxScoreOfMatrixGoogle mg;

    @Before
    public void setup() {
        mg = new MaxScoreOfMatrixGoogle();
    }

    @Test
    public void testCase1(){
        int[][] matrix = new int[4][4];
        for (int i=0; i<matrix.length; i++) {
            for (int j=0; j<matrix[i].length; j++) {
                matrix[i][j] = (int) (Math.random()*10);
            }
        }
        for(int[] row : matrix)
            System.out.println(Arrays.toString(row));
        int res = mg.getMaxScoreOfMatrix(matrix);
        int res2 = mg.getMaxScoreOfMatrixDP(matrix);
        System.out.println(res);
        System.out.println(res2);
        Assert.assertEquals(res, res2);
    }
}
