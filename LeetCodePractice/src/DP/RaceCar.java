package DP;
/*Your car starts at position 0 and speed +1 on an infinite number line.  (Your car can go into negative positions.)
Your car drives automatically according to a sequence of instructions A (accelerate) and R (reverse).
When you get an instruction "A", your car does the following: position += speed, speed *= 2.
When you get an instruction "R", your car does the following: if your speed is positive then speed = -1 , otherwise speed = 1.  (Your position stays the same.)
For example, after commands "AAR", your car goes to positions 0->1->3->3, and your speed goes to 1->2->4->-1.
Now for some target position, say the length of the shortest sequence of instructions to get there.

Example 1:
Input: 
target = 3
Output: 2
Explanation: 
The shortest instruction sequence is "AA".
Your position goes from 0->1->3.
Example 2:
Input: 
target = 6
Output: 5
Explanation: 
The shortest instruction sequence is "AAARA".
Your position goes from 0->1->3->7->7->6.*/
public class RaceCar {
	public int racecar(int target) {
        int[] dp = new int[target + 1];
        for(int i = 1; i <= target; i++){
        	dp[i] = Integer.MAX_VALUE;
        	int countOfAbeforeFirstR = 1, j = 1;
        	while(j < i){
        		int countOfAbeforeSecondR = 0;
        		//case 1: 2 R before reaching i
        		for(int k = 0; k < j;){
        			dp[i] = Math.min(dp[i], 1 + countOfAbeforeFirstR + 1 + countOfAbeforeSecondR + dp[i - (j - k)]);
        			k = k + (1 << countOfAbeforeSecondR);
        			countOfAbeforeSecondR++;
        		}
        		j = j + (1 << countOfAbeforeFirstR);
        		countOfAbeforeFirstR++;
        	}
        	if(j == i){
        		//case 2: reach i without R
        		dp[i] = Math.min(dp[i], countOfAbeforeFirstR); 
        	} else {
        		// case 3: pass i, then R, then reach i
        		dp[i] = Math.min(dp[i], countOfAbeforeFirstR + 1 + dp[j - i]);
        	}
        }
        return dp[target];
    }
}
