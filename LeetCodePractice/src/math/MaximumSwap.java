package math;

/*
Given a non-negative integer, you could swap two digits at most once to get the maximum valued number. Return the maximum valued number you could get.

Example 1:
Input: 2736
Output: 7236
Explanation: Swap the number 2 and the number 7.

Example 2:
Input: 9973
Output: 9973
Explanation: No swap.
Note:
The given number is in the range [0, 108]

analysis:
TC O(N)
*/

public class MaximumSwap {
    public int maximumSwap(int num) {
        char[] arr = Integer.toString(num).toCharArray();
        int len = arr.length;
        int[] lastIdx = new int[10];
        for(int i = 0; i < len; i++){
            char c = arr[i];
            lastIdx[c - '0'] = i; // the last idx of digit c
        }
        for(int i = 0; i < len; i++){
            char c = arr[i];
            int digit = c - '0';
            for(int n = 9; n > digit; n--){
                if(lastIdx[n] > i){
                    arr[i] = (char)('0' + n);
                    arr[lastIdx[n]] = c;
                    return Integer.valueOf(String.valueOf(arr));
                }
            }
        }
        return num;
    }
}
