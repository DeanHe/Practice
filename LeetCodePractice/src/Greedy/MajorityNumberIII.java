package Greedy;

import java.util.*;
// https://www.lintcode.com/problem/majority-number-iii/description
public class MajorityNumberIII {
	/**
     * @param nums: A list of integers
     * @param k: As described
     * @return: The majority number
     */
    public int majorityNumber(ArrayList<Integer> nums, int k) {
        // write your code
        // map of num to count
        HashMap<Integer, Integer> map = new HashMap<>();
        for(Integer n : nums){
            if(map.containsKey(n)){
                int count = map.get(n);
                map.put(n, count + 1);
            } else {
                map.put(n, 1);   
            }
            if(map.size() >= k){
                removePair(map);
            }
        }
        if(map.size() == 0){
            return Integer.MIN_VALUE;
        }
        // reset map
        for(Integer key : map.keySet()){
            map.put(key, 0);
        }
        for(Integer n : nums){
            if(map.containsKey(n)){
                int count = map.get(n);
                map.put(n, count + 1);
            }
        }
        //find the max key
        int count = 0;
        int max = 0;
        for(Integer key : map.keySet()){
            if(count < map.get(key)){
                count = map.get(key);
                max =key;
            }
        }
        return max;
    }
    
    private void removePair(HashMap<Integer, Integer> map){
        Set<Integer> keys = map.keySet();
        ArrayList<Integer> removeList = new ArrayList<>();
        for(Integer n : keys){
            int count = map.get(n);
            count--;
            map.put(n, count);
            if(count == 0){
                removeList.add(n);
            }
        }
        for(Integer n : removeList){
            map.remove(n);
        }
    }
}
