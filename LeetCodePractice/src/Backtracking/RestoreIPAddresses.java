package Backtracking;
/*Given a string containing only digits, restore it by returning all possible valid IP address combinations.

(Your task is to add three dots to this string to make it a valid IP address. Return all possible IP address.)

Example
Example 1:

Input: "25525511135"
Output: ["255.255.11.135", "255.255.111.35"]
Explanation: ["255.255.111.35", "255.255.11.135"] will be accepted as well.
Example 2:

Input: "1116512311"
Output: ["11.165.123.11","111.65.123.11"]
Notice
You can return all valid IP address in any order.*/
import java.util.*;

public class RestoreIPAddresses {
	/**
     * @param s the IP string
     * @return All possible valid IP addresses
     */
    public List<String> restoreIpAddresses(String s) {
        // Write your code here
        List<String> res = new ArrayList<>();
        if(s == null || s.length() < 4 || s.length() > 12){
            return res;
        }
        List<String> ls = new ArrayList<>();
        dfs(res, s, 0, ls);
        return res;
    }

    private void dfs(List<String> res, String s, int pos, List<String> ls){
	    if(ls.size() == 4){
		    if(pos == s.length()){
			    StringBuilder sb = new StringBuilder();
			    for(String sub : ls){
				    sb.append(sub);
				    sb.append('.');
			    }
			    sb.deleteCharAt(sb.length() - 1);
			    res.add(sb.toString());
		    }
		    return;
	    }

	    for(int i = pos + 1; i <= s.length() && i <= pos + 3; i++){
		    String sub = s.substring(pos, i);
		    if(isValidIpNum(sub)){
		    	ls.add(sub);
			    dfs(res, s, i, ls);
			    ls.remove(ls.size() - 1);
		    }
	    }
    }

    private boolean isValidIpNum(String s){
	    if(s.charAt(0) == '0'){
		    return s.equals("0");
	    }
	    int num = Integer.valueOf(s);
	    if(num >= 0 && num <= 255){
		    return true;
	    } else {
		    return false;
	    }
    }
}
