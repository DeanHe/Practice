package stack.monotonicStack;

import java.util.Stack;

/*
Given a non-negative integer num represented as a string, remove k digits from the number so that the new number is the smallest possible.

Note:
The length of num is less than 10002 and will be ≥ k.
The given num does not contain any leading zero.
Example 1:

Input: num = "1432219", k = 3
Output: "1219"
Explanation: Remove the three digits 4, 3, and 2 to form the new number 1219 which is the smallest.
Example 2:

Input: num = "10200", k = 1
Output: "200"
Explanation: Remove the leading 1 and the number is 200. Note that the output must not contain leading zeroes.
Example 3:

Input: num = "10", k = 2
Output: "0"
Explanation: Remove all the digits from the number and it is left with nothing which is 0.

Constraints:
1 <= k <= num.length <= 10^5
num consists of only digits.
num does not have any leading zeros except for the zero itself.

analysis:
use a Monotonic increasing stack
one can simply scan from left to right, and remove the first "peak" digit;
the peak digit is larger than its right neighbor. One can repeat this procedure k times
*/
public class RemoveKDigits {
	public String removeKdigits(String num, int k) {
        int len = num.length();
        if(k >= len){
        	return "0";
        }

        Stack<Character> stack = new Stack<>();
        for(char c : num.toCharArray()){
        	//whenever meet a digit which is less than the previous digit, discard the previous one
        	while(k > 0 && !stack.isEmpty() && c < stack.peek()){
        		stack.pop();
        		k--;
        	}
        	stack.push(c);
        }
        while(k > 0){
        	stack.pop();
        	k--;
        }
      //construct the number from the stack
        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty()){
        	sb.insert(0, stack.pop());
        }
      //remove all the 0 at the beginning
        while(sb.length() > 1 && sb.charAt(0) == '0'){
        	sb.deleteCharAt(0);
        }
        return sb.toString();
    }
}
