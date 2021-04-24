package math;
/*Given two Sparse Matrix A and B, return the result of AB.
You may assume that A's column number is equal to B's row number.

Example
Example1
Input: 
[[1,0,0],[-1,0,3]]
[[7,0,0],[0,0,0],[0,0,1]]
Output:
[[7,0,0],[-7,0,3]]
Explanation:
A = [
  [ 1, 0, 0],
  [-1, 0, 3]
]

B = [
  [ 7, 0, 0 ],
  [ 0, 0, 0 ],
  [ 0, 0, 1 ]
]


     |  1 0 0 |   | 7 0 0 |   |  7 0 0 |
AB = | -1 0 3 | x | 0 0 0 | = | -7 0 3 |
                  | 0 0 1 |
Example2
Input:
[[1,0],[0,1]]
[[0,1],[1,0]]
Output:
[[0,1],[1,0]]
*/
public class SparseMatrixMultiplication {
	/**
     * @param A: a sparse matrix
     * @param B: a sparse matrix
     * @return: the result of A * B
     */
    public int[][] multiply(int[][] A, int[][] B) {
        // write your code here
    	int A_rows = A.length;
    	int A_cols = A[0].length;
    	//A_cols == B_rows
    	int B_cols = B[0].length;
    	int[][] res = new int[A_rows][B_cols];
    	for(int r = 0; r < A_rows; r++){
    		for(int k = 0; k < A_cols; k++){
				if(A[r][k] == 0){
					continue;
				}
				for(int c = 0; c < B_cols; c++){
					res[r][c] += A[r][k] * B[k][c];   
	    		}
			}
    	}
    	return res;
    }
}
