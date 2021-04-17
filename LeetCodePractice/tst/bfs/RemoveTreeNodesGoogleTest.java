package bfs;

import org.junit.Before;
import org.junit.Test;
import org.testng.Assert;

public class RemoveTreeNodesGoogleTest {
    private RemoveTreeNodesGoogle rt;

    @Before
    public void setup() {
        rt = new RemoveTreeNodesGoogle();
    }

    @Test
    public void testCase1() {
        int[] tree = {0, 0, 0, 2, 2, 4};
        int[] toRemove = {2};
        rt.removeNodes(tree, toRemove);
        Assert.assertTrue(tree.length > 0);
    }
}
