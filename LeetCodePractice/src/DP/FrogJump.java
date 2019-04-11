package DP;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/*A frog is crossing a river. The river is divided into x units and at each unit there may or may not exist a stone. The frog can jump on a stone, but it must not jump into the water.
Given a list of stones' positions (in units) in sorted ascending order, determine if the frog is able to cross the river by landing on the last stone. Initially, the frog is on the first stone and assume the first jump must be 1 unit.
If the frog's last jump was k units, then its next jump must be either k - 1, k, or k + 1 units. Note that the frog can only jump in the forward direction.*/
public class FrogJump {
	/**
     * @param stones: a list of stones' positions in sorted ascending order
     * @return: true if the frog is able to cross the river or false
     */
    public boolean canCross(int[] stones) {
        // stone : last jump takes how many units
    	HashMap<Integer, HashSet<Integer>> map = new HashMap<>();
    	int len = stones.length;
    	for(int i = 0; i < len; i++){
    		map.put(stones[i], new HashSet<>());
    	}
    	map.get(stones[0]).add(0);
    	for(int i = 0; i < len - 1; i++){
    		int stone = stones[i];
    		HashSet<Integer> lastSteps = new HashSet<>(map.get(stone)); //java bug
    		for(int k : lastSteps){
    			//k - 1;
        		if(map.containsKey(stone + k - 1)){
        			map.get(stone + k - 1).add(k - 1);
        		}
        		//k;
        		if(map.containsKey(stone + k)){
        			map.get(stone + k).add(k);
        		}
        		//k + 1;
        		if(map.containsKey(stone + k + 1)){
        			map.get(stone + k + 1).add(k + 1);
        		}
    		}
    	}
    	return !map.get(stones[len - 1]).isEmpty();
    }
}
