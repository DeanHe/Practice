package BitManipulation;
/*
        Given a range [m, n] where 0 <= m <= n <= 2147483647, return the bitwise AND of all numbers in this range, inclusive.

        Example 1:

        Input: [5,7]
        Output: 4
        Example 2:

        Input: [0,1]
        Output: 0

        solution:
        In one word, this problem is asking us to find the common prefix of m and n 's binary code.
*/
public class BitwiseANDofNumbersRange {
    public int rangeBitwiseAnd(int m, int n) {
        if(m == 0){
            return 0;
        }
        int rightShift = 0;
        while(m != n){
            m >>= 1;
            n >>= 1;
            rightShift++;
        }
        return m << rightShift; // compensate back
    }
}
