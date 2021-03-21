package BinarySearch;

/*
Count the unique elements in an Sorted Array.

Example:

Input: [1, 1, 1, 1, 2, 2, 2, 2, 5, 5, 5, 7, 7, 8, 8, 10]
Output = 6
 */
public class UniqueElementsInaSortedArray {
    public void test() {
        int[] array = {1, 1, 1, 1, 2, 2, 2, 2, 5, 5, 5, 7, 7, 8, 8, 10};
        System.out.println(countUnique(array));
    }

    public int countUnique(int[] array) {
        int res = 0;
        for (int i = 0; i < array.length; ) {
            int end = search(array, array[i], i);
            i = end + 1;
            res++;
        }
        return res;
    }

    private int search(int[] array, int target, int start) {
        int end = array.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (array[mid] == target) {
                start = mid;
            } else {
                end = mid;
            }
        }
        if (array[end] == target) {
            return end;
        }
        return start;
    }
}
