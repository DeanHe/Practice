package sort;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/*
Given two arrays of positive integers boxes and warehouse representing the heights of some boxes of unit width, and the heights of n rooms in a warehouse, respectively. The warehouse's rooms are labeled from 0 to n - 1 from left to right where warehouse[i] (0-indexed) is the height of the ith room.

Boxes are put into the warehouse by the following rules:

Boxes can't be piled up.
You can rearrange the order of the boxes.
Boxes can only be pushed into the warehouse from left to right only.
If the height of some room in the warehouse is less than the height of a box, then the box will be stopped before that room, so are the boxes behind it.
Return the maximum number of boxes you can put into the warehouse.

Example 1:
Input: boxes = [1,2,2,3,4], warehouse = [3,4,1,2]
Output: 4
Explanation:
Notice that there are other valid ways to put 4 boxes such as swapping the red and green boxes or the red and orange boxes.

Example 2:
Input: boxes = [3,5,5,2], warehouse = [2,1,3,4,5]
Output: 3
Explanation:
It’s not possible to put the two boxes of height 5 in the warehouse since there’s only 1 room of height >= 5.
Other valid solutions are to put the green box in room 2 or to put the orange box first in room 2 before putting the green and red boxes.

Example 3:
Input: boxes = [1,2,3], warehouse = [1,2,3,4]
Output: 3

Example 4:
Input: boxes = [4,5,6], warehouse = [3,3,3,3,3]
Output: 0

 */

public class PutBoxesIntoTheWareHouseIITest {
    private PutBoxesIntoTheWareHouseII pb;

    @Before
    public void setup() {
        pb = new PutBoxesIntoTheWareHouseII();
    }

    @Test
    public void testCase1() {
        int[] boxes = {1, 2, 2, 3, 4};
        int[] warehouse = {3, 4, 1, 2};
        int res = pb.maxBoxesInWarehouse(boxes, warehouse);
        Assert.assertEquals(4, res);
    }

    @Test
    public void testCase2() {
        int[] boxes = {1, 2, 3};
        int[] warehouse = {2, 1, 3, 4, 5};
        int res = pb.maxBoxesInWarehouse(boxes, warehouse);
        Assert.assertEquals(3, res);
    }

    @Test
    public void testCase3() {
        int[] boxes = {1, 2, 3};
        int[] warehouse = {1, 2, 3, 4};
        int res = pb.maxBoxesInWarehouse(boxes, warehouse);
        Assert.assertEquals(3, res);
    }

    @Test
    public void testCase4() {
        int[] boxes = {4, 5, 6};
        int[] warehouse = {3, 3, 3, 3, 3};
        int res = pb.maxBoxesInWarehouse(boxes, warehouse);
        Assert.assertEquals(0, res);
    }
}

