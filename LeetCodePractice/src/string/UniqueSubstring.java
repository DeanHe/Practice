package string;

import java.util.*;

// https://www.lintcode.com/problem/unique-substring/
/*Given a string s, find all the unique substring with the length of k and sort the results in lexicographic order.*/
public class UniqueSubstring {
	/**
     * @param s: a string
     * @param k: an integer
     * @return: all unique substring
     */
    public List<String> uniqueSubstring(String s, int k) {
        // Write your code here
        List<String> res = new ArrayList<>();
        if(s.length() == 0 || k == 0){
            return res;
        }
        TreeSet<String> set = new TreeSet<>();
        for(int i = 0; i <= s.length() - k; i++){
            set.add(s.substring(i, i + k));
        }
        while(!set.isEmpty()){
            res.add(set.pollFirst());
        }
        return res;
    }
}
