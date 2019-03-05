package Sort;

import java.util.*;

/*Given a list of non negative integers, arrange them such that they form the largest number.

Example
Example 1:

Input:[1, 20, 23, 4, 8]
Output:"8423201"
Example 2:

Input:[4, 6, 65]
Output:"6654"
Challenge
Do it in O(nlogn) time complexity.

Notice
The result may be very large, so you need to return a string instead of an integer.*/
public class LargestNumber {
	/**
     * @param nums: A list of non negative integers
     * @return: A string
     */
    public String largestNumber(int[] nums) {
        // write your code here
    	StringBuilder sb = new StringBuilder();
        List<String> list = new ArrayList<String>();
        for(int i : nums){
       	 list.add(String.valueOf(i));
        }
        Collections.sort(list, new Comparator<String>(){
        	@Override
    		public int compare(String a, String b) {
        		String ab = a + b;
				String ba = b + a;
				return ba.compareTo(ab);
    		}
        });
        for(String s : list){
       	 sb.append(s);
        }
        String result = sb.toString();
        if(result.charAt(0) == '0'){
       	 return "0";
        }
        return result;
    }
}
