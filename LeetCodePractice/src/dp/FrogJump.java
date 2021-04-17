package dp;

import java.util.HashMap;
import java.util.HashSet;

/*
A frog is crossing a river. The river is divided into some number of units, and at each unit, there may or may not exist a stone. The frog can jump on a stone, but it must not jump into the water.

Given a list of stones' positions (in units) in sorted ascending order, determine if the frog can cross the river by landing on the last stone. Initially, the frog is on the first stone and assumes the first jump must be 1 unit.

If the frog's last jump was k units, its next jump must be either k - 1, k, or k + 1 units. The frog can only jump in the forward direction.



Example 1:

Input: stones = [0,1,3,5,6,8,12,17]
Output: true
Explanation: The frog can jump to the last stone by jumping 1 unit to the 2nd stone, then 2 units to the 3rd stone, then 2 units to the 4th stone, then 3 units to the 6th stone, 4 units to the 7th stone, and 5 units to the 8th stone.
Example 2:

Input: stones = [0,1,2,3,4,8,9,11]
Output: false
Explanation: There is no way to jump to the last stone as the gap between the 5th and 6th stone is too large.


Constraints:

2 <= stones.length <= 2000
0 <= stones[i] <= 2^31 - 1
stones[0] == 0
*/
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
