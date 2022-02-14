package sweepLine;

import java.util.*;
/*
 given a text input string, and a list of interval to character mapping, for example:
 string "Isomorphism" has following mapping:

 [0 - 3) -> a
 [2 - 7) -> b
 [4 - 8) -> c
 [6 - 10) -> d

please output the intervals without overlap and there correspondent characters, for above case:
 [0 - 2) -> a
 [2 - 3) -> a,b
 [3 - 4) -> b
 [4 - 6) -> b,c
 [6 - 7) -> b,c,d
 [7 - 8) -> c,d
 [8 - 10) -> d
*/
public class TextNormalization {
    public List<Span> normalize(List<Span> input) {
        TreeMap<Integer, Tick> axis = new TreeMap<>();
        List<Span> res = new ArrayList<>();
        for (Span span : input) {
            Tick startTick = axis.computeIfAbsent(span.start, x -> new Tick());
            startTick.set.addAll(span.set);
            startTick.isStart = true;
            Tick endTick = axis.computeIfAbsent(span.end, x -> new Tick());
            endTick.set.addAll(span.set);
            endTick.isStart = false;
        }
        Tick carry = new Tick();
        int pre = -1;
        for(int t : axis.keySet()){
            Span cur = new Span(pre, t);
            cur.set.addAll(carry.set);
            if(pre != -1){
                res.add(cur);
            }
            pre = t;
            Tick tick = axis.get(t);
            if(tick.isStart){
                carry.set.addAll(tick.set);
            } else {
                carry.set.removeAll(tick.set);
            }
        }
        return res;
    }

    class Tick {
        public Set<Character> set;
        public boolean isStart;
        public Tick(){
            set = new HashSet<>();
            isStart = false;
        }

    }
}
