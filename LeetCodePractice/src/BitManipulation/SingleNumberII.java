package BitManipulation;
/*Given 3*n + 1 non-negative integer, every numbers occurs triple times except one, find it.

Example
Example 1:
	Input:  [1,1,2,3,3,3,2,2,4,1]
	Output:  4


Example 2:
	Input: [2,1,2,2]
	Output:  1
	
Challenge
One-pass, constant extra space.*/
public class SingleNumberII {
	public int singleNumberII(int[] A) {
        if (A == null || A.length == 0) {
            return -1;
        }
        int result=0;
        int[] bits=new int[32];
        for (int i = 0; i < 32; i++) {
            for(int j = 0; j < A.length; j++) {
                bits[i] += A[j] >> i & 1;
                bits[i] %= 3;
            }

            result |= (bits[i] << i);
        }
        return result;
    }
}
