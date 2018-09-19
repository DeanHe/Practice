package Array;

public class NextPermutation {
	public void nextPermutation(int[] num) {
		if (num == null || num.length == 0) {
			return;
		}
		// step 1 Find the largest index k such that a[k] < a[k + 1]. If no such
		// index exists, the permutation is the last permutation.
		int loc = -1;
		for (int i = num.length - 2; i >= 0; i--) {
			if (num[i] < num[i + 1]) {
				loc = i;
				break;
			}
		}
		if (loc == -1) {
			for (int i = 0; i < num.length / 2; i++) {
				swap(num, i, num.length - 1 - i);
			}
			return;
		}
		// step 2 Find the smallest index l such that a[k] < a[l]. Since k + 1
		// is such an index, l is well defined and satisfies k < l.
		for (int i = num.length - 1; i >= 0; i--) {
			if (num[loc] < num[i]) {
				swap(num, i, loc);
				break;
			}
		}
		// step 3 Reverse the sequence from a[k + 1] up to and including the
		// final element a[n]
		for (int i = loc + 1; i < (num.length + loc + 1) / 2; i++) {
			swap(num, i, num.length + loc - i);
		}
		return;
	}

	public void swap(int[] array, int i, int j) {
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
}
