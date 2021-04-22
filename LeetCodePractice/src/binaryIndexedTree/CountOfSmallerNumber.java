package binaryIndexedTree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
        Give you an integer array (index from 0 to n-1, where n is the size of this array,
        value from 0 to 10000) and an query list.
        For each query, give you an integer, return the number of element in the array that are smaller than the given integer.
        We suggest you finish problem Segment Tree Build and Segment Tree Query II first.

        Have you met this question in a real interview?

        Example
        Example 1:
        Input: array =[1,2,7,8,5] queries =[1,8,5]
        Output:[0,4,2]

        Example 2:
        Input: array =[3,4,5,8] queries =[2,4]
        Output:[0,1]
*/
public class CountOfSmallerNumber {
    int[] bit;

    /**
     * @param A:       An integer array
     * @param queries: The query list
     * @return: The number of element in the array that are smaller that the given integer
     */
    public List<Integer> countOfSmallerNumber(int[] A, int[] queries) {
        bit = new int[A.length + queries.length + 1];
        List<Integer> res = new ArrayList<>();
        discretization(A, queries);
        for (int i = 0; i < A.length; i++) {
            update(A[i]);
        }
        for (int i = 0; i < queries.length; i++) {
            int count = getPreSum(queries[i] - 1);
            res.add(count);
        }
        return res;
    }

    private void discretization(int[] nums, int[] queries) {
        // combine nums and queries
        int[] sorted = new int[nums.length + queries.length];
        System.arraycopy(nums, 0, sorted, 0, nums.length);
        System.arraycopy(queries, 0, sorted, nums.length, queries.length);
        Arrays.sort(sorted);
        for (int i = 0; i < nums.length; i++) {
            nums[i] = Arrays.binarySearch(sorted, nums[i]);
        }
        for (int i = 0; i < queries.length; i++) {
            queries[i] = Arrays.binarySearch(sorted, queries[i]);
        }
    }

    private void update(int idx) {
        for (int i = idx + 1; i < bit.length; i += lowbit(i)) {
            bit[i]++;
        }
    }

    private int getPreSum(int idx) {
        int sum = 0;
        for (int i = idx + 1; i > 0; i -= lowbit(i)) {
            sum += bit[i];
        }
        return sum;
    }

    private int lowbit(int x) {
        return x & -x;
    }
}
