package array;

/*
Given a positive integer n, find the smallest integer which has exactly the same digits existing in the integer n and is greater in value than n.
If no such positive integer exists, return -1.

Note that the returned integer should fit in 32-bit integer, if there is a valid answer but it does not fit in 32-bit integer, return -1.



Example 1:

Input: n = 12
Output: 21
Example 2:

Input: n = 21
Output: -1


Constraints:

1 <= n <= 2^31 - 1

analysis:
same as Next Permutation
 */
public class NextGreaterElementIII {
    public int nextGreaterElement(int n) {
        char[] arr = String.valueOf(n).toCharArray();
        int idx = -1;
        for (int i = arr.length - 2; i >= 0; i--) {
            if (arr[i] < arr[i + 1]) {
                idx = i;
                break;
            }
        }
        if (idx == -1) {
            return -1;
        }
        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[idx] < arr[i]) {
                swap(arr, idx, i);
                break;
            }
        }
        reverse(arr, idx + 1, arr.length - 1);
        try {
            return Integer.valueOf(String.valueOf(arr));
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private void swap(char[] arr, int idx, int i) {
        char temp = arr[idx];
        arr[idx] = arr[i];
        arr[i] = temp;
    }

    private void reverse(char[] arr, int s, int e) {
        while (s < e) {
            char temp = arr[s];
            arr[s] = arr[e];
            arr[e] = temp;
            s++;
            e--;
        }
    }
}
