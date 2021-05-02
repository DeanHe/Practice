package array;
/*
Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.

If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).

The replacement must be in-place and use only constant extra memory.

Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.

1,2,3 → 1,3,2
3,2,1 → 1,2,3
1,1,5 → 1,5,1

analysis:
1.Start from its last element, traverse backward to find the first one with index i that satisfy num[i-1] < num[i]. So, elements from num[i] to num[n-1] is descending order.
2.To find the next permutation, we have to swap some numbers at different positions, to minimize the increased amount, we have to make the highest changed position as high as possible.
Notice that index larger than or equal to i is not possible as num[i,n-1] is reversely sorted.
So, we want to increase the number at index i-1, clearly, swap it with the smallest number between num[i,n-1] that is larger than num[i-1].
For example, original number is 121543321, we want to swap the '1' at position 2 with '2' at position 7.
3.The last step is to make the remaining higher position part as small as possible, we just have to reversely sort the num[i,n-1]
 */
public class NextPermutation {
	public void nextPermutation(int[] nums) {
		if (nums == null || nums.length == 0) {
			return;
		}
		int len = nums.length;
		// step 1 Find the largest index k such that a[k] < a[k + 1]. If no such
		// index exists, the permutation is the last permutation.
		int idx = -1;
		for (int i = len - 2; i >= 0; i--) {
			if (nums[i] < nums[i + 1]) {
				idx = i;
				break;
			}
		}
		if (idx == -1) {
			reverse(nums, 0, len - 1);
			return;
		}
		// step 2 Find the largest index l such that a[k] < a[l]. Since k + 1
		// is such an index, l is well defined and satisfies k < l.
		for (int i = len - 1; i > idx; i--) {
			if (nums[idx] < nums[i]) {
				swap(nums, i, idx);
				break;
			}
		}
		// step 3 Reverse the sequence from a[k + 1] up to and including the
		// final element a[n]
		reverse(nums, idx + 1, len - 1);
	}

	private void swap(int[] array, int i, int j) {
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}

	private void reverse(int[] array, int s, int e){
		while (s < e){
			swap(array, s, e);
			s++;
			e--;
		}
	}
}
