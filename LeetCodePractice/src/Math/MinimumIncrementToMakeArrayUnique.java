package Math;

import java.util.Arrays;

/*Given an array of integers A, a move consists of choosing any A[i], and incrementing it by 1.
Return the least number of moves to make every value in A unique.

Example 1:

Input: [1,2,2]
Output: 1
Explanation:  After 1 move, the array could be [1, 2, 3].
Example 2:

Input: [3,2,1,2,1,7]
Output: 6
Explanation:  After 6 moves, the array could be [3, 4, 1, 2, 5, 7].
It can be shown with 5 or less moves that it is impossible for the array to have all unique values.
 
Note:
0 <= A.length <= 40000
0 <= A[i] < 40000*/
public class MinimumIncrementToMakeArrayUnique {
	public int minIncrementForUnique(int[] A) {
        int ans = 0;
        if(A == null || A.length == 0){
            return ans;
        }
        int[] map = new int[10000];
        for(int a : A){
        	map[a]++;
        }
        int duplicate = 0;
        for(int x = 0; x < 10000; x++){
        	if(map[x] > 1){
        		duplicate += map[x] - 1;
        		ans -= x *(map[x] - 1);
        	} else if(map[x] == 0 && duplicate > 0){
        		duplicate--;
        		ans += x;
        	}
        }
        return ans;
    }
	
	public int minIncrementForUniqueBySort(int[] A) {
		int ans = 0;
        if(A == null || A.length == 0){
            return ans;
        }
        Arrays.sort(A);
        int pre = A[0];
        for(int i  = 1; i < A.length; i++){
        	if(A[i] < pre + 1){
        		ans += pre + 1 - A[i];
                A[i] = pre + 1;
        	} 
        	pre = A[i];
        }
        return ans;
    }
}
