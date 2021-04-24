package math;

/*
The set [1,2,3,...,n] contains a total of n! unique permutations.

By listing and labeling all of the permutations in order, we get the following sequence for n = 3:

"123"
"132"
"213"
"231"
"312"
"321"
Given n and k, return the kth permutation sequence.

Note:

Given n will be between 1 and 9 inclusive.
Given k will be between 1 and n! inclusive.
Example 1:

Input: n = 3, k = 3
Output: "213"
Example 2:

Input: n = 4, k = 9
Output: "2314"

analysis:
time complexity O(n)
https://leetcode.com/problems/permutation-sequence/discuss/22507/
 */

import java.util.ArrayList;
import java.util.List;

public class PermutationSequence {
    public String getPermutation(int n, int k) {
        StringBuilder sb = new StringBuilder();
        List<Integer> number = new ArrayList<>();
        int[] factorial = new int[n + 1]; // factorial[] = {1, 1, 2, 6, 24, ... n!}
        factorial[0] = 1;
        for(int i = 1; i <= n; i++){
            factorial[i] = factorial[i - 1] * i;
        }
        for(int i = 1; i <= n; i++){
            number.add(i);
        }
        k--;
        for(int i = 1; i <= n; i++){
            int idx = k / factorial[n - i];
            sb.append(number.get(idx));
            number.remove(idx);
            k -= idx * factorial[n - i];
        }
        return sb.toString();
    }
}
