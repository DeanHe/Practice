package hashMap;

/*
Given a binary array, find the maximum length of a contiguous subarray with equal number of 0 and 1.

        Example 1:
        Input: [0,1]
        Output: 2
        Explanation: [0, 1] is the longest contiguous subarray with equal number of 0 and 1.
        Example 2:
        Input: [0,1,0]
        Output: 2
        Explanation: [0, 1] (or [1, 0]) is a longest contiguous subarray with equal number of 0 and 1.
        Note: The length of the given binary array will not exceed 50,000.
*/

import java.util.HashMap;
import java.util.Map;

public class ContiguousArray {
    public int findMaxLength(int[] nums) {
        int count = 0, res = 0;
        Map<Integer, Integer>  idxMap = new HashMap<>(); // count : first occur idx;
        idxMap.put(0, -1);
        for(int i = 0; i < nums.length; i++){
            if(nums[i] == 1){
                count++;
            } else {
                count--;
            }
            if(idxMap.containsKey(count)){
                res = Math.max(res, i - idxMap.get(count));
            } else {
                idxMap.put(count, i);
            }
        }
        return res;
    }
}
