package DividAndConquer;

import java.util.Stack;

/*Given an expression s includes numbers, letters and brackets. Number represents the number of repetitions inside the brackets(can be a string or another expression)��Please expand expression to be a string.

Example
s = abc3[a] return abcaaa
s = 3[abc] return abcabcabc
s = 4[ac]dy, return acacacacdy
s = 3[2[ad]3[pf]]xyz, return adadpfpfpfadadpfpfpfadadpfpfpfxyz*/
public class DecodeString {
	/**
     * @param s: an expression includes numbers, letters and brackets
     * @return: a string
     */
	public String expressionExpand(String s) {
        // write your code here
        char[] arr = s.toCharArray();
    	Stack<String> stack = new Stack<>();
    	int count = 0;
    	for(char c : arr) {
    		if(c == '[') {
    			stack.push(String.valueOf(count));
    			count = 0;
    		} else if(c == ']'){
    			String sub = getRepeat(stack);
    			stack.push(sub);
    		} else if(Character.isDigit(c)){
    			count = count * 10 + c - '0';
    		} else {
    			stack.push(String.valueOf(c));
    		}
    	}
    	return getRepeat(stack);
    }
    private String getRepeat(Stack<String> stack) {
    	StringBuilder reepeat = new StringBuilder();
    	int count = 1;
    	while(!stack.isEmpty()) {
    		String cur = stack.pop();
    		if(isNumber(cur)) {
    			count = Integer.parseInt(cur);
    			break;
    		} else {
    			reepeat.insert(0, cur);
    		}
    	}
    	StringBuilder ans = new StringBuilder();
    	for(int i = 0; i < count; i++) {
		    ans.append(reepeat.toString());
	    }
    	return ans.toString();
    }
    private boolean isNumber(String s) {
    	if(s.isEmpty()) {
    		return false;
    	}
    	char[] arr = s.toCharArray();
    	for(char c : arr) {
    		if(!Character.isDigit(c)) {
    			return false;
    		}
    	}
    	return true;
    }
}
