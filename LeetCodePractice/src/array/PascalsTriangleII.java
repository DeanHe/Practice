package array;

import java.util.ArrayList;
import java.util.List;

/*
Given a non-negative index k where k ≤ 33, return the kth index row of the Pascal's triangle.

Note that the row index starts from 0.


In Pascal's triangle, each number is the sum of the two numbers directly above it.

Example:

Input: 3
   1
  1 1
 1 2 1
1 3 3 1
Output: [1,3,3,1]
Follow up:

Could you optimize your algorithm to use only O(k) extra space?
 */
public class PascalsTriangleII {
    public List<Integer> getRow(int rowIndex) {
        List<Integer> res = new ArrayList<>();
        if(rowIndex < 0){
            return res;
        }
        res.add(1);
        for(int i = 0; i < rowIndex; i++){
            for(int j = i; j > 0; j--){
                res.set(j, res.get(j) + res.get(j - 1));
            }
            res.add(1);
        }
        return res;
    }

    Integer[][] mem;
    public List<Integer> getRow2(int rowIndex) {
        List<Integer> res = new ArrayList<>();
        mem = new Integer[rowIndex + 1][rowIndex + 1];
        for(int k = 0; k <= rowIndex; k++){
            res.add(nCk(rowIndex, k));
        }
        return res;
    }

    private Integer nCk(int n, int k) {
        if(k == 0 || n == k){
            return 1;
        }
        if(mem[n][k] != null){
            return mem[n][k];
        }
        return mem[n][k] = nCk(n - 1, k - 1) + nCk(n - 1, k);
    }
}
