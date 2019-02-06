package DividAndConquer;

import java.util.*;

/*Given a string that contains only digits 0-9 and a target value, return all possibilities to add binary operators (not unary) +, -, or * between the digits so they evaluate to the target value.

Example 1:

Input: num = "123", target = 6
Output: ["1+2+3", "1*2*3"] 
Example 2:

Input: num = "232", target = 8
Output: ["2*3+2", "2+3*2"]
Example 3:

Input: num = "105", target = 5
Output: ["1*0+5","10-5"]
Example 4:

Input: num = "00", target = 0
Output: ["0+0", "0-0", "0*0"]
Example 5:

Input: num = "3456237490", target = 9191
Output: []*/
public class ExpressionAddOperators {
	public List<String> res;
	public String digits;
	public long target;
	
    public List<String> addOperators(String num, int target) {
        if(num == null || num.length() == 0){
        	return new ArrayList<String>();
        }
        this.target = target;
        this.digits = num;
        this.res = new ArrayList<>();
        dfs(0, 0, new StringBuilder(), 0);
        return this.res;
    }
    
    /*
    index: The index in the digits string that we are processing.
    value: current acumulated value of the expression.
    ops: Represents the actual expression.
    previousValue: Previous operand of the expression along with the appropriate sign.
    */
    private void dfs(int index, long value, StringBuilder ops, long previousValue){
    	// Base case. Time = O(N)
    	if(index == this.digits.length()){
    		if(value == target){
    			this.res.add(ops.toString());
    		}
    		return;
    	}
    	long current_val = 0;
    	String current_val_rep = null;
    	int len = this.digits.length();
    	for(int i = index; i < len; i++){
    		 // Operand
    		char c = this.digits.charAt(i);
    		current_val = current_val * 10 + Character.getNumericValue(c);
    		current_val_rep = Long.toString(current_val);
    		// If this is the first index, simply recurse.
    		if(index == 0){
    			dfs(i + 1, current_val, new StringBuilder(ops).append(current_val_rep), current_val);
    		} else {
    			// ADD
    			dfs(i + 1, value + current_val, new StringBuilder(ops).append("+").append(current_val_rep), current_val);
    			// SUBTRACT
    			dfs(i + 1, value - current_val, new StringBuilder(ops).append("-").append(current_val_rep), -current_val);
    			// MULTIPLY
    			long preAcumulatedValue = value - previousValue;
    			dfs(i + 1, preAcumulatedValue + (previousValue * current_val), new StringBuilder(ops).append("*").append(current_val_rep), previousValue * current_val);
    		}
    		if(this.digits.charAt(i) == '0'){
    			break;
    		}
    	}
    }
}
