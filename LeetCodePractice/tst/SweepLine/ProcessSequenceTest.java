package SweepLine;

import SweepLine.Intervals.Interval;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
Given logs = [[1, 1234], [2, 1200]], queries = [1, 1100, 1201, 1235], return [1, 0].
 */
public class ProcessSequenceTest {
    private ProcessSequence ps;

    @Before
    public void setup() {
        ps = new ProcessSequence();
    }

    @Test
    public void testCase1(){
        List<Interval> intervals = Arrays.asList(new Interval(1, 1200), new Interval(2, 1234));
        List<Integer> queries = Arrays.asList(1, 1100, 1201, 1235);
        List<Integer> res = ps.numberOfProcesses(intervals, queries);
        System.out.println(res);
        Assert.assertTrue(res.size() > 0);
    }

    @Test
    public void testCase21(){
        List<Interval> intervals = Arrays.asList(new Interval(1, 1200), new Interval(2, 1234));
        List<Integer> queries = Arrays.asList(1, 1100, 1201, 1235);
        List<Integer> res = ps.numberOfProcesses2(intervals, queries);
        System.out.println(res);
        Assert.assertTrue(res.size() > 0);
    }
}
