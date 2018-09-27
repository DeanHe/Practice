package Greedy;

import java.util.*;

// https://www.lintcode.com/problem/majority-element/description
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
