package designDataStructure;

import org.junit.Before;
import org.junit.Test;

public class FindMinimumMissingNumberGoogleTest {
    private FindMinimumMissingNumberGoogle fm;

    @Before
    public void setup() {
        fm = new FindMinimumMissingNumberGoogle();
    }

    @Test
    public void testCase1() {
        int[] arr = new int[]{9, 1, 2, 7, 5, 4, 3, 8, 6, 11};
        for (int n : arr) {
            System.out.println(fm.min());
            fm.receive(n);
        }
        System.out.println(fm.min());
    }
}
