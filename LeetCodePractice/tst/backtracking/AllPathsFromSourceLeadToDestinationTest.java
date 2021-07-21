package backtracking;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

/*
Example 1:



Input: n = 3, edges = [[0,1],[0,2]], source = 0, destination = 2
Output: false
Explanation: It is possible to reach and get stuck on both node 1 and node 2.
Example 2:



Input: n = 4, edges = [[0,1],[0,3],[1,2],[2,1]], source = 0, destination = 3
Output: false
Explanation: We have two possibilities: to end at node 3, or to loop over node 1 and node 2 indefinitely.
Example 3:



Input: n = 4, edges = [[0,1],[0,2],[1,3],[2,3]], source = 0, destination = 3
Output: true
Example 4:



Input: n = 3, edges = [[0,1],[1,1],[1,2]], source = 0, destination = 2
Output: false
Explanation: All paths from the source node end at the destination node, but there are an infinite number of paths,
such as 0-1-2, 0-1-1-2, 0-1-1-1-2, 0-1-1-1-1-2, and so on.
Example 5:



Input: n = 2, edges = [[0,1],[1,1]], source = 0, destination = 1
Output: false
Explanation: There is infinite self-loop at destination node.
Note:
 */

public class AllPathsFromSourceLeadToDestinationTest {
    private AllPathsFromSourceLeadToDestination ap;

    @Before
    public void setup() {
        ap = new AllPathsFromSourceLeadToDestination();
    }

    @Test
    public void testCase1() {
        int n = 3;
        int[][] edges = {{0, 1}, {0, 2}};
        int source = 0;
        int dest = 2;
        Assert.assertFalse(ap.leadsToDestination(n, edges, source, dest));
    }

    @Test
    public void testCase2() {
        int n = 4;
        int[][] edges = {{0, 1}, {0, 3}, {1, 2}, {2, 1}};
        int source = 0;
        int dest = 3;
        Assert.assertFalse(ap.leadsToDestination(n, edges, source, dest));
    }

    @Test
    public void testCase3() {
        int n = 4;
        int[][] edges = {{0, 1}, {0, 2}, {1, 3}, {2, 3}};
        int source = 0;
        int dest = 3;
        Assert.assertTrue(ap.leadsToDestination(n, edges, source, dest));
    }

    @Test
    public void testCase4() {
        int n = 3;
        int[][] edges = {{0, 1}, {1, 1}, {1, 2}};
        int source = 0;
        int dest = 2;
        Assert.assertFalse(ap.leadsToDestination(n, edges, source, dest));
    }

    @Test
    public void testCase5() {
        int n = 2;
        int[][] edges = {{0, 1}, {1, 1}};
        int source = 0;
        int dest = 1;
        Assert.assertFalse(ap.leadsToDestination(n, edges, source, dest));
    }
}
