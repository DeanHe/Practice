package Math;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

/*
Given an array of integers target. From a starting array, A consisting of all 1's, you may perform the following procedure :

        let x be the sum of all elements currently in your array.
        choose index i, such that 0 <= i < target.size and set the value of A at index i to x.
        You may repeat this procedure as many times as needed.
        Return True if it is possible to construct the target array from A otherwise return False.



        Example 1:

        Input: target = [9,3,5]
        Output: true
        Explanation: Start with [1, 1, 1]
        [1, 1, 1], sum = 3 choose index 1
        [1, 3, 1], sum = 5 choose index 2
        [1, 3, 5], sum = 9 choose index 0
        [9, 3, 5] Done
        Example 2:

        Input: target = [1,1,1,2]
        Output: false
        Explanation: Impossible to create target array from [1,1,1,1].
        Example 3:

        Input: target = [8,5]
        Output: true


        Constraints:

        N == target.length
        1 <= target.length <= 5 * 10^4
        1 <= target[i] <= 10^9
*/
public class ConstructTargetArrayWithMultipleSums {
    public boolean isPossible(int[] target) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        long sum = 0;
        for(int n : target){
            sum += n;
            pq.offer(n);
        }
        while (true){
            int max = pq.poll();
            sum -= max; // sum now is the rest sum
            // largest number is 1.
            if(max == 1){
                return true;
            }
            // case [1, max]
            if(sum == 1){
                return true;
            }
            // case [1, 1, 2] or [max]
            if(max <= sum || sum == 0){
                return false;
            }
            int remain =  (int)(max % sum);
            // case [x, y, k * (x + y)]
            if(remain == 0){
                return false;
            }
            pq.offer(remain);
            sum += remain;
        }
    }
}
