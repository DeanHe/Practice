package Backtracking;
/*Return the result of evaluating a given boolean expression, represented as a string.

An expression can either be:

"t", evaluating to True;
"f", evaluating to False;
"!(expr)", evaluating to the logical NOT of the inner expression expr;
"&(expr1,expr2,...)", evaluating to the logical AND of 2 or more inner expressions expr1, expr2, ...;
"|(expr1,expr2,...)", evaluating to the logical OR of 2 or more inner expressions expr1, expr2, ...
 

Example 1:

Input: expression = "!(f)"
Output: true
Example 2:

Input: expression = "|(f,t)"
Output: true
Example 3:

Input: expression = "&(t,f)"
Output: false
Example 4:

Input: expression = "|(&(t,f,t),!(t))"
Output: false
 

Constraints:

1 <= expression.length <= 20000
expression[i] consists of characters in {'(', ')', '&', '|', '!', 't', 'f', ','}.
expression is a valid expression representing a boolean, as given in the description.*/
public class ParsingaBooleanExpression {
	public boolean parseBoolExpr(String expression) {
        if(expression.equals("t")){
        	return true;
        }
        if(expression.equals("f")){
        	return false;
        }
        char[] arr = expression.toCharArray();
        int len = arr.length;
        char op = arr[0];
        boolean result = false;
        if(op == '&'){
        	result = true;
        }
        int parenthese_count = 0, parenthese_start_pos = 2;
        for (int i = 1; i < len; i++) {
        	char c = arr[i];
        	if(c == '('){
        		parenthese_count++;
        	}
        	if(c == ')'){
        		parenthese_count--;
        	}
        	if((c == ')' && parenthese_count == 0) || (c == ',' && parenthese_count == 1)){
        		boolean next = parseBoolExpr(expression.substring(parenthese_start_pos, i));
        		parenthese_start_pos = i + 1;
        		if(op == '|'){
        			result |= next;
        		} else if(op == '&'){
        			result &= next;
        		} else {
        			result = !next;
        		}
        	}
        }
        return result;
    }
}
