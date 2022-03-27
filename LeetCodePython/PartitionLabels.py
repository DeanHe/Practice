"""
A string S of lowercase letters is given. We want to partition this string into as many parts as possible so that each letter appears in at most one part,
and return a list of integers representing the size of these parts.

Example 1:
	Input:  S = "ababcbacadefegdehijhklij"
	Output:  [9,7,8]

	Explanation:
	The partitions are "ababcbaca", "defegde", "hijhklij".
	A partition like "ababcbacadefegde", "hijhklij" is incorrect, because it splits S into less parts.

Example 2:
	Input: S = "abcab"
	Output:  [5]

	Explanation:
	We can not split it.

Notice
1.S will have length in range [1, 500].
2.S will consist of lowercase letters ('a' to 'z') only.

tag: greedy
find the last index of each letter in S first.
iterate letter from start to last[start], and expand the partition to last[cur]
"""

from typing import List


class PartitionLabels:
    def partitionLabels(self, s: str) -> List[int]:
        res, last, start, end = [], {}, 0, 0
        for i, c in enumerate(s):
            last[c] = i
        for i, c in enumerate(s):
            end = max(end, last[c])
            if i == end:
                res.append(end - start + 1)
                start = end + 1
        return res
