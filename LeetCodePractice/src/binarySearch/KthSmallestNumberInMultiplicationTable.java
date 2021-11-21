package binarySearch;
/*
Nearly everyone has used the Multiplication Table. The multiplication table of size m x n is an integer matrix mat where mat[i][j] == i * j (1-indexed).
Given three integers m, n, and k, return the kth smallest element in the m x n multiplication table.


Example 1:
Input: m = 3, n = 3, k = 5
Output: 3
Explanation: The 5th smallest number is 3.

Example 2:
Input: m = 2, n = 3, k = 6
Output: 6
Explanation: The 6th smallest number is 6.


Constraints:

1 <= m, n <= 3 * 10^4
1 <= k <= m * n

analysis:
The count function is to find how many numbers in the table are less than or equal to value v. Since it is a multiplication table, and each number in the table is r*c,
we can find the amount of numbers row by row (or column by column).

For the first row, r=1, the maximum possible c is v/1=v, or n. Because c starts from 1, we can only have at most Math.min(v/1,n) values, which are less than or equal to v.
For the second row, r=2, the maximum c is v/2, or n. Similarly, we can only have at most Math.min(v/2,n) values.
For the i-th row, r=i, the maximum c is v/i, or n.
 */
public class KthSmallestNumberInMultiplicationTable {
    public int findKthNumber(int m, int n, int k) {
        int s = 1, e = m * n + 1;
        while(s + 1 < e){
            int mid = s + (e - s) / 2;
            if(enough(mid, m, n, k)){
                e = mid;
            } else {
                s = mid;
            }
        }
        if(enough(s, m, n, k)){
            return s;
        }
        return e;
    }

    private boolean enough(int t, int m, int n, int k) {
        int cnt = 0;
        for(int r = 1; r <= m; r++){
            int tmp = Math.min(t / r, n);
            cnt += tmp;
        }
        return cnt >= k;
    }
}
