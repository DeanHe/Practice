package backtracking.expression;


import java.util.ArrayList;
import java.util.List;

/*
Implement a basic calculator to evaluate a simple expression string.

The expression string may contain open ( and closing parentheses ), the plus + or minus sign -, non-negative integers and empty spaces .

The expression string contains only non-negative integers, +, -, *, / operators , open ( and closing parentheses ) and empty spaces . The integer division should truncate toward zero.

You may assume that the given expression is always valid. All intermediate results will be in the range of [-2147483648, 2147483647]

Example
"1 + 1" = 2
" 6-4 / 2 " = 4
"2*(5+5*2)/3+(6/2+8)" = 21
"(2+6* 3+5- (3*14/7+2)*5)+3"=-12


Analysis
We use a different method here which is called interpreter pattern which is described here.
https://sourcemaking.com/design_patterns/interpreter
The key idea of interpreter is that we given the current state and next token we see, we will expect certain command, we parse and execute that command and accumulate the result.
For example here, when we see ‘+’, ‘-’ we get next factor and accumulate result in expression, when we see ‘*’ or ‘/’ we get next term and convert result to factor.
f we see ‘(‘ we start another inner expression which ending by ‘)’.
Expr = term + - term
term = factor * / factor
factor = number or (Expr) // number is the base case
*/
public class BasicCalculatorIII {
    int idx = 0;

    /**
     * @param s: the expression string
     * @return: the answer
     */
    public int calculate(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        List<String> tokens = parseExpressionToken(s);
        int res = parseExpression(tokens);
        return res;
    }

    private List<String> parseExpressionToken(String s) {
        List<String> res = new ArrayList<>();
        int len = s.length(), cur = 0;
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                cur = 10 * cur + c - '0';
                if (i + 1 < len && !Character.isDigit(s.charAt(i + 1))) {
                    res.add(String.valueOf(cur));
                    cur = 0;
                }
            } else {
                if (c != ' ') {
                    res.add(String.valueOf(c));
                }
            }
        }
        if (cur != 0) {
            res.add(String.valueOf(cur));
        }
        return res;
    }

    private int parseExpression(List<String> tokens) {
        int res = parseTerm(tokens);
        while (idx < tokens.size()) {
            if (tokens.get(idx).equals("+")) {
                idx++;
                res += parseTerm(tokens);
            } else if (tokens.get(idx).equals("-")) {
                idx++;
                res -= parseTerm(tokens);
            } else {
                break;
            }
        }
        return res;
    }

    private int parseTerm(List<String> tokens) {
        int res = parseFactor(tokens);
        while (idx < tokens.size()) {
            if (tokens.get(idx).equals("*")) {
                idx++;
                res *= parseFactor(tokens);
            } else if (tokens.get(idx).equals("/")) {
                idx++;
                res /= parseFactor(tokens);
            } else {
                break;
            }
        }
        return res;
    }

    private int parseFactor(List<String> tokens) {
        int res = 0;
        if (Character.isDigit(tokens.get(idx).charAt(0))) { // is Numeric
            res = Integer.parseInt(tokens.get(idx));

        } else if (tokens.get(idx).equals("(")) {
            idx++;
            res = parseExpression(tokens);
            // when return with ")" left there
        }
        idx++;
        return res;
    }
}
