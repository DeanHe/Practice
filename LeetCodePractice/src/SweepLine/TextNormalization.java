package SweepLine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// given a text input string, and a list of interval to character mapping, for example:
// string "Isomorphism" has following mapping:
// [0 - 3) -> a
// [2 - 7) -> b
// [4 - 8) -> c
// [6 - 10) -> d
//please output the intervals without overlap and there correspondent characters, for above case:
// [0 - 2) -> a
// [2 - 3) -> a,b
// [3 - 4) -> b
// [4 - 6) -> b,c
// [6 - 7) -> b,c,d
// [7 - 8) -> c,d
// [8 - 10) -> d
public class TextNormalization {
    public List<Span> normalize(List<Span> input) {
        List<Span> res = new ArrayList<>();
        List<Tick> axis = new ArrayList<>();
        for (Span span : input) {
            Tick tick_start = new Tick(span.start);
            Tick tick_end = new Tick(span.end);
            for (String s : span.set) {
                tick_start.set.add(s);
                tick_end.set.add(s);
            }
            axis.add(tick_start);
            axis.add(tick_end);
        }
        Collections.sort(axis, (a, b) -> a.x - b.x);
        Tick pre = axis.get(0);
        for (int i = 1; i < axis.size(); i++) {
            Tick cur = axis.get(i);
            Span span = new Span(pre.x, cur.x);
            for (String s : pre.set) {
                span.set.add(s);
                if(cur.set.contains(s)){
                    cur.set.remove(s);
                } else {
                    cur.set.add(s);
                }
            }
            res.add(span);
            pre = cur;
        }
        return res;
    }

    public void test(){
        List<Span> inputs = new ArrayList<>();
        Span s1 = new Span(0, 3);
        s1.set.add("a");
        inputs.add(s1);
        Span s2 = new Span(2, 7);
        s2.set.add("b");
        inputs.add(s2);
        Span s3 = new Span(4, 8);
        s3.set.add("c");
        inputs.add(s3);
        Span s4 = new Span(6, 10);
        s4.set.add("d");
        inputs.add(s4);
        List<Span> res = normalize(inputs);
        for (Span s : res) {
            System.out.println(s);
        }
    }

    private class Span {
        int start, end;
        Set<String> set;

        public Span(int s, int e) {
            start = s;
            end = e;
            set = new HashSet<>();
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (String name : set) {
                sb.append(name + ",");
            }
            sb.deleteCharAt(sb.length() - 1);
            return String.format("(%s, %s) [%s]", start, end, sb.toString());
        }
    }

    private class Tick {
        int x;
        Set<String> set;

        public Tick(int x) {
            this.x = x;
            set = new HashSet<>();
        }
    }
}
