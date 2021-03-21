package SweepLine;

import java.util.HashSet;
import java.util.Set;

public class Span {
    int start, end;
    Set<Character> set;

    public Span(int s, int e) {
        start = s;
        end = e;
        set = new HashSet<>();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (char c : set) {
            sb.append(c + ",");
        }
        sb.deleteCharAt(sb.length() - 1);
        return String.format("(%s, %s) [%s]", start, end, sb.toString());
    }
}
