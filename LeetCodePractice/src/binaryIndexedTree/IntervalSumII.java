package binaryIndexedTree;

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
		After query(0, 2), 1 + 2 + 7 = 10,
		After modify(0, 4), change A[0] from 1 to 4, A = [4,2,7,8,5].
		After query(0, 1), 4 + 2 = 6.
		After modify(2, 1), change A[2] from 7 to 1，A = [4,2,1,8,5].
		After query(2, 4), 1 + 8 + 5 = 14.

		Example 2
		Input:
		[1,2,3,4,5]
		[query(0,0),query(1,2),quert(3,4)]
		Output: [1,5,9]
		Explantion:
		1 = 1
		2 + 3 = 5
		4 + 5 = 9
		Challenge
		O(logN) time for query and modify.
*/
public class IntervalSumII {
	int[] arr, bit;
	/**
	 * @param A: An integer array
	 */
	public IntervalSumII(int[] A) {
		int len = A.length;
		arr = new int[len];
		bit = new int[len + 1];
		for(int i = 0; i < len; i++){
			modify(i, A[i]);
		}
	}

	/**
	 * @param start: An integer
	 * 
	 * @param end: An integer
	 * 
	 * @return: The sum from start to end
	 */
	public long query(int start, int end) {
		// write your code here
		return getPreSum(end) - getPreSum(start - 1);
	}

	/**
	 * @param index: An integer
	 * 
	 * @param value: An integer
	 * 
	 * @return: nothing
	 */
	public void modify(int index, int value) {
		int diff = value - arr[index];
		arr[index] = value;
		for(int i = index + 1; i < bit.length; i += lowbit(i)){
			bit[i] += diff;
		}
	}

	private int getPreSum(int idx){
		int sum = 0;
		for(int i = idx + 1; i > 0; i -= lowbit(i)){
			sum += bit[i];
		}
		return sum;
	}

	private int lowbit(int x){
		return x & -x;
	}
}
