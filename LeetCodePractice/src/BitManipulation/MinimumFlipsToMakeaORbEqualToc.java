package BitManipulation;
/*
Given 3 positives numbers a, b and c. Return the minimum flips required in some bits of a and b to make ( a OR b == c ). (bitwise OR operation).
        Flip operation consists of change any single bit 1 to 0 or change the bit 0 to 1 in their binary representation.

        Example 1:



        Input: a = 2, b = 6, c = 5
        Output: 3
        Explanation: After flips a = 1 , b = 4 , c = 5 such that (a OR b == c)
        Example 2:

        Input: a = 4, b = 2, c = 7
        Output: 1
        Example 3:

        Input: a = 1, b = 2, c = 3
        Output: 0


        Constraints:

        1 <= a <= 10^9
        1 <= b <= 10^9
        1 <= c <= 10^9
*/
public class MinimumFlipsToMakeaORbEqualToc {
    public int minFlips(int a, int b, int c) {
        int flip = 0;
        int bit_a  = 0, bit_b = 0, bit_c = 0;
        for(int i = 0; i < 32; i++){
            if(((a >> i) & 1) == 1){
                bit_a = 1;
            }
            if(((b >> i) & 1) == 1){
                bit_b = 1;
            }
            if(((c >> i) & 1) == 1){
                bit_c = 1;
            }
            if(bit_c == 0){
                flip += bit_a + bit_b;
            } else {
                //bit_c == 1
                if(bit_a == 0 && bit_b == 0){
                    flip++;
                }
            }
            bit_a = 0;
            bit_b = 0;
            bit_c = 0;
        }
        return flip;
    }
}
