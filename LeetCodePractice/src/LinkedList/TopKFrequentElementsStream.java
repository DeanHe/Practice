package LinkedList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

public class TopKFrequentElementsStream {
    int most = 0;
    Map<Integer, Integer> freq = new HashMap<>();
    Map<Integer, LinkedHashSet<Integer>> freqList = new HashMap<>();

    public void offer(int n){
        if(freq.containsKey(n)){
            int f = freq.get(n);
            freqList.get(f).remove(n);
            freq.put(n, f + 1);
            freqList.computeIfAbsent(f + 1, x -> new LinkedHashSet<>()).add(n);
            most = Math.max(most, f + 1);
        } else {
            freq.put(n , 1);
            freqList.computeIfAbsent(1, x -> new LinkedHashSet<>()).add(n);
            most = Math.max(most, 1);
        }
    }
    public List<Integer> topKFrequent(int k) {
        List<Integer> res = new ArrayList<>();
        for(int i = most; i > 0 && k > 0; i--){
            if(freqList.containsKey(i)){
                LinkedHashSet<Integer> set = freqList.get(i);
                for(int n : set){
                    res.add(n);
                    k--;
                    if(k == 0){
                        return res;
                    }
                }
            }
        }
        return res;
    }
}

