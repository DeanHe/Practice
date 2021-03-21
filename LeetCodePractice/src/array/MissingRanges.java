package array;

import java.util.ArrayList;
import java.util.List;

/*
Given a sorted integer array where the range of elements are in the inclusive range [lower, upper], return its missing ranges.

Example
Example 1

Input:
nums = [0, 1, 3, 50, 75], lower = 0 and upper = 99
Output:
["2", "4->49", "51->74", "76->99"]
Explanation:
in range[0,99],the missing range includes:range[2,2],range[4,49],range[51,74] and range[76,99]
Example 2

Input:
nums = [0, 1, 2, 3, 7], lower = 0 and upper = 7
Output:
["4->6"]
Explanation:
in range[0,7],the missing range include range[4,6]
*/
public class MissingRanges {
	/*
     * @param nums: a sorted integer array
     * @param lower: An integer
     * @param upper: An integer
     * @return: a list of its missing ranges
     */
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        // write your code here
    	List<String> res = new ArrayList<>();
    	if(nums == null || nums.length == 0){
    		res.add(getRange(lower, upper));
    		return res;
    	}
    	int len = nums.length;
    	if(lower < nums[0]){
			res.add(getRange(lower, nums[0] - 1));
		}
    	for(int i = 1; i < len; i++){
    		int diff = nums[i] - nums[i - 1];
    		if(diff > 1){
    			res.add(getRange(nums[i - 1] + 1, nums[i] - 1));
    		}
    	}
    	if(nums[len - 1] < upper){
    		res.add(getRange(nums[len - 1] + 1, upper));
    	}
    	return res;
    }
    private String getRange(int from, int to){
    	if(from == to){
    		return String.valueOf(from);
    	} else {
    		return from + "->" + to;
    	}
    }
}
