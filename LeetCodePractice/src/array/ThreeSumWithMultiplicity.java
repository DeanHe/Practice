package array;

/*
Given an integer array arr, and an integer target, return the number of tuples i, j, k such that i < j < k and arr[i] + arr[j] + arr[k] == target.

As the answer can be very large, return it modulo 109 + 7.



Example 1:

Input: arr = [1,1,2,2,3,3,4,4,5,5], target = 8
Output: 20
Explanation:
Enumerating by the values (arr[i], arr[j], arr[k]):
(1, 2, 5) occurs 8 times;
(1, 3, 4) occurs 8 times;
(2, 2, 4) occurs 2 times;
(2, 3, 3) occurs 2 times.
Example 2:

Input: arr = [1,1,2,2,2,2], target = 5
Output: 12
Explanation:
arr[i] = 1, arr[j] = arr[k] = 2 occurs 12 times:
We choose one 1 from [1,1] in 2 ways,
and two 2s from [2,2,2,2] in 6 ways.


Constraints:

3 <= arr.length <= 3000
0 <= arr[i] <= 100
0 <= target <= 300

TC O(N ^ 2)
 */
public class ThreeSumWithMultiplicity {
    int MOD = (int)(1e9 + 7);
    public int threeSumMulti(int[] arr, int target) {
        long res = 0;
        long[] cnt = new long[101];
        for(int n : arr){
            cnt[n]++;
        }
        // make sure i <= j <= k
        for(int i = 0; i <= 100; i++){
            for(int j = i; j <= 100; j++){
                int k = target - i - j;
                if(k >= 0 && k <= 100){
                    if(i == j && j == k){
                        // C(N, 3)
                        res += cnt[i] * (cnt[i] - 1) * (cnt[i] - 2) / 6;
                    } else if(i == j && j != k){
                        // C(N, 2)
                        res += cnt[i] * (cnt[i] - 1) / 2 * cnt[k];
                    } else if(j < k){
                        res += cnt[i] * cnt[j] * cnt[k];
                    }
                }
            }
        }
        return (int)(res % MOD);
    }
}
