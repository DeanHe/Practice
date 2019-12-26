package Stack;

import java.util.Stack;

/*
You are given a string s that consists of lower case English letters and brackets.

        Reverse the strings in each pair of matching parentheses, starting from the innermost one.

        Your result should not contain any brackets.



        Example 1:

        Input: s = "(abcd)"
        Output: "dcba"
        Example 2:

        Input: s = "(u(love)i)"
        Output: "iloveu"
        Explanation: The substring "love" is reversed first, then the whole string is reversed.
        Example 3:

        Input: s = "(ed(et(oc))el)"
        Output: "leetcode"
        Explanation: First, we reverse the substring "oc", then "etco", and finally, the whole string.
        Example 4:

        Input: s = "a(bcdefghijkl(mno)p)q"
        Output: "apmnolkjihgfedcbq"


        Constraints:

        0 <= s.length <= 2000
        s only contains lower case English characters and parentheses.
        It's guaranteed that all parentheses are balanced.
*/
public class ReverseSubstringsBetweenEachPairOfParentheses {
    public String reverseParentheses(String s) {
        int len = s.length();
        Stack<Integer> stack = new Stack<>();
        int[] pair = new int[len];
        char[] arr = s.toCharArray();
        for(int i = 0; i < len; i++){
            if(arr[i] == '('){
                stack.push(i);
            } else if(arr[i] == ')') {
                int j = stack.pop();
                pair[i] = j;
                pair[j] = i;
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0, d = 1; i < len; i += d){
            if(arr[i] == '(' || arr[i] == ')'){
                i = pair[i];
                d = -d;
            } else {
                sb.append(arr[i]);
            }
        }
        return sb.toString();
    }
}
