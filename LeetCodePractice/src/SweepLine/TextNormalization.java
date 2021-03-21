package SweepLine;

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
        Map<Integer, Map<Character, Integer>> axis = new TreeMap<>();
        List<Span> res = new ArrayList<>();
        for (Span span : input) {
            Map<Character, Integer> startMap = axis.computeIfAbsent(span.start, x -> new HashMap<>());
            for(Character c : span.set){
                startMap.put(c, startMap.getOrDefault(c, 0) + 1);
            }
            Map<Character, Integer> endMap = axis.computeIfAbsent(span.end, x -> new HashMap<>());
            for(Character c : span.set){
                endMap.put(c, endMap.getOrDefault(c, 0) - 1);
            }
        }
        int pre = -1;
        Map<Character, Integer> sum = new HashMap<>();
        for(int tick : axis.keySet()){
            Span cur = new Span(pre, tick);
            for(char c : sum.keySet()){
                if(sum.get(c) > 0){
                    cur.set.add(c);
                }
            }
            Map<Character, Integer> tickMap = axis.get(tick);
            for(char c : tickMap.keySet()){
                sum.put(c, sum.getOrDefault(c, 0) + tickMap.get(c));
            }
            if(pre != -1){
                res.add(cur);
            }
            pre = tick;
        }
        return res;
    }
}
