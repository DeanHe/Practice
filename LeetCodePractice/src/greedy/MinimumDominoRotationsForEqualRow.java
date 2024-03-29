package greedy;
/*
In a row of dominoes, A[i] and B[i] represent the top and bottom halves of the ith domino.  (A domino is a tile with two numbers from 1 to 6 - one on each half of the tile.)

We may rotate the ith domino, so that A[i] and B[i] swap values.

Return the minimum number of rotations so that all the values in A are the same, or all the values in B are the same.

If it cannot be done, return -1.



Example 1:


Input: A = [2,1,2,4,2,2], B = [5,2,6,2,3,2]
Output: 2
Explanation:
The first figure represents the dominoes as given by A and B: before we do any rotations.
If we rotate the second and fourth dominoes, we can make every value in the top row equal to 2, as indicated by the second figure.
Example 2:

Input: A = [3,5,1,2,3], B = [3,6,3,3,4]
Output: -1
Explanation:
In this case, it is not possible to rotate the dominoes to make one row of values equal.


Constraints:

2 <= A.length == B.length <= 2 * 10^4
1 <= A[i], B[i] <= 6

analysis:
Count the frequency of each number in A and B, respectively;
Count the frequency of A[i] if A[i] == B[i];
If countA[i] + countB[i] - same[i] == A.length, we find a solution; otherwise, return -1;
min(countA[i], countB[i]) - same[i] is the answer. countA[i] + countB[i] - same[i] is like finding the union of two set A and set B <=> A + B - (A & B)

TC: O(A + B)
SC: O(1)
 */
public class MinimumDominoRotationsForEqualRow {
    public int minDominoRotations(int[] A, int[] B) {
        int len = A.length;
        int[] countA = new int[7];
        int[] countB = new int[7];
        int[] same = new int[7];
        for(int i = 0; i < len; i++){
            countA[A[i]]++;
            countB[B[i]]++;
            if(A[i] == B[i]){
                same[A[i]]++;
            }
        }
        for(int i = 1; i < 7; i++){
            if(countA[i] + countB[i] -same[i] == len){
                return Math.min(countA[i], countB[i]) - same[i];
            }
        }
        return -1;
    }
}
