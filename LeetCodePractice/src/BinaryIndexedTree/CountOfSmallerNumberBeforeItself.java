package BinaryIndexedTree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/*
Give you an integer arr (index from 0 to n-1, where n is the size of this arr, data value from 0 to 10000) .
For each element Ai in the arr, count the number of element left this element Ai is smaller than it and return count number arr.

Example
Example 1:

Input:
[1,2,7,8,5]
Output:
[0,1,2,3,2]
Example 2:

Input:
[7,8,2,1,3]
Output:
[0,1,0,0,2]
Clarification
Before you do this, you'd better complete the following three questions： Segment Tree Build， Segment Tree Query II，and Count of Smaller Number left itself I 。

Notice
We suggest you finish problem Segment Tree Build, Segment Tree Query II and Count of Smaller Number first.
*/

public class CountOfSmallerNumberBeforeItself {
    int[] bit;
	/**
     * @param A: an integer array
     * @return: A list of integers includes the index of the first number and the index of the last number
     */
    public List<Integer> countOfSmallerNumberII(int[] A) {
        int len = A.length;
        bit = new int[len + 1];
        discretization(A);
        List<Integer> res = new ArrayList<>();
        for(int i = 0; i < len; i++){
            int count = getPreSum(A[i] - 1);
            res.add(count);
            update(A[i]);
        }
        return res;
    }

    private void discretization(int[] nums) {
        int[] sorted = nums.clone();
        Arrays.sort(sorted);
        for (int i = 0; i < nums.length; i++) {
            nums[i] = Arrays.binarySearch(sorted, nums[i]);
        }
    }

    private int getPreSum(int idx){
        int sum = 0;
        for(int i = idx + 1; i > 0; i -= lowbit(i)){
            sum += bit[i];
        }
        return sum;
    }

    private void update(int idx){
        for(int i = idx + 1; i < bit.length; i += lowbit(i)){
            bit[i]++;
        }
    }

    private int lowbit(int x){
        return x & -x;
    }
}
