package math.strobogrammatic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).
Write a function to count the total strobogrammatic numbers that exist in the range of low <= num <= high.
 */
public class StrobogrammaticNumberIII {
	/**
     * @param low: a string
     * @param high: a string
     * @return: the total strobogrammatic numbers that exist in the range of low <= num <= high
     */
    public int strobogrammaticInRange(String low, String high) {
    	int lowLen = low.length();
    	int highLen = high.length();
    	List<String> ls = new ArrayList<>();
    	for(int i = lowLen; i <= highLen; i++) {
    		ls.addAll(helper(i, i));
    	}
    	int count = 0;
    	for(String num : ls) {
    		if((num.length() == lowLen && num.compareTo(low) < 0) || (num.length() == highLen && num.compareTo(high) > 0)) {
    			continue;
    		}
    		count++;
    	}
    	return count;
    }
    
    private List<String> helper(int cur, int max) {
		if (cur == 0) {
			return new ArrayList<String>(Arrays.asList(""));
		}
		if (cur == 1) {
			return new ArrayList<String>(Arrays.asList("0", "1", "8"));
		}
		List<String> list = helper(cur - 2, max);
		List<String> res = new ArrayList<>();
		for (String str : list) {
			if (cur != max) {
				res.add("0" + str + "0");
			}
			res.add("1" + str + "1");
			res.add("8" + str + "8");
			res.add("6" + str + "9");
			res.add("9" + str + "6");
		}
		return res;
	}
}
