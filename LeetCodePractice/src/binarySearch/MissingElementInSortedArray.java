package binarySearch;
/*
Given a sorted array A of unique numbers, find the K-th missing number starting from the leftmost number of the array.

Example 1:
Input: A = [4,7,9,10], K = 1
Output: 5
Explanation:
The first missing number is 5.

Example 2:
Input: A = [4,7,9,10], K = 3
Output: 8
Explanation:
The missing numbers are [5,6,8,…], hence the third missing number is 8.

Example 3:
Input: A = [1,2,4], K = 3
Output: 6
Explanation:
The missing numbers are [3,5,6,7,…], hence the third missing number is 6.

Note:
1 <= A.length <= 50000
1 <= A[i] <= 10^7
1 <= K <= 10^8

analysis:
when the missCnt is >= k, then the index be located in (s, m]
when the missCnt is < k, then the index be located in (m, e]
 */
public class MissingElementInSortedArray {
    public int missingElement(int[] nums, int k){
        int len = nums.length, s = 0, e = len - 1;
        int missCnt = nums[e] - nums[s] - (len - 1);
        if(missCnt < k){
            return nums[e] + k - missCnt;
        }
        while(s + 1 < e){
            int mid = s + (e - s) / 2;
            missCnt = nums[mid] - nums[s] - (mid - s);
            if(missCnt < k){
                k -= missCnt;
                s = mid;
            } else {
                e = mid;
            }
        }
        return nums[s] + k;
    }
}
