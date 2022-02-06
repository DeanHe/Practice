package dp.memorization;

import dp.MaxPathFromEntryRowToExitRowGoogle;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class NumberOfPathIncludingAllIntermediatePointGoogleTest {
    private NumberOfPathsIncludingAllIntermediatePointGoogle np;

    @Before
    public void setup() {
        np = new NumberOfPathsIncludingAllIntermediatePointGoogle();
    }

    @Test
    public void testCase1() {
        int[] start = {5, 0};
        int[] end = {5, 9};
        int[][] intermediates = {
                {4, 1},
                {4, 2},
                {5, 3},
        };
        int res = np.totalPaths(10, 10, start, end, intermediates);
        System.out.println("total paths:" + res);
    }
}
