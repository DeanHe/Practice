package sweepLine.Intervals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
Google interview
Given on-call intervals, input A(0,3), B(2,4), C(5,6) -> output (0,2) A, (2,3) A, B , (3,4) B, (5,6) C
 */
public class OncallSchedule {

    public List<Span> schedule(List<Oncall> ls) {
        List<Tick> axis = new ArrayList<>();
        List<Span> res = new ArrayList<>();
        if(ls == null || ls.isEmpty()){
            return res;
        }
        for (Oncall oncall : ls) {
            Tick startTick = new Tick(oncall.start);
            startTick.set.add(oncall.name);
            axis.add(startTick);
            Tick endTick = new Tick(oncall.end);
            endTick.set.add(oncall.name);
            axis.add(endTick);
        }
        Collections.sort(axis, (a, b) -> a.x - b.x);
        Tick pre = axis.get(0);
        for (int i = 1; i < axis.size(); i++) {
            Tick cur = axis.get(i);
            Span span = new Span(pre.x, cur.x);
            for (String name : pre.set) {
                span.set.add(name);
                if (cur.set.contains(name)) {
                    cur.set.remove(name);
                } else {
                    cur.set.add(name);
                }
            }
            res.add(span);
            pre = cur;
        }
        return res;
    }

    public void test() {
        List<Oncall> oncalls = new ArrayList<>();
        oncalls.add(new Oncall(0, 3, "A"));
        oncalls.add(new Oncall(2, 4, "B"));
        oncalls.add(new Oncall(5, 6, "C"));
        List<Span> res = schedule(oncalls);
        for (Span s : res) {
            System.out.println(s);
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

    private class Oncall {
        String name;
        int start, end;

        public Oncall(int s, int e, String n) {
            name = n;
            start = s;
            end = e;
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
            return String.format("(%s, %s) %s", start, end, sb.toString());
        }
    }
}
