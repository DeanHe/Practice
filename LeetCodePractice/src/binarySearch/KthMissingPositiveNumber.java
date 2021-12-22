package binarySearch;
/*
Given an array arr of positive integers sorted in a strictly increasing order, and an integer k.
Find the kth positive integer that is missing from this array.

Example 1:
Input: arr = [2,3,4,7,11], k = 5
if no miss   [1,2,3,4,5]
Output: 9
Explanation: The missing positive integers are [1,5,6,8,9,10,12,13,...]. The 5th missing positive integer is 9.

Example 2:
Input: arr = [1,2,3,4], k = 2
Output: 6
Explanation: The missing positive integers are [5,6,7,...]. The 2nd missing positive integer is 6.

Constraints:
1 <= arr.length <= 1000
1 <= arr[i] <= 1000
1 <= k <= 1000
arr[i] < arr[j] for 1 <= i < j <= arr.length

hint:
Keep track of how many positive numbers are missing as you scan the array.

analysis:
arr[i] - (i + 1) will be the count of number that missed.
we compare the missing number count of arr[0:mid], with expect missing count k
if # miss arr[0:mid] < k, indicate the missing number is in range arr[mid + 1:]
if # miss arr[0:mid] >= k,  indicate the missing number is in range arr[:mid]

TC O(logN)
SC O(1)
 */
public class KthMissingPositiveNumber {
    public int findKthPositive(int[] arr, int k) {
        int len = arr.length, s = 0, e = len;
        while(s < e){
            int mid = s + (e - s) / 2;
            int missCnt = arr[mid] - (mid + 1);
            if(missCnt < k){
                s = mid + 1;
            } else {
                e = mid;
            }
        }
        return s + k;
    }
}
