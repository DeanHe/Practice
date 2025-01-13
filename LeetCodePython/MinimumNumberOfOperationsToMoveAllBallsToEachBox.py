"""
You have n boxes. You are given a binary string boxes of length n, where boxes[i] is '0' if the ith box is empty, and '1' if it contains one ball.

In one operation, you can move one ball from a box to an adjacent box. Box i is adjacent to box j if abs(i - j) == 1. Note that after doing so, there may be more than one ball in some boxes.

Return an array answer of size n, where answer[i] is the minimum number of operations needed to move all the balls to the ith box.

Each answer[i] is calculated considering the initial state of the boxes.

Example 1:
Input: boxes = "110"
Output: [1,1,3]
Explanation: The answer for each box is as follows:
1) First box: you will have to move one ball from the second box to the first box in one operation.
2) Second box: you will have to move one ball from the first box to the second box in one operation.
3) Third box: you will have to move one ball from the first box to the third box in two operations, and move one ball from the second box to the third box in one operation.

Example 2:
Input: boxes = "001011"
Output: [11,8,5,4,3,4]


Constraints:
n == boxes.length
1 <= n <= 2000
boxes[i] is either '0' or '1'.

hints:
1 If you want to move a ball from box i to box j, you'll need abs(i-j) moves.
2 To move all balls to some box, you can move them one by one.
3 For each box i, iterate on each ball in a box j, and add abs(i-j) to answers[i].

Analysis:
prefix sum, two side pointers
TC: O(N)
"""
from typing import List


class MinimumNumberOfOperationsToMoveAllBallsToEachBox:
    def minOperations(self, boxes: str) -> List[int]:
        sz = len(boxes)
        res = [0] * sz
        pre_sum = balls = 0
        # from left to right moving balls
        for i in range(sz):
            res[i] += pre_sum
            balls += int(boxes[i])
            pre_sum += balls
        # from right to left moving balls
        pre_sum = balls = 0
        for i in range(sz - 1, -1, -1):
            res[i] += pre_sum
            balls += int(boxes[i])
            pre_sum += balls
        return res