package PrefixSum;
/*In an array A of 0s and 1s, how many non-empty subarrays have sum S?

Example 1:
Input: A = [1,0,1,0,1], S = 2
Output: 4
Explanation: 
The 4 subarrays are bolded below:
[1,0,1,0,1]
[1,0,1,0,1]
[1,0,1,0,1]
[1,0,1,0,1]
Note:

A.length <= 30000
0 <= S <= A.length
A[i] is either 0 or 1.*/
public class BinarySubarraysWithSum {
	public int numSubarraysWithSum(int[] A, int S) {
        int len = A.length, preSum = 0, res = 0;
        // preSum : occurrence
        int[] map = new int[len + 1];
        map[0] = 1;
        for(int a : A){
        	preSum += a;
        	if(preSum >= S){
        		res += map[preSum - S];
        	}
        	map[preSum]++;
        }
        return res;
    }
}
