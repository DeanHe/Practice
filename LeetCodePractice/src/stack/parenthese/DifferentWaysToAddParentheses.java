package stack.parenthese;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
Given a string expression of numbers and operators, return all possible results from computing all the different possible ways to group numbers and operators. You may return the answer in any order.



Example 1:

Input: expression = "2-1-1"
Output: [0,2]
Explanation:
((2-1)-1) = 0
(2-(1-1)) = 2
Example 2:

Input: expression = "2*3-4*5"
Output: [-34,-14,-10,-10,10]
Explanation:
(2*(3-(4*5))) = -34
((2*3)-(4*5)) = -14
((2*(3-4))*5) = -10
(2*((3-4)*5)) = -10
(((2*3)-4)*5) = 10


Constraints:

1 <= expression.length <= 20
expression consists of digits and the operator '+', '-', and '*'.

analysis:
recursion + memorization
 */
public class DifferentWaysToAddParentheses {
    Map<String, List<Integer>> mem = new HashMap<>();

    public List<Integer> diffWaysToCompute(String expression) {
        if(mem.containsKey(expression)){
            return mem.get(expression);
        }
        int len = expression.length();
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            char c = expression.charAt(i);
            if (c == '+' || c == '-' || c == '*') {
                String pre = expression.substring(0, i);
                String post = expression.substring(i + 1);
                List<Integer> preRes = diffWaysToCompute(pre);
                List<Integer> postRes = diffWaysToCompute(post);
                for (int a : preRes) {
                    for (int b : postRes) {
                        int val = 0;
                        if (c == '+') {
                            val = a + b;
                        } else if (c == '-') {
                            val = a - b;
                        } else if (c == '*') {
                            val = a * b;
                        }
                        res.add(val);
                    }
                }
            }
        }
        if (res.isEmpty()) {
            res.add(Integer.valueOf(expression));
        }
        mem.put(expression, res);
        return res;
    }
}

