package dp;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/*

 */
public class UniquePathsProbabilityGoogleTest {
    private UniquePathsProbabilityGoogle up;

    @Before
    public void setup() {
        up = new UniquePathsProbabilityGoogle();
    }

    @Test
    public void testCase1() {
        double[][] dp = up.uniquePathsProbability(3, 7);
        for(double[] arr : dp){
            System.out.println(Arrays.toString(arr));
        }
    }
}
