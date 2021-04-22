package binaryIndexedTree;

/*
Given a string num representing the digits of a very large integer and an integer k.

You are allowed to swap any two adjacent digits of the integer at most k times.

Return the minimum integer you can obtain also as a string.



Example 1:


Input: num = "4321", k = 4
Output: "1342"
Explanation: The steps to obtain the minimum integer from 4321 with 4 adjacent swaps are shown.
Example 2:

Input: num = "100", k = 1
Output: "010"
Explanation: It's ok for the output to have leading zeros, but the input is guaranteed not to have any leading zeros.
Example 3:

Input: num = "36789", k = 1000
Output: "36789"
Explanation: We can keep the number without any swaps.
Example 4:

Input: num = "22", k = 22
Output: "22"
Example 5:

Input: num = "9438957234785635408", k = 23
Output: "0345989723478563548"


Constraints:

1 <= num.length <= 30000
num contains digits only and doesn't have leading zeros.
1 <= k <= 10^9
 */

import java.util.ArrayDeque;
import java.util.Deque;

public class MinimumPossibleIntegerAfterAtMostKAdjacentSwapsOnDigits {
    // Binary Index Tree Solution
    // Initialize BIT with 1 for all indices, which is the character count;
    // Update the count to 0 ( count--) when a character at index i is swapped to the beginning.
    int[] bit;
    public String minIntegerBIT(String num, int k) {
        char[] arr = num.toCharArray();
        int len = arr.length;
        StringBuilder sb = new StringBuilder();
        bit = new int[len + 1];
        for(int i = 0; i < len; i++){
            update(i, 1);
        }
        Deque<Integer>[] digitDeque = new Deque[10];
        for(int digit = 0; digit < 10; digit++){
            digitDeque[digit] = new ArrayDeque<>();
        }
        for(int i = 0; i < len; i++){
            int digit = arr[i] - '0';
            digitDeque[digit].offerLast(i);
        }
        for(int i = 0; i < len; i++){
            for(int digit = 0; digit < 10; digit++){
                if(!digitDeque[digit].isEmpty()){
                    Integer idx = digitDeque[digit].peekFirst();
                    int steps = getPreSum(idx);
                    if(k >= steps){
                        k -= steps;
                        update(idx, -1);
                        digitDeque[digit].pollFirst();
                        sb.append((char)('0' + digit));
                        break;
                    }
                }
            }
        }
        return sb.toString();
    }

    private void update(int idx, int val){
        for(int i = idx + 1; i < bit.length; i += lowbit(i)){
            bit[i] += val;
        }

    }

    private int getPreSum(int idx){
        int sum = 0;
        for(int i = idx; i > 0; i -= lowbit(i)){
            sum += bit[i];
        }
        return sum;
    }

    private int lowbit(int x){
        return x & (-x);
    }
    // naive solution
    public String minInteger(String num, int k) {
        char[] arr = num.toCharArray();
        dfs(arr, 0, k);
        return  new String(arr);
    }

    private void dfs(char[] arr, int pos, int k){
        if(k == 0 || pos == arr.length){
            return;
        }
        char minNum = arr[pos];
        int minIdx = pos;
        for(int i = pos + 1; i < Math.min(arr.length, pos + k + 1); i++){
            if(arr[i] < minNum){
                minNum = arr[i];
                minIdx = i;
            }
        }
        for(int i = minIdx; i > pos; i--){
            arr[i] = arr[i - 1];
        }
        arr[pos] = minNum;
        dfs(arr, pos + 1, k - (minIdx - pos));
    }

}
