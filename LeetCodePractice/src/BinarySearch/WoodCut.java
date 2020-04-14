package BinarySearch;

/*
Given n pieces of wood with length L[i] (integer array). Cut them into small pieces to guarantee you could have equal or more than k pieces with the same length.
What is the longest length you can get from the n pieces of wood? Given L & k, return the maximum length of the small pieces.

Example
Example 1

Input:
L = [232, 124, 456]
k = 7
Output: 114
Explanation: We can cut it into 7 pieces if any piece is 114cm long, however we can't cut it into 7 pieces if any piece is 115cm long.
Example 2

Input:
L = [1, 2, 3]
k = 7
Output: 0
Explanation: It is obvious we can't make it.
Challenge
O(n log Len), where Len is the longest length of the wood.

Notice
You couldn't cut wood into float length.

If you couldn't get >= k pieces, return 0.
*/public class WoodCut {
    /**
     * @param L: Given n pieces of wood with length L[i]
     * @param k: An integer
     * @return: The maximum length of the small pieces
     */
    public int woodCut(int[] L, int k) {
        // write your code here
        int start = 1, end = 0;
        int res = 0;
        for (int l : L) {
            end = Math.max(end, l);
        }
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (count(L, mid) >= k) {
                start = mid;
            } else {
                end = mid;
            }
        }
        if (count(L, end) >= k) {
            return end;
        } else if (count(L, start) >= k) {
            return start;
        }
        return 0;
    }

    private int count(int[] L, int len) {
        int count = 0;
        for (int l : L) {
            count += l / len;
        }
        return count;
    }
}
