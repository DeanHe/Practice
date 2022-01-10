package binarySearch;

/*
Given an integer array sorted in non-decreasing order, there is exactly one integer in the array that occurs more than 25% of the time, return that integer.

Example 1:
Input: arr = [1,2,2,6,6,6,6,7,10]
Output: 6
Example 2:
Input: arr = [1,1]
Output: 1


Constraints:
1 <= arr.length <= 10^4
0 <= arr[i] <= 10^5

hint:
1 Divide the array in four parts [1 - 25%] [25 - 50 %] [50 - 75 %] [75% - 100%]
2 The answer should be in one of the ends of the intervals.
3 In order to check which is element is the answer we can count the frequency with binarySearch.
 */
public class ElementAppearingMoreThanTwentyFivePercentInSortedArray {
    public int findSpecialInteger(int[] arr) {
        int len = arr.length;
        if (len == 1) {
            return arr[0];
        }
        int[] candidates = {arr[len / 4], arr[len / 2], arr[len * 3 / 4]};
        for (int n : candidates) {
            int start = binarySearch(arr, n, true);
            int end = binarySearch(arr, n, false);
            if (end - start + 1 > len / 4) {
                return n;
            }
        }
        return -1;
    }

    private int binarySearch(int[] arr, int n, boolean leftward) {
        int s = 0, e = arr.length - 1;
        while (s + 1 < e) {
            int mid = s + (e - s) / 2;
            if (leftward) {
                if (arr[mid] >= n) {
                    e = mid;
                } else {
                    s = mid;
                }
            } else {
                if (arr[mid] <= n) {
                    s = mid;
                } else {
                    e = mid;
                }
            }
        }
        if (leftward) {
            if (arr[s] == n) {
                return s;
            }
            if (arr[e] == n) {
                return e;
            }
        } else {
            if (arr[e] == n) {
                return e;
            }
            if (arr[s] == n) {
                return s;
            }
        }
        return -1;
    }
}
