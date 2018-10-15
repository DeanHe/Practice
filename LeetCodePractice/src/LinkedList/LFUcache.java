package LinkedList;

import java.util.*;

public class LFUcache {
	 //min is used to track the least frequency of cache
    private int min = -1;
    private int cap = 0;
    //key : value
    private HashMap<Integer, Integer> valueHash = null;
    // key : frequency
    private HashMap<Integer, Integer> countHash = null;
    //frequency: list<key>
    private HashMap<Integer, LinkedHashSet<Integer>> lists = null;
    // @param capacity, an integer
    public LFUcache(int capacity) {
        // Write your code here
        cap = capacity;
        valueHash = new HashMap<>();
        countHash = new HashMap<>();
        lists = new HashMap<>();
        lists.put(1, new LinkedHashSet<Integer>());
    }

    // @param key, an integer
    // @param value, an integer
    // @return nothing
    public void set(int key, int value) {
        // Write your code here
        if(cap <= 0){
            return;
        }
        if(valueHash.containsKey(key)){
            valueHash.put(key, value);
            get(key);
            return;
        }
        if(valueHash.size() >= cap){
            int evit = lists.get(min).iterator().next();
            lists.get(min).remove(evit);
            valueHash.remove(evit);
        }
        valueHash.put(key, value);
        countHash.put(key, 1);
        min = 1;
        lists.get(1).add(key);
    }

    public int get(int key) {
        // Write your code here
        if(!valueHash.containsKey(key)){
            return -1;
        }
        int count = countHash.get(key);
        countHash.put(key, count + 1);
        lists.get(count).remove(key);
        if(count == min && lists.get(count).size() == 0){
            min++;
        }
        if(!lists.containsKey(count + 1)){
            lists.put(count + 1, new LinkedHashSet<Integer>());
        }
        lists.get(count + 1).add(key);
        return valueHash.get(key);      
    }
}
