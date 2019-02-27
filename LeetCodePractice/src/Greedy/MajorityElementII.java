package Greedy;
/*Given an array of integers, the majority number is the number that occurs more than 1/3 of the size of the array.

Find it.

Example
Example 1:

Input: [99,2,99,2,99,3,3], 
Output: 99.
Example 2:

Input: [1, 2, 1, 2, 1, 3, 3], 
Output: 1.
Challenge
O(n) time and O(1) extra space.

Notice
There is only one majority number in the array.*/
import java.util.*;

public class MajorityElementII {
	/**
     * @param nums: A list of integers
     * @return: The majority number that occurs more than 1/3
     */
    public int majorityNumber(ArrayList<Integer> nums) {
        // write your code
        if(nums == null || nums.size() == 0){
            return 0;
        }
        int count1 = 0;
        int count2 = 0;
        int candidate1 = -1;
        int candidate2 = -1;
        int len = nums.size();
        for(int i = 0; i < len; i++){
            if(count1 == 0){
                candidate1 = nums.get(i);
                count1 = 1;
            } else if(candidate1 == nums.get(i)){
                count1++;
            } else if(count2 == 0){
                candidate2 = nums.get(i);
                count2 = 1;
            } else if(candidate2 == nums.get(i)){
                count2++;
            } else {
                count1--;
                count2--;
            }
        }
        count1 = 0;
        count2 = 0;
        for(int i = 0; i < len; i++){
            if(candidate1 == nums.get(i)){
                count1++;
            } else if(candidate2 == nums.get(i)){
                count2++;
            }
        }
        if(count1 > count2){
            return candidate1;
        } else {
            return candidate2;
        }
    }
}
