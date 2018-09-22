package Backtracking;

import java.util.*;

public class RestoreIPAddresses {
	/**
     * @param s the IP string
     * @return All possible valid IP addresses
     */
    public ArrayList<String> restoreIpAddresses(String s) {
        // Write your code here
        ArrayList<String> res = new ArrayList<>();
        if(s == null || s.length() < 4 || s.length() > 12){
            return res;
        }
        ArrayList<String> tempList = new ArrayList<>();
        dfs(res, s, 0, tempList);
        return res;
    }

    private void dfs(ArrayList<String> res, String s, int pos, ArrayList<String> tempList){
	    if(tempList.size() == 4){
		    if(pos == s.length()){
			    StringBuilder sb = new StringBuilder();
			    for(String substr : tempList){
				    sb.append(substr);
				    sb.append('.');
			    }
			    sb.deleteCharAt(sb.length() - 1);
			    res.add(sb.toString());
			    return;
		    } else {
			    return;
		    }
	    }

	    for(int i = pos + 1; i <= s.length() && i <= pos + 3; i++){
		    String substr = s.substring(pos, i);
		    if(isValidIpNum(substr)){
			    tempList.add(substr);
			    dfs(res, s, i, tempList);
			    tempList.remove(tempList.size() - 1);
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
