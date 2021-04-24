package math;

// https://www.lintcode.com/problem/dot-product/description
public class DotProduct {
	/**
     * @param A: an array
     * @param B: an array
     * @return: dot product of two array
     */
    public int dotProduct(int[] A, int[] B) {
        // Write your code here
        if(A == null || A.length == 0 || B == null || B.length == 0 || A.length != B.length){
            return -1;
        }
        int len = A.length;
        int res = 0;
        for(int i = 0; i < len; i++){
            res += A[i] * B[i];
        }
        return res;
    }
}
