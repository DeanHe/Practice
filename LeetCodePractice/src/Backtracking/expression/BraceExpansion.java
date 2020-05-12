package Backtracking.expression;

import java.util.*;

/*
A string S represents a list of words.

        Each letter in the word has 1 or more options. If there is one option, the letter is represented as is. If there is more than one option, then curly braces delimit the options. For example, “{a,b,c}” represents options [“a”, “b”, “c”].

        For example, “{a,b,c}d{e,f}” represents the list [“ade”, “adf”, “bde”, “bdf”, “cde”, “cdf”].

        Return all words that can be formed in this manner, in lexicographical order.

        Example 1:

        Input: "{a,b}c{d,e}f"
        Output: ["acdf","acef","bcdf","bcef"]
        Example 2:

        Input: "abcd"
        Output: ["abcd"]
        Note:

        1 <= S.length <= 50
        There are no nested curly brackets.
        All characters inside a pair of consecutive opening and ending curly brackets are different.
*/
public class BraceExpansion {
    List<String> res = new ArrayList<>();
    public String[] expand(String s) {
        if(s == null || s.length() == 0){
            return new String[0];
        }
        product(s, 0, "");
        Collections.sort(res);
        return res.toArray(new String[res.size()]);
    }
    private void product(String s, int pos, String temp){
        if(pos == s.length()){
            res.add(temp);
            return;
        }
        char c = s.charAt(pos);
        if(c == '{'){
            int end = pos + 1;
            while(s.charAt(end) != '}'){
                end++;
            }
            String[] pool = s.substring(pos + 1, end).split(",");
            for(String letter : pool){
                product(s, end + 1, temp + letter);
            }
        } else {
            product(s, pos + 1, temp + c);
        }
    }
}
