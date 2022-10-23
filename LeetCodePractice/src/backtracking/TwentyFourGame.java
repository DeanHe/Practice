package backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
You are given an integer array cards of length 4. You have four cards, each containing a number in the range [1, 9]. You should arrange the numbers on these cards in a mathematical expression using the operators ['+', '-', '*', '/'] and the parentheses '(' and ')' to get the value 24.

You are restricted with the following rules:

The division operator '/' represents real division, not integer division.
For example, 4 / (1 - 2 / 3) = 4 / (1 / 3) = 12.
Every operation done is between two numbers. In particular, we cannot use '-' as a unary operator.
For example, if cards = [1, 1, 1, 1], the expression "-1 - 1 - 1 - 1" is not allowed.
You cannot concatenate numbers together
For example, if cards = [1, 2, 1, 2], the expression "12 + 12" is not valid.
Return true if you can get such expression that evaluates to 24, and false otherwise.

Example 1:
Input: cards = [4,1,8,7]
Output: true
Explanation: (8-4) * (7-1) = 24

Example 2:
Input: cards = [1,2,1,2]
Output: false

Constraints:
cards.length == 4
1 <= cards[i] <= 9
 */
public class TwentyFourGame {
    public boolean judgePoint24(int[] cards) {
        List<Double> ls = new ArrayList<>();
        for(int n : cards){
            ls.add((double) n);
        }
        return dfs(ls);
    }

    // every time pick two cards to process
    private boolean dfs(List<Double> ls){
        // only one card left
        if(ls.size() == 1){
            if(Math.abs(ls.get(0) - 24.0) < 0.001){
                return true;
            }
            return false;
        }

        for(int i = 0; i < ls.size(); i++){
            for(int j = i + 1; j < ls.size(); j++){
                for(double candidate : computeCandidates(ls.get(i), ls.get(j))){
                    List<Double> next = new ArrayList<>();
                    next.add(candidate);
                    for(int k = 0; k < ls.size(); k++){
                        if(k == i || k == j){
                            continue;
                        }
                        next.add(ls.get(k));
                    }
                    if(dfs(next)){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private List<Double> computeCandidates(double a, double b){
        List<Double> res = Arrays.asList(a + b, a - b, b - a, a * b, a / b, b / a);
        return res;
    }
}
