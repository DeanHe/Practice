package BFS;

import java.util.*;

/*You have a lock in front of you with 4 circular wheels. Each wheel has 10 slots: '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'. The wheels can rotate freely and wrap around: for example we can turn '9' to be '0', or '0' to be '9'. Each move consists of turning one wheel one slot.

The lock initially starts at '0000', a string representing the state of the 4 wheels.

You are given a list of deadends dead ends, meaning if the lock displays any of these codes, the wheels of the lock will stop turning and you will be unable to open it.

Given a target representing the value of the wheels that will unlock the lock, return the minimum total number of turns required to open the lock, or -1 if it is impossible.

Example 1:
Input: deadends = ["0201","0101","0102","1212","2002"], target = "0202"
Output: 6
Explanation:
A sequence of valid moves would be "0000" -> "1000" -> "1100" -> "1200" -> "1201" -> "1202" -> "0202".
Note that a sequence like "0000" -> "0001" -> "0002" -> "0102" -> "0202" would be invalid,
because the wheels of the lock become stuck after the display becomes the dead end "0102".
Example 2:
Input: deadends = ["8888"], target = "0009"
Output: 1
Explanation:
We can turn the last wheel in reverse to move from "0000" -> "0009".
Example 3:
Input: deadends = ["8887","8889","8878","8898","8788","8988","7888","9888"], target = "8888"
Output: -1
Explanation:
We can't reach the target without getting stuck.
Example 4:
Input: deadends = ["0000"], target = "8888"
Output: -1*/
public class OpenTheLock {
	public int openLock(String[] deadends, String target) {
        int step = 0;
        String start = "0000";
        if(target == start){
            return step;
        }
        HashSet<String> deadEnds= new HashSet<>(Arrays.asList(deadends));
        if(deadEnds.contains(target) || deadEnds.contains(start)) {
        	return -1;
        }
        HashSet<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        queue.offer(target);
        visited.add(target);
        while(!queue.isEmpty()){
        	int size = queue.size();
            for(int i = 0; i < size; i++){
                String cur = queue.poll();
                if(target.equals(cur)){
                    return step;
                }
                ArrayList<String> neighbors = getNeighbors(cur);
                for(String nb : neighbors){
                    if(!deadEnds.contains(nb) && !visited.contains(nb)){
                        queue.offer(nb);
                        visited.add(nb);
                    }
                }
            }
            step++;
        }
        return -1;
    }
    private ArrayList<String> getNeighbors(String s){
        ArrayList<String> nbs = new ArrayList<>();
        int len = s.length();
        for(int i = 0; i < len; i++){
            StringBuilder sb = new StringBuilder(s);
            char c = s.charAt(i);
            char[] tempChars = rotate(c);
            sb.setCharAt(i, tempChars[0]);
            nbs.add(sb.toString());
            sb = new StringBuilder(s);
            sb.setCharAt(i, tempChars[1]);
            nbs.add(sb.toString());
        }
        return nbs;
    }
    
    private char[] rotate(char c){
    	int ic = Integer.parseInt(String.valueOf(c));
        char[] res = new char[2];
        res[0] = (char)((ic - 1 + 10) % 10 + '0');
        res[1] = (char)((ic + 1) % 10 + '0');
        return res;
    }
}
