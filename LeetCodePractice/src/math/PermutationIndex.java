package math;
/*Given a permutation which contains no repeated number, find its index in all the permutations of these numbers, which are ordered in lexicographical order. The index begins at 1.

Example
Example 1:

Input:[1,2,4]
Output:1
Example 2:

Input:[3,2,1]
Output:6

take [4, 2, 1, 3] for example, its index is 3 * 3! + 1 * 2! + 0 * 1! + 0 * 0! + 1
*/
public class PermutationIndex {
	/**
     * @param A: An array of integers
     * @return: A long integer
     */
    public long permutationIndex(int[] A) {
        int len = A.length;
        long factor = 1;
        for(int i = 1; i < len; i++){
        	factor *= i;
        }
        long res = 0, top = len - 1;
        for(int i = 0; i < len; i++){
        	int count = 0;
        	for(int j = i + 1; j < len; j++){
        		if(A[i] > A[j]){
        			count++;
        		}
        	}
        	res += factor * count;
        	if(top != 0){
        		factor /= top;
            	top--;
        	}
        }
        res++;
        return res;
    }
}
