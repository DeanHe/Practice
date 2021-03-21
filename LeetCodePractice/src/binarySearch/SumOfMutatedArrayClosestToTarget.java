package binarySearch;
/*
Given an integer array arr and a target value target, return the integer value such that when we change all the integers larger than value in the given array to be equal to value, the sum of the array gets as close as possible (in absolute difference) to target.
        In case of a tie, return the minimum such integer.
        Notice that the answer is not necessarily a number from arr.

        Example 1:

        Input: arr = [4,9,3], target = 10
        Output: 3
        Explanation: When using 3 arr converts to [3, 3, 3] which sums 9 and that's the optimal answer.
        Example 2:

        Input: arr = [2,3,5], target = 10
        Output: 5
        Example 3:

        Input: arr = [60864,25176,27249,21296,20204], target = 56803
        Output: 11361


        Constraints:

        1 <= arr.length <= 10^4
        1 <= arr[i], target <= 10^5
*/
public class SumOfMutatedArrayClosestToTarget {
    public int findBestValue(int[] arr, int target) {
        int sum = 0, m = Integer.MIN_VALUE;
        for(int n : arr){
            sum += n;
            m = Math.max(m, n);
        }
        if(target >= sum){
            return m;
        }
        int start = 0, end = m;
        while(start + 1 < end){
            int mid = (start + end) / 2;
            sum = 0;
            for(int n : arr){
                if(n > mid){
                    sum += mid;
                } else {
                    sum += n;
                }
            }
            if(sum >= target){
                end = mid;
            } else {
                start = mid;
            }
        }
        int sum_s = 0, sum_e = 0;
        for(int n : arr){
            if(n > start){
                sum_s += start;
            } else {
                sum_s += n;
            }
        }
        for(int n : arr){
            if(n > end){
                sum_e += end;
            } else {
                sum_e += n;
            }
        }
        return Math.abs(target - sum_s) <= Math.abs(target - sum_e) ? start : end;
    }
}
