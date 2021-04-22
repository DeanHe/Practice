package greedy;

import java.util.ArrayList;
import java.util.Arrays;

/*
Suppose you have a random list of people standing in a queue. Each person is described by a pair of integers (h, k), where h is the height of the person and k is the number of people in front of this person who have a height greater than or equal to h. Write an algorithm to reconstruct the queue.

Note:
The number of people is less than 1,100.

Example

Input:
[[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]

Output:
[[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]
*/
public class QueueReconstructionByHeight {
	public int[][] reconstructQueue(int[][] people) {
		int len = people.length;
        Arrays.sort(people, (a, b) -> { 
        	if(a[0] == b[0]){
        		return a[1] - b[1]; // index smaller place ahead
        	}
        	return b[0] - a[0]; // height bigger place ahead;
        });
        ArrayList<int[]> ls = new ArrayList<>();
        for(int i = 0; i < len; i++){
        	int[] person = people[i];
        	int pos = person[1];
        	ls.add(pos, person);
        }
        int[][] res = new int[len][2];
        for(int i = 0; i < len; i++){
        	res[i] = ls.get(i);
        }
        return res;

    }
}
