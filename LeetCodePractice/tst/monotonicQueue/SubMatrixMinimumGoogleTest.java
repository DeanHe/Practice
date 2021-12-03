package monotonicQueue;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

public class SubMatrixMinimumGoogleTest {
    private SubMatrixMinimumGoogle sm;

    @Before
    public void setup() {
        sm = new SubMatrixMinimumGoogle();
    }

    @Test
    public void testCase1(){
        int[][] matrix = {
                {1, 3, 2},
                {4, 6, 5},
                {7, 8, 9},
        };
        int[][] res = sm.minimumSubMatrix(matrix, 2);
        for(int[] row : res){
            System.out.println(Arrays.toString(row));
        }
        Assert.assertTrue(res.length > 0);
    }

    @Test
    public void testCase2(){
        int[][] matrix = {
                {7, 9, 8, 4, 5, 5},
                {2, 9, 2, 8, 5, 4},
                {2, 9, 2, 8, 5, 4},
                {4, 2, 6, 6, 7, 4},
                {5, 2, 7, 1, 9, 3},
                {4, 8, 5, 7, 8, 2},
        };
        int[][] res = sm.minimumSubMatrix(matrix, 3);
        for(int[] row : res){
            System.out.println(Arrays.toString(row));
        }
        Assert.assertTrue(res.length > 0);
    }
}
