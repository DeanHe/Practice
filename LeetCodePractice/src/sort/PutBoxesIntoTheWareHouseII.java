package sort;

import java.util.Arrays;

/*
Given two arrays of positive integers boxes and warehouse representing the heights of some boxes of unit width, and the heights of n rooms in a warehouse, respectively.
The warehouse’s rooms are labeled from 0 to n - 1 from left to right where warehouse[i] (0-indexed) is the height of the i-th room.

Boxes are put into the warehouse by the following rules:

Boxes can’t be piled up.
You can rearrange the order of the boxes.
Boxes can only be pushed into the warehouse from either side (left or right).
If the height of some room in the warehouse is less than the height of a box, then that box and all other boxes behind it will be stopped before that room.
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

Constraints:
n == warehouse.length
1 <= boxes.length, warehouse.length <= 10^5
1 <= boxes[i], warehouse[i] <= 10^9
 */
public class PutBoxesIntoTheWareHouseII {
    public int maxBoxesInWarehouse(int[] boxes, int[] warehouse){
        int cnt = 0, bLen = boxes.length, whLen = warehouse.length;
        int[] arr = new int[whLen];
        int leftMost = Integer.MAX_VALUE, rightMost = Integer.MAX_VALUE;
        for(int i = 0; i < whLen; i++){
            leftMost = Math.min(leftMost, warehouse[i]);
            arr[i] = Math.max(leftMost, arr[i]);
        }
        for(int i = whLen - 1; i >= 0; i--){
            rightMost = Math.min(rightMost, warehouse[i]);
            arr[i] = Math.max(rightMost, arr[i]);
        }
        Arrays.sort(boxes);
        Arrays.sort(arr);
        int j = 0; // box index
        for(int i = 0; i < whLen && j < bLen; i++){
            if(arr[i] >= boxes[j]){
                j++;
                cnt++;
            }
        }
        return cnt;
    }
}
