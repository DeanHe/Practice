package dp;
/*
Given a binary array consisting of only zeroes and ones. The task is to find:


The number of subarrays which has only 1 in it.
The number of subarrays which has only 0 in it.
Examples:


Input: arr[] = {0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1}
Output:
The number of subarrays consisting of 0 only: 7
The number of subarrays consisting of 1 only: 7

Input: arr[] = {1, 1, 0, 0, 1, 0, 1, 0, 1, 1, 1, 1}
Output:
The number of subarrays consisting of 0 only: 5
The number of subarrays consisting of 1 only: 15
 */
public class CountSubarraysWIthAllOnes {
    public int count(int[] arr){
        int len = arr.length, res = 0, pre = 0;
        for(int i = 0; i < len; i++){
            if(arr[i] == 1){
                pre++;
            } else {
                pre = 0;
            }
            res += pre;
        }
        return res;
    }

    public int countII(int[] arr){
        int len = arr.length, res = 0;
        int[] dp = new int[len + 1];
        for(int i = 0; i < len; i++){
            if(arr[i] == 1){
                dp[i + 1] = dp[i] + 1;
            } else {
                dp[i + 1] = 0;
            }
            res += dp[i + 1];
        }
        return res;
    }
}

