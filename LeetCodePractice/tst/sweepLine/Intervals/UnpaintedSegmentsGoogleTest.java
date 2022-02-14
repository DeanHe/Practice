package sweepLine.Intervals;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import sweepLine.Span;
import sweepLine.TextNormalization;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
Calculate and output the length of unpainted segments inside [A, B] and then paints it.
Example input: N=4, [4, 10], [7, 13] [20, 30] [1, 40]
Output:
6, 3, 10, 20
Explain:
6 : 10-4
3 : [7, 10] already painted, [10, 13] is unpainted segment inside [7, 13], 13 -10 = 3
10: 30 - 20
20: [4, 13] and [20‍‌‌‍‌‌‌‌‍‍‌‍‍‍‌‌‌‍, 30] segments are already painted, [1, 4] [13, 20] [30, 40] are unpainted inside [1, 40],
(4-1) + (20-13) + (40-30) = 20
 */
public class UnpaintedSegmentsGoogleTest {
    private UnpaintedSegmentsGoogle us;

    @Before
    public void setup() {
        us = new UnpaintedSegmentsGoogle();
    }

    @Test
    public void testCase1(){
        int n = 4;
        List<Interval> inputs = new ArrayList<>();
        inputs.add(new Interval(4, 10));
        inputs.add(new Interval(7, 13));
        inputs.add(new Interval(20, 30));
        inputs.add(new Interval(1, 40));
        int[] res = us.unpaintedArea(n, inputs);
        System.out.println(Arrays.toString(res));
    }

    @Test
    public void testCase2(){
        int n = 4;
        List<Interval> inputs = new ArrayList<>();
        inputs.add(new Interval(4, 10));
        inputs.add(new Interval(7, 13));
        inputs.add(new Interval(20, 30));
        inputs.add(new Interval(1, 40));
        int[] res = us.unpaintedArea2(n, inputs);
        System.out.println(Arrays.toString(res));
    }
}
