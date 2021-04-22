package greedy;

import org.junit.Before;
import org.junit.Test;
import org.testng.Assert;

public class TaskSchedulerFBTest {
    private TaskSchedulerFB ts;

    @Before
    public void setup() {
        ts = new TaskSchedulerFB();
    }

    @Test
    public void testCase1() {
        char[] tasks = {'D', 'D', 'D', 'Z', 'Z', 'D'};
        int res = ts.leastInterval(tasks, 2);
        Assert.assertEquals(12, res);
    }

    @Test
    public void testCase2() {
        char[] tasks = {'D', 'D', 'D', 'Z', 'D', 'D'};
        int res = ts.leastInterval(tasks, 2);
        Assert.assertEquals(13, res);
    }

    @Test
    public void testCase3() {
        char[] tasks = {'A', 'B', 'C', 'D', 'E', 'F'};
        int res = ts.leastInterval(tasks, 2);
        Assert.assertEquals(6, res);
    }
}
