package stack.parenthese;
/*
Given a string s containing only three types of characters: '(', ')' and '*', return true if s is valid.

The following rules define a valid string:

Any left parenthesis '(' must have a corresponding right parenthesis ')'.
Any right parenthesis ')' must have a corresponding left parenthesis '('.
Left parenthesis '(' must go before the corresponding right parenthesis ')'.
'*' could be treated as a single right parenthesis ')' or a single left parenthesis '(' or an empty string "".


Example 1:

Input: s = "()"
Output: true
Example 2:

Input: s = "(*)"
Output: true
Example 3:

Input: s = "(*))"
Output: true


Constraints:

1 <= s.length <= 100
s[i] is '(', ')' or '*'.

analysis:
extend it to tracking the lower and upper bound of valid '(' counts

*/
public class ValidParenthesisString {
    public boolean checkValidString(String s) {
        int leftMin = 0, leftMax = 0;
        int len = s.length();
        for(int i = 0; i < len; i++){
            char c = s.charAt(i);
            if(c == '('){
                leftMin++;
                leftMax++;
            } else if(c == ')'){
                if(leftMin > 0){
                    leftMin--;
                }
                leftMax--;
            } else { // '*'
                if(leftMin > 0){
                    leftMin--;
                }
                leftMax++;
            }
            if(leftMax < 0){
                return false;
            }
        }
        return leftMin == 0;
    }
}
