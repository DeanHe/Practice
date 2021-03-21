package backtracking.expression;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
        Under a grammar given below, strings can represent a set of lowercase words.  Let's use R(expr) to denote the set of words the expression represents.

        Grammar can best be understood through simple examples:

        Single letters represent a singleton set containing that word.
        R("a") = {"a"}
        R("w") = {"w"}
        When we take a comma delimited list of 2 or more expressions, we take the union of possibilities.
        R("{a,b,c}") = {"a","b","c"}
        R("{{a,b},{b,c}}") = {"a","b","c"} (notice the final set only contains each word at most once)
        When we concatenate two expressions, we take the set of possible concatenations between two words where the first word comes from the first expression and the second word comes from the second expression.
        R("{a,b}{c,d}") = {"ac","ad","bc","bd"}
        R("a{b,c}{d,e}f{g,h}") = {"abdfg", "abdfh", "abefg", "abefh", "acdfg", "acdfh", "acefg", "acefh"}
        Formally, the 3 rules for our grammar:

        For every lowercase letter x, we have R(x) = {x}
        For expressions e_1, e_2, ... , e_k with k >= 2, we have R({e_1,e_2,...}) = R(e_1) ∪ R(e_2) ∪ ...
        For expressions e_1 and e_2, we have R(e_1 + e_2) = {a + b for (a, b) in R(e_1) × R(e_2)}, where + denotes concatenation, and × denotes the cartesian product.
        Given an expression representing a set of words under the given grammar, return the sorted list of words that the expression represents.



        Example 1:

        Input: "{a,b}{c,{d,e}}"
        Output: ["ac","ad","ae","bc","bd","be"]
        Example 2:

        Input: "{{a,z},a{b,c},{ab,z}}"
        Output: ["a","ab","ac","z"]
        Explanation: Each distinct word is written only once in the final answer.


        Constraints:

        1 <= expression.length <= 60
        expression[i] consists of '{', '}', ','or lowercase English letters.
        The given expression represents a set of words based on the grammar given in the description.

        interpreter pattern
        https://leetcode.com/problems/brace-expansion-ii/discuss/411848/Java-DFS%2BRecursive
        two operations:
        one is union; because of "," it split to sub-problems.
        one is product； handled by product
*/
public class BraceExpansionII {
    public List<String> braceExpansionII(String expression) {
        if (expression == null || expression.length() == 0) {
            return null;
        }
        List<String> res = new ArrayList<>();
        Set<String> set = new HashSet<>();
        product(expression, 0, "", set);
        res.addAll(set);
        Collections.sort(res);
        return res;
    }

    private void product(String exp, int pos, String temp, Set<String> res) {
        if (pos == exp.length()) {
            res.add(temp);
            return;
        }
        char c = exp.charAt(pos);
        if (c == '{') {
            int end, level; // level means the nested brackets match level
            for (end = pos + 1, level = 1; level != 0; end++) {
                if (exp.charAt(end) == '{') {
                    level++;
                } else if (exp.charAt(end) == '}') {
                    level--;
                }
            }
            String sub = exp.substring(pos + 1, end - 1);
            List<String> ls = braceExpansionII(sub);
            for (String str : ls) {
                product(exp, end, temp + str, res);
            }
        } else if (c == ',') {
            union(exp, pos, temp, res);
        } else {
            product(exp, pos + 1, temp + c, res);
        }
    }

    private void union(String exp, int pos, String temp, Set<String> res){
        res.add(temp);
        temp = "";
        product(exp, pos + 1, temp, res);
    }
}
