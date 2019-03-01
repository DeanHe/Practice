package Array;

import java.util.*;

public class SummaryRanges {
	/**
     * @param nums:  a sorted integer array without duplicates
     * @return: the summary of its ranges
     */
    public List<String> summaryRanges(int[] nums) {
        // Write your code here
    	int len = nums.length;
    	List<String> res = new ArrayList<>();
    	for(int start = 0; start < len;){
    		String s = "";
    		int end = start + 1;
    		int cur = nums[start];
    		s += cur;
    		while(end < len && cur + 1 == nums[end]){
    			end++;
    			cur++;
    		}
    		if(end > start + 1){
    			s += "->" + cur;
    		}
    		res.add(s);
    		start = end;
    	}
    	return res;
    }
}
