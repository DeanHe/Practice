package binaryIndexedTree;

import java.util.Arrays;

/*
		Given an integer array in the construct method, implement two methods query(start, end) and modify(index, value):
		For query(start, end), return the sum from index start to index end in the given array.
		For modify(index, value), modify the number in the given index to value
		We suggest you finish problem Segment Tree Build, Segment Tree Query and Segment Tree Modify first.

		Example
		Example 1
		Input:
		[1,2,7,8,5]
		[query(0,2),modify(0,4),query(0,1),modify(2,1),query(2,4)]
		Output: [10,6,14]
		Explanation:
		Given array A = [1,2,7,8,5].
		After query(0, 2), 1 * 2 * 7 = 14,
		After modify(0, 4), change A[0] from 1 to 4, A = [4,2,7,8,5].
		After query(0, 1), 4 * 2 = 8.
		After modify(2, 1), change A[2] from 7 to 1，A = [4,2,1,8,5].
		After query(2, 4), 1 * 8 * 5 = 40.

		Example 2
		Input:
		[1,2,3,4,5]
		[query(0,0),query(1,2),quert(3,4)]
		Output: [1,5,9]
		Explantion:
		1 = 1
		2 * 3 = 6
		4 * 5 = 20
		Challenge
		O(logN) time for query and modify.
*/
public class IntervalProductII {
	int[] arr, bit;
	/**
	 * @param A: An integer array
	 */
	public IntervalProductII(int[] A) {
		int len = A.length;
		arr = new int[len];
		bit = new int[len + 1];
		// init
		Arrays.fill(arr, 1);
		for(int i = 1; i < bit.length; i++){
		    bit[i] = 1;
        }
		for(int i = 0; i < len; i++){
			modify(i, A[i], 1);
		}
	}

	/**
	 * @param start: An integer
	 * 
	 * @param end: An integer
	 * 
	 * @return: The product from start to end
	 */
	public int query(int start, int end) {
		return getPreProduct(end) / getPreProduct(start - 1);
	}

	/**
	 * @param index: An integer
	 * 
	 * @param value: the new value
     *
     * @param old: the old value
	 * 
	 * @return: nothing
	 */
	public void modify(int index, int value, int old) {
		for(int i = index + 1; i < bit.length; i += lowbit(i)){
            bit[i] /= old;
			bit[i] *= value;
		}
		arr[index] = value;
	}

	private int getPreProduct(int idx){
		int res = 1;
		for(int i = idx + 1; i > 0; i -= lowbit(i)){
			res *= bit[i];
		}
		return res;
	}

	private int lowbit(int x){
		return x & -x;
	}
}
