package stack;

import java.util.*;

/*Given a chemical formula (given as a string), return the count of each atom.
An atomic element always starts with an uppercase character, then zero or more lowercase letters, representing the name.
1 or more digits representing the count of that element may follow if the count is greater than 1. If the count is 1, no digits will follow. For example, H2O and H2O2 are possible, but H1O2 is impossible.
Two formulas concatenated together produce another formula. For example, H2O2He3Mg4 is also a formula.
A formula placed in parentheses, and a count (optionally added) is also a formula. For example, (H2O2) and (H2O2)3 are formulas.
Given a formula, output the count of all elements as a string in the following form: the first name (in sorted order), followed by its count (if that count is more than 1), followed by the second name (in sorted order), followed by its count (if that count is more than 1), and so on.

Example 1:
Input: 
formula = "H2O"
Output: "H2O"
Explanation: 
The count of elements are {'H': 2, 'O': 1}.
Example 2:
Input: 
formula = "Mg(OH)2"
Output: "H2MgO2"
Explanation: 
The count of elements are {'H': 2, 'Mg': 1, 'O': 2}.
Example 3:
Input: 
formula = "K4(ON(SO3)2)2"
Output: "K4N2O14S4"
Explanation: 
The count of elements are {'K': 4, 'N': 2, 'O': 14, 'S': 4}.
Note:

All atom names consist of lowercase letters, except for the first character which is uppercase.
The length of formula will be in the range [1, 1000].
formula will only consist of letters, digits, and round parentheses, and is a valid formula as defined in the problem.*/
public class NumberOfAtoms {
	public String countOfAtoms(String formula) {
		int len = formula.length();
        Map<String, Integer> map = new HashMap<>();
        Stack<Map<String, Integer>> stack = new Stack<>();
        int i = 0;
        while(i < len){
        	char c = formula.charAt(i);
        	if(c == '('){
        		stack.push(map);
        		map = new HashMap<>();
        		i++;
        	} else if(c == ')'){
        		i++;
        		int count = 0;
        		while(i < len && Character.isDigit(formula.charAt(i))){
        			count = 10 * count + formula.charAt(i) - '0';
        			i++;
        		}
        		if(count == 0){
        			count = 1;
        		}
        		if(!stack.isEmpty()){
        			Map<String, Integer> cur = map;
        			map = stack.pop();
        			for(String key : cur.keySet()){
        				map.put(key, map.getOrDefault(key, 0) + cur.get(key) * count);
        			}
        		}
        	} else {
        		int start = i;
        		i++; // pass the first Cap letter
        		while(i < len && Character.isLowerCase(formula.charAt(i))){
        			i++;
        		}
        		String temp = formula.substring(start, i);
        		int count = 0;
        		while(i < len && Character.isDigit(formula.charAt(i))){
        			count = 10 * count + formula.charAt(i) - '0';
        			i++;
        		}
        		if(count == 0){
        			count = 1;
        		}
        		map.put(temp, map.getOrDefault(temp, 0) + count);
        	}
        }
        StringBuilder sb = new StringBuilder();
        List<String> ls = new ArrayList<>(map.keySet());
        Collections.sort(ls);
        for(String key : ls){
        	sb.append(key);
        	if(map.get(key) > 1){
        		sb.append(map.get(key));
        	}
        }
        return sb.toString();
    }
}
