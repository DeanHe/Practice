package BitManipulation;
/*
Given a number s in their binary representation. Return the number of steps to reduce it to 1 under the following rules:

        If the current number is even, you have to divide it by 2.

        If the current number is odd, you have to add 1 to it.

        It's guaranteed that you can always reach to one for all testcases.



        Example 1:

        Input: s = "1101"
        Output: 6
        Explanation: "1101" corressponds to number 13 in their decimal representation.
        Step 1) 13 is odd, add 1 and obtain 14.
        Step 2) 14 is even, divide by 2 and obtain 7.
        Step 3) 7 is odd, add 1 and obtain 8.
        Step 4) 8 is even, divide by 2 and obtain 4.
        Step 5) 4 is even, divide by 2 and obtain 2.
        Step 6) 2 is even, divide by 2 and obtain 1.
        Example 2:

        Input: s = "10"
        Output: 1
        Explanation: "10" corressponds to number 2 in their decimal representation.
        Step 1) 2 is even, divide by 2 and obtain 1.
        Example 3:

        Input: s = "1"
        Output: 0


        Constraints:

        1 <= s.length <= 500
        s consists of characters '0' or '1'
        s[0] == '1'

Intuition: division by two is the same as the right shift by one bit (character).
If the bit is 0, we just do the shift - one operation.
If the bit is 1 - we do plus one, and our bit changes to zero. So, we set carry to 1 and shift. Two operations.

Algorithm
We have three phases here:

We haven't encountered any 1. Every char adds one operation.
We encounter our first 1. We set carry to 1 and add two operations.
The rest:
3A. Every 1 needs one operation (carry makes it 0). carry is still 1 due to addition.
3B. Every 0 needs two operations (carry makes it 1). carry is still 1 as we need to add 1 in this case.
Observation: as you can see from 3A and 3B, carry is always 1 after the second phase.
*/
public class NumberOfStepsToReduceaNumberInBinaryRepresentationToOne {
    public int numSteps(String s) {
        char[] arr = s.toCharArray();
        int step = 0, carry = 0, len = arr.length;
        for(int i = len - 1; i > 0; i--){
            if(arr[i] == '1'){
                if(carry == 1){ // odd
                    carry = 1;
                    step += 1;
                } else { // carry = 0, even
                    carry = 1;
                    step += 2;
                }
            } else { // arr[i] == '0'
                if(carry == 1){ // odd
                    carry = 1;
                    step += 2;
                } else { // carry = 0, even
                    carry = 0;
                    step += 1;
                }
            }
        }
        return step + carry;
    }
}
