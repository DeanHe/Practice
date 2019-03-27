package Array;
/*Given an unsorted integer array, find the first missing positive integer.

Example
Example 1:

Input:[1,2,0]
Output:3
Example 2:

Input:[3,4,-1,1]
Output:2
Challenge
Your algorithm should run in O(n) time and uses constant space.*/
// http://www.cnblogs.com/yuzhangcmu/p/4200096.html
// bucket-sort like algorithm
public class FirstMissingPositive {
	public int firstMissingPositive(int[] A) {
		if(A == null){
            return 1;
        }
        int size = A.length;
        for (int i = 0; i < size; i++) {
            while (A[i] > 0 && A[i] <= size && A[i] != (i + 1)) {
                if (A[i] == A[A[i]-1]) {
                    break;
                }
                int tmp = A[A[i]-1];
                A[A[i]-1] = A[i];
                A[i] = tmp;
            }
        }

        for(int i = 0; i < size; i++){
            if(A[i] != i + 1){
                return i + 1;
            }
        }
        return size + 1;
	}
}
