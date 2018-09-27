package Greedy;
// https://www.lintcode.com/problem/single-number/description
public class SingleNumber {
	/**
     * @param A: An integer array
     * @return: An integer
     */
    public int singleNumber(int[] A) {
        // write your code here
        int result = 0;
        for(int i : A){
            result = result ^ i;
        }
        return result;
    }
}
