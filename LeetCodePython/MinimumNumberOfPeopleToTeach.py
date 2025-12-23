"""
On a social network consisting of m users and some friendships between users, two users can communicate with each other if they know a common language.

You are given an integer n, an array languages, and an array friendships where:

There are n languages numbered 1 through n,
languages[i] is the set of languages the ith user knows, and
friendships[i] = [ui, vi] denotes a friendship between the users ui and vi.
You can choose one language and teach it to some users so that all friends can communicate with each other. Return the minimum number of users you need to teach.

Note that friendships are not transitive, meaning if x is a friend of y and y is a friend of z, this doesn't guarantee that x is a friend of z.


Example 1:
Input: n = 2, languages = [[1],[2],[1,2]], friendships = [[1,2],[1,3],[2,3]]
Output: 1
Explanation: You can either teach user 1 the second language or user 2 the first language.

Example 2:
Input: n = 3, languages = [[2],[1,3],[1,2],[3]], friendships = [[1,4],[1,2],[3,4],[2,3]]
Output: 2
Explanation: Teach the third language to users 1 and 3, yielding two users to teach.

Constraints:
2 <= n <= 500
languages.length == m
1 <= m <= 500
1 <= languages[i].length <= n
1 <= languages[i][j] <= n
1 <= u_i < v_i <= languages.length
1 <= friendships.length <= 500
All tuples (u_i, v_i) are unique
languages[i] contains only unique values

hints:
1 You can just use brute force and find out for each language the number of users you need to teach
2 Note that a user can appear in multiple friendships but you need to teach that user only once

Analysis:
Greedy
TC: O(M * N)
Let m be the number of friendship pairs and n be the number of available languages.
"""
from typing import List


class MinimumNumberOfPeopleToTeach:
    def minimumTeachings(self, n: int, languages: List[List[int]], friendships: List[List[int]]) -> int:
        sz = len(languages)
        people_to_teach = [False] * sz
        for a, b in friendships:
            a -= 1
            b -= 1
            found_common_lang = False
            for lang_a in languages[a]:
                for lang_b in languages[b]:
                    if lang_a == lang_b:
                        found_common_lang = True
                        break
            if not found_common_lang:
                people_to_teach[a] = True
                people_to_teach[b] = True
        lang_cnt = [0] * (n + 1)
        max_lang_cnt = 0
        people_to_teach_cnt = 0
        for i in range(sz):
            if people_to_teach[i]:
                people_to_teach_cnt += 1
                for j in languages[i]:
                    lang_cnt[j] += 1
                    max_lang_cnt = max(max_lang_cnt, lang_cnt[j])
        return people_to_teach_cnt - max_lang_cnt
