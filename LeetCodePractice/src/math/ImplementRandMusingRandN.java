package math;
/*
        Given a function rand7 which generates a uniform random integer in the range 1 to 7, write a function rand10 which generates a uniform random integer in the range 1 to 10.
        Do NOT use system's math.random().

        Example 1:
        Input: 1
        Output: [7]

        Example 2:
        Input: 2
        Output: [8,4]

        Example 3:
        Input: 3
        Output: [8,1,10]


        Note:
        rand7 is predefined.
        Each testcase has one argument: n, the number of times that rand10 is called.

        Follow up:

        What is the expected value for the number of calls to rand7() function?
        Could you minimize the number of calls to rand7()?

        Analysis:

        https://www.youtube.com/watch?v=Wyauxe92JJA
        https://leetcode.com/problems/implement-rand10-using-rand7/discuss/150301/Three-line-Java-solution-the-idea-can-be-generalized-to-%22Implement-RandM()-Using-RandN()%22

        Time complexity:
        The total number of iterations follows geometric distribution. For each iteration in the while loop,
        the probability of exiting the loop is p = 40/49. So the average time complexity T(n) = O(1/p) = O(49/40) = O(1).

        Correctness
Note that rand49() generates a uniform random integer in [1, 49], so any number in this range has the same probability to be generated. Suppose k is an integer in range [1, 40], P(rand49() = k) = 1/49.

   P(result = k)
= P(rand49() = k in the 1st iteration) +
   P(rand49() > 40 in the 1st iteration) * P(rand49() = k in the 2nd iteration) +
   P(rand49() > 40 in the 1st iteration) * P(rand49() > 40 in the 2nd iteration) * P(rand49() = k in the 3rd iteration) +
   P(rand49() > 40 in the 1st iteration) * P(rand49() > 40 in the 2nd iteration) * P(rand49() > 40 in the 3rd iteration) * P(rand49() = k in the 4th iteration) +
   ...
= (1/49) + (9/49) * (1/49) + (9/49)^2 * (1/49) + (9/49)^3 * (1/49) + ...
= (1/49) * [1 + (9/49) + (9/49)^2 + (9/49)^3 + ... ]
= (1/49) * [1/(1-9/49)]
= (1/49) * (49/40)
= 1/40

Generalization
Implement randM() using randN() when M > N:
Step 1: Use randN() to generate randX(), where X >= M. In this problem, I use 7 * (rand7() - 1) + (rand7() - 1) to generate rand49() - 1.
Step 2: Use randX() to generate randM(). In this problem, I use rand49() to generate rand40() then generate rand10.

Note: N^b * (randN() - 1) + N^(b - 1) * (randN() - 1) + N^(b - 2) * (randN() - 1) + ... + N^0 * (randN() - 1) generates randX() - 1, where X = N^(b + 1).
*/

import java.util.Random;

/**
 * The rand7() API is already defined in the parent class SolBase.
 * public int rand7();
 * @return a random integer in the range 1 to 7
 */
public class ImplementRandMusingRandN {
    public int rand10() {
        int roll = 40;
        while(roll >= 40){
            roll = 7 * (rand7() - 1) + (rand7() - 1);
        }
        return roll % 10 + 1;
    }

    //dummy
    private int rand7(){
        Random rand = new Random();
        return 1 + rand.nextInt(7);
    }
}
