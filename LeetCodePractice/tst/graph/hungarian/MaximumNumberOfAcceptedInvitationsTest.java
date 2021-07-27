package graph.hungarian;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

/*
There are m boys and n girls in a class attending an upcoming party.
You are given an m x n integer matrix grid, where grid[i][j] equals 0 or 1.
If grid[i][j] == 1, then that means the ith boy can invite the jth girl to the party.
A boy can invite at most one girl, and a girl can accept at most one invitation from a boy.
Return the maximum possible number of accepted invitations.
Example 1:
Input: grid = [[1,1,1],
               [1,0,1],
               [0,0,1]]
Output: 3
Explanation: The invitations are sent as follows:
- The 1st boy invites the 2nd girl.
- The 2nd boy invites the 1st girl.
- The 3rd boy invites the 3rd girl.
Example 2:
Input: grid = [[1,0,1,0],
               [1,0,0,0],
               [0,0,1,0],
               [1,1,1,0]]
Output: 3
Explanation: The invitations are sent as follows:
-The 1st boy invites the 3rd girl.
-The 2nd boy invites the 1st girl.
-The 3rd boy invites no one.
-The 4th boy invites the 2nd girl.
Constraints:
grid.length == m
grid[i].length == n
1 <= m, n <= 200
grid[i][j] is either 0 or 1.
 */
public class MaximumNumberOfAcceptedInvitationsTest {
    private MaximumNumberOfAcceptedInvitations mn;

    @Before
    public void setup() {
        mn = new MaximumNumberOfAcceptedInvitations();
    }

    @Test
    public void testCase1() {
        boolean[][] gird = {
                {true, true, true},
                {true, false, true},
                {false, false, true},
        };
        int res = mn.maximumInvitations(gird);
        Assert.assertEquals(3, res);
    }

    @Test
    public void testCase2() {
        boolean[][] gird = {
                {true, false, true, false},
                {true, false, false, false},
                {false, false, true, false},
                {true, true, true, false},
        };
        int res = mn.maximumInvitations(gird);
        Assert.assertEquals(3, res);
    }
}
