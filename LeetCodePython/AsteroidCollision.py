"""
We are given an array asteroids of integers representing asteroids in a row.

For each asteroid, the absolute value represents its size, and the sign represents its direction (positive meaning right, negative meaning left). Each asteroid moves at the same speed.

Find out the state of the asteroids after all collisions. If two asteroids meet, the smaller one will explode. If both are the same size, both will explode. Two asteroids moving in the same direction will never meet.

Example 1:
Input: asteroids = [5,10,-5]
Output: [5,10]
Explanation: The 10 and -5 collide resulting in 10. The 5 and 10 never collide.

Example 2:
Input: asteroids = [8,-8]
Output: []
Explanation: The 8 and -8 collide exploding each other.

Example 3:
Input: asteroids = [10,2,-5]
Output: [10]
Explanation: The 2 and -5 collide resulting in -5. The 10 and -5 collide resulting in 10.

Constraints:
2 <= asteroids.length <= 10^4
-1000 <= asteroids[i] <= 1000
asteroids[i] != 0

hints:
1 Say a row of asteroids is stable. What happens when a new asteroid is added on the right?
"""
from typing import List


class AsteroidCollision:
    def asteroidCollision(self, asteroids: List[int]) -> List[int]:
        stack = []
        for a in asteroids:
            if not stack or a > 0:
                stack.append(a)
            else:
                while True:
                    pre = stack[-1]
                    if pre < 0:
                        stack.append(a)
                        break
                    else:
                        if pre == -a:
                            stack.pop()
                            break
                        elif pre > -a:
                            break
                        else:
                            stack.pop()
                            if not stack:
                                stack.append(a)
                                break
        return stack