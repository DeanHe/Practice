"""
In a project, you have a list of required skills req_skills, and a list of people. The ith person people[i] contains a list of skills that the person has.

Consider a sufficient team: a set of people such that for every required skill in req_skills, there is at least one person in the team who has that skill. We can represent these teams by the index of each person.

For example, team = [0, 1, 3] represents the people with skills people[0], people[1], and people[3].
Return any sufficient team of the smallest possible size, represented by the index of each person. You may return the answer in any order.

It is guaranteed an answer exists.

Example 1:
Input: req_skills = ["java","nodejs","reactjs"], people = [["java"],["nodejs"],["nodejs","reactjs"]]
Output: [0,2]

Example 2:
Input: req_skills = ["algorithms","math","java","reactjs","csharp","aws"], people = [["algorithms","math","java"],["algorithms","math","reactjs"],["java","csharp","aws"],["reactjs","csharp"],["csharp","math"],["aws","java"]]
Output: [1,2]

Constraints:
1 <= req_skills.length <= 16
1 <= req_skills[i].length <= 16
req_skills[i] consists of lowercase English letters.
All the strings of req_skills are unique.
1 <= people.length <= 60
0 <= people[i].length <= 16
1 <= people[i][j].length <= 16
people[i][j] consists of lowercase English letters.
All the strings of people[i] are unique.
Every skill in people[i] is a skill in req_skills.
It is guaranteed a sufficient team exists.

hints:
1 Do a bitmask DP.
2 For each person, for each set of skills, we can update our understanding of a minimum set of people needed to perform this set of skills.

analysis:
TC: O(n * 2^m)
"""
from typing import List


class SmallestSufficientTeam:
    def smallestSufficientTeam(self, req_skills: List[str], people: List[List[str]]) -> List[int]:
        res = []
        skill_id = {}
        for i, skill in enumerate(req_skills):
            skill_id[skill] = i
        person_skills_mask = [0] * len(people)
        for i in range(len(people)):
            for skill in people[i]:
                person_skills_mask[i] |= 1 << skill_id[skill]
        # dp[i] means minimal people_mask required to complete skills_mask
        dp = [(1 << len(people)) - 1] * (1 << len(req_skills))
        dp[0] = 0

        for skills_mask in range(1, 1 << len(req_skills)):
            for i in range(len(people)):
                pre_skills_mask = skills_mask & ~person_skills_mask[i]
                if pre_skills_mask != skills_mask:
                    people_mask = dp[pre_skills_mask] | (1 << i)
                    if people_mask.bit_count() < dp[skills_mask].bit_count():
                        dp[skills_mask] = people_mask

        goal_mask = dp[(1 << len(req_skills)) - 1]
        for i in range(len(people)):
            if (goal_mask >> i) & 1:
                res.append(i)
        return res