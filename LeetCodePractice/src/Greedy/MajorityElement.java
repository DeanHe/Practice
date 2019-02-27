package Greedy;
/*Given an array of integers, the majority number is the number that occurs more than half of the size of the array. Find it.

Example
Example 1:
Input: [1, 1, 1, 1, 2, 2, 2]
Output: 1

Example 2:
Input: [1, 1, 1, 2, 2, 2, 2]
Output: 2

Challenge
O(n) time and O(1) extra space

Notice
You may assume that the array is non-empty and the majority number always exist in the array.*/
import java.util.*;

public class MajorityElement {
	/**
     * @param nums: a list of integers
     * @return: find a  majority number
     */
    public int majorityNumber(ArrayList<Integer> nums) {
        // write your code
        int len = nums.size();
        if(len == 1){
            return nums.get(0);
        }
        int res = 0;
        int count = 0;
        for(int i = 0; i < len; i++){
            if(count == 0){
                res = nums.get(i);
                count++;
            } else if(res != nums.get(i)){
                count--;
            } else {
                //res equals nums.get(i)
                count++;
            }
        }
        return res;
    }
}
