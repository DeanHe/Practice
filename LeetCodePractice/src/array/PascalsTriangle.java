package array;

import java.util.ArrayList;
import java.util.List;

/*
Given an integer numRows, return the first numRows of Pascal's triangle.

In Pascal's triangle, each number is the sum of the two numbers directly above it as shown:




Example 1:

Input: numRows = 5
Output: [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]
Example 2:

Input: numRows = 1
Output: [[1]]


Constraints:

1 <= numRows <= 30
 */
public class PascalsTriangle {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> ls = new ArrayList<>();
        for(int r = 0; r < numRows; r++){
            for(int i = r - 1; i >= 1; i--){
                ls.set(i, ls.get(i) + ls.get(i - 1));
            }
            ls.add(1);
            res.add(new ArrayList<>(ls));
        }
        return res;
    }
}

