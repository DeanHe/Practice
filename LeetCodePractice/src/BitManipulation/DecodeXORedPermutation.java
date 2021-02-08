package BitManipulation;
/*
There is an integer array perm that is a permutation of the first n positive integers, where n is always odd.

It was encoded into another integer array encoded of length n - 1, such that encoded[i] = perm[i] XOR perm[i + 1]. For example, if perm = [1,3,2], then encoded = [2,1].

Given the encoded array, return the original array perm. It is guaranteed that the answer exists and is unique.



Example 1:

Input: encoded = [3,1]
Output: [1,2,3]
Explanation: If perm = [1,2,3], then encoded = [1 XOR 2,2 XOR 3] = [3,1]
Example 2:

Input: encoded = [6,5,4,6]
Output: [2,4,1,5,3]


Constraints:

3 <= n < 10^5
n is odd.
encoded.length == n - 1

analysis:
preSum = 1 ^...^ n
a1 = (a2 ^ a3) ^ (a4 ^ a5) ^...^ (an-1, an) ^ preSum
a1 = encoded[1] ^ encoded[3] ^ ... encoded[n - 1] ^ preSum;
after find out a1, then rest can be inducted
 */
public class DecodeXORedPermutation {
    public int[] decode(int[] encoded) {
        int n = encoded.length + 1;
        int[] res = new int[n];
        int preSum = 0;
        for (int i = 1; i <= n; i++) {
            preSum ^= i;
        }
        res[0] = preSum;
        for (int i = 1; i < encoded.length; i += 2) {
            res[0] ^= encoded[i];
        }
        for (int i = 0; i < encoded.length; i++) {
            res[i + 1] = encoded[i] ^ res[i];
        }
        return res;
    }
}
