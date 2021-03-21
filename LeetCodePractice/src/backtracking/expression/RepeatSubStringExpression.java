package backtracking.expression;
/*
Print expressions with special syntax representing repetitive substrings. E.g. 'abc(d)<2>e' --> 'abcdde', 'a(bcd)<2>e' --> 'abcdbcde', 'a(b(c)<3>d)<2>e' --> 'abcccdbcccde'.
 */
public class RepeatSubStringExpression {
    public String parse(String input) {
        StringBuilder sb = new StringBuilder();
        dfs(input, 0, sb);
        return sb.toString();
    }

    private void dfs(String input, int pos, StringBuilder sb) {
        if (pos == input.length()) {
            return;
        }
        if (Character.isAlphabetic(input.charAt(pos))) {
            sb.append(input.charAt(pos));
            dfs(input, pos + 1, sb);
            return;
        }
        if (input.charAt(pos) == '(') {
            int start = pos + 1;
            int end;
            int bracketLevel = 1;
            for (end = pos + 1; bracketLevel != 0; end++) {
                if (input.charAt(end) == '(') {
                    bracketLevel++;
                } else if (input.charAt(end) == ')') {
                    bracketLevel--;
                }
            }
            String inside = parse(input.substring(start, end - 1));
            int count = 0;
            while (input.charAt(end) != '>') {
                if (Character.isDigit(input.charAt(end))) {
                    count *= 10;
                    count += Character.getNumericValue(input.charAt(end));
                }
                end++;
            }
            end++;
            for (int cnt = 0; cnt < count; cnt++) {
                sb.append(inside);
            }
            dfs(input, end, sb);
        }
    }
}
