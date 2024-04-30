"""
In the video game Fallout 4, the quest "Road to Freedom" requires players to reach a metal dial called the "Freedom Trail Ring" and use the dial to spell a specific keyword to open the door.

Given a string ring that represents the code engraved on the outer ring and another string key that represents the keyword that needs to be spelled, return the minimum number of steps to spell all the characters in the keyword.

Initially, the first character of the ring is aligned at the "12:00" direction. You should spell all the characters in key one by one by rotating ring clockwise or anticlockwise to make each character of the string key aligned at the "12:00" direction and then by pressing the center button.

At the stage of rotating the ring to spell the key character key[i]:

You can rotate the ring clockwise or anticlockwise by one place, which counts as one step. The final purpose of the rotation is to align one of ring's characters at the "12:00" direction, where this character must equal key[i].
If the character key[i] has been aligned at the "12:00" direction, press the center button to spell, which also counts as one step. After the pressing, you could begin to spell the next character in the key (next stage). Otherwise, you have finished all the spelling.

Example 1:
Input: ring = "godding", key = "gd"
Output: 4
Explanation:
For the first key character 'g', since it is already in place, we just need 1 step to spell this character.
For the second key character 'd', we need to rotate the ring "godding" anticlockwise by two steps to make it become "ddinggo".
Also, we need 1 more step for spelling.
So the final output is 4.

Example 2:
Input: ring = "godding", key = "godding"
Output: 13

Constraints:
1 <= ring.length, key.length <= 100
ring and key consist of only lower case English letters.
It is guaranteed that key could always be spelled by rotating ring.

analysis:
DP
"""
import math


class FreedomTrail:
    def findRotateSteps(self, ring: str, key: str) -> int:
        # O(key_len * N * N). N is the most occurrence of the same character on the ring.
        def dist(i, j):
            return min(abs(i - j), len(ring) - abs(i - j))

        idx = {}
        for i, c in enumerate(ring):
            if c in idx:
                idx[c].append(i)
            else:
                idx[c] = [i]

        state = {0: 0} # position of ring: min steps
        for c in key:
            next_state = {}
            for i in idx[c]:
                next_state[i] = math.inf
                for j in state:
                    next_state[i] = min(next_state[i], state[j] + dist(j, i))
            state = next_state
        return min(state.values()) + len(key)

    def findRotateStepsII(self, ring: str, key: str) -> int:
        # TC: O(key_len * ring_len * ring_len)
        ring_len = len(ring)
        key_len = len(key)
        dp = [[0] * (ring_len) for _ in range(key_len + 1)]
        for i in range(key_len - 1, -1, -1):
            for cur in range(ring_len):
                dp[i][cur] = math.inf
                for nxt in range(ring_len):
                    if ring[nxt] == key[i]:
                        diff = abs(cur - nxt)
                        step = min(diff, ring_len - diff)
                        dp[i][cur] = min(dp[i][cur], step + dp[i + 1][nxt])
        return dp[0][0] + key_len