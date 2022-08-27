package math;

import java.util.HashMap;
import java.util.Map;

/*
Given two integers representing the numerator and denominator of a fraction, return the fraction in string format.

If the fractional part is repeating, enclose the repeating part in parentheses.

Example 1:
Input: numerator = 1, denominator = 2
Output: "0.5"

Example 2:
Input: numerator = 2, denominator = 1
Output: "2"

Example 3:
Input: numerator = 2, denominator = 3
Output: "0.(6)"

hint:
1 No scary math, just apply elementary math knowledge. Still remember how to perform a long division?
2 Try a long division on 4/9, the repeating part is obvious. Now try 4/333. Do you see a pattern?
3 Notice that once the remainder starts repeating, so does the divided result
4 Be wary of edge cases! List out as many test cases as you can think of and test your code thoroughly.
*/
public class FractionToRecurringDecimal {
	public String fractionToDecimal(int numerator, int denominator) {
        StringBuilder sb = new StringBuilder();
        if((numerator > 0 && denominator < 0) || (numerator < 0 && denominator > 0)){
        	sb.append("-");
        }
        long numeratorl = Math.abs((long)numerator);
        long denominatorl = Math.abs((long)denominator);
        long res = numeratorl / denominatorl;
        sb.append(res);
        numeratorl = (numeratorl % denominatorl) * 10;
        if(numeratorl != 0){
        	sb.append(".");
        }
        Map<Long, Integer> map = new HashMap<>();
        while(numeratorl != 0){
        	res = numeratorl / denominatorl;
        	if(map.containsKey(numeratorl)){
        		sb.insert(map.get(numeratorl), "(");
        		sb.append(")");
        		break;
        	} else {
        		map.put(numeratorl, sb.length());
        		sb.append(res);
        		numeratorl = (numeratorl % denominatorl) * 10;
        	}
        }
        return sb.toString();      
    }
}
