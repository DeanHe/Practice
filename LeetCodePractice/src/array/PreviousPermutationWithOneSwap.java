package array;

/*
Given an array of positive integers arr (not necessarily distinct), return the lexicographically largest permutation that is smaller than arr,
that can be made with exactly one swap (A swap exchanges the positions of two numbers arr[i] and arr[j]). If it cannot be done, then return the same array.

Example 1:
Input: arr = [3,2,1]
Output: [3,1,2]
Explanation: Swapping 2 and 1.

Example 2:
Input: arr = [1,1,5]
Output: [1,1,5]
Explanation: This is already the smallest permutation.

Example 3:
Input: arr = [1,9,4,6,7]
Output: [1,7,4,6,9]
Explanation: Swapping 9 and 7.

Example 4:
Input: arr = [3,1,1,3]
Output: [1,3,1,3]
Explanation: Swapping 1 and 3.

Constraints:
1 <= arr.length <= 10^4
1 <= arr[i] <= 10^4
hint
1: You need to swap two values, one larger than the other. Where is the larger one located?
 */
public class PreviousPermutationWithOneSwap {
    public int[] prevPermOpt1(int[] arr) {
        if (arr.length <= 1) {
            return arr;
        }
        int idx = -1;
        // find largest i such that A[i] > A[i + 1]
        for (int i = arr.length - 2; i >= 0; i--) {
            if (arr[i] > arr[i + 1]) {
                idx = i;
                break;
            }
        }
        // the array already sorted, not smaller permutation
        if (idx == -1) {
            return arr;
        }
        // find the largest j such that A[j] > A[i], then swap them
        for (int i = arr.length - 1; i > idx; i--) {
            // the second check to skip duplicate numbers
            if (arr[idx] > arr[i] && arr[i] != arr[i - 1]) {
                int tmp = arr[idx];
                arr[idx] = arr[i];
                arr[i] = tmp;
                break;
            }
        }
        return arr;
    }
}
