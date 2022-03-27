"""
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
"""
from collections import Counter
from typing import List


class MinimumDominoRotationsForEqualRow:

    def minDominoRotations(self, tops: List[int], bottoms: List[int]) -> int:
        same, count_tops, count_bottoms = Counter(), Counter(tops), Counter(bottoms)
        for a, b in zip(tops, bottoms):
            if a == b:
                same[a] += 1
        for i in range(1, 7):
            if count_tops[i] + count_bottoms[i] - same[i] == len(tops):
                return min(count_tops[i], count_bottoms[i]) - same[i]
        return -1

