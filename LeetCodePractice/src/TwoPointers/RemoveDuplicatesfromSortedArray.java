package TwoPointers;

public class RemoveDuplicatesfromSortedArray {
	public int removeDuplicates(int[] A) {
       	if (A.length <= 1) {
			return A.length;
		}
        //two pointer
		int i = 0, j = 1;
		while (j < A.length) {
			if (A[i] == A[j]) {
				j++;
			} else {
				A[++i] = A[j++];
			}
		}
		return i + 1;
	}
}
