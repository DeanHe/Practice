package PrefixSum;
/*
Given a binary array. We are allowed flip to any size of sub array.
Flipping means changing 1 to 0 and 0 to 1. The task is maximize the number of 1s in the array. Output the maximum number of 1s.

TC O(N)
 */
public class MaximizeTheBinaryArrayByFilppingSubarrayOnce {
    // O(N)
    public int solve(int[] arr){
        int zerosMoreThanOnes = 0, onesCnt = 0, zerosCnt = 0, len = arr.length, sum = 0;
        for (int i = 0; i < len; i++) {
           sum += arr[i];
        }
        for(int i = 0; i < arr.length; i++){
            if(arr[i] == 0){
                zerosCnt++;
            } else {
                onesCnt++;
            }
            if(zerosCnt < onesCnt){
                onesCnt = 0;
                zerosCnt = 0;
            }
            zerosMoreThanOnes = Math.max(zerosMoreThanOnes, zerosCnt - onesCnt);
        }
        return sum + zerosMoreThanOnes;
    }

    // O(N^2)
    int[] ones;
    public int solve2(int[] arr) {
        int res = 0, len = arr.length;
        ones = new int[len + 1];
        for (int i = 0; i < len; i++) {
            if (arr[i] == 1) {
                ones[i + 1] = 1;

            }
            ones[i + 1] += ones[i];
        }
        for (int l = 1; l <= len; l++) {
            for(int i = 0; i + l - 1 < len; i++){
                res = Math.max(res, ones[len] + l - 2 * countOnes(i, i + l - 1));
            }
        }
        return res;
    }

    private int countOnes(int s, int e) {
        return ones[e + 1] - ones[s];
    }
}
