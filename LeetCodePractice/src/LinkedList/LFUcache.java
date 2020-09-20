package LinkedList;

import java.util.*;

public class LFUcache {
	 //min is used to track the least frequency of cache
    private int min = -1;
    private int cap;
    //key : value
    private HashMap<Integer, Integer> valMap;
    // key : frequency
    private HashMap<Integer, Integer> freqMap;
    //frequency: list<key>
    private HashMap<Integer, LinkedHashSet<Integer>> freqLists;
    // @param capacity, an integer
    public LFUcache(int capacity) {
        // Write your code here
        cap = capacity;
        valMap = new HashMap<>();
        freqMap = new HashMap<>();
        freqLists = new HashMap<>();
        freqLists.put(1, new LinkedHashSet<>());
    }

    // @param key, an integer
    // @param value, an integer
    // @return nothing
    public void set(int key, int value) {
        // Write your code here
        if(cap <= 0){
            return;
        }
        if(valMap.containsKey(key)){
            valMap.put(key, value);
            get(key);
            return;
        }
        if(valMap.size() >= cap){
            int evit = freqLists.get(min).iterator().next();
            freqLists.get(min).remove(evit);
            valMap.remove(evit);
            freqMap.remove(evit);
        }
        valMap.put(key, value);
        freqMap.put(key, 1);
        min = 1;
        freqLists.get(1).add(key);
    }

    public int get(int key) {
        // Write your code here
        if(!valMap.containsKey(key)){
            return -1;
        }
        int count = freqMap.get(key);
        freqMap.put(key, count + 1);
        freqLists.get(count).remove(key);
        if(count == min && freqLists.get(min).isEmpty()){
            min++;
        }
        freqLists.putIfAbsent(count + 1, new LinkedHashSet<>());
        freqLists.get(count + 1).add(key);
        return valMap.get(key);
    }
}
