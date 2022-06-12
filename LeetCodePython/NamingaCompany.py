"""
You are given an array of strings ideas that represents a list of names to be used in the process of naming a company. The process of naming a company is as follows:

Choose 2 distinct names from ideas, call them ideaA and ideaB.
Swap the first letters of ideaA and ideaB with each other.
If both of the new names are not found in the original ideas, then the name ideaA ideaB (the concatenation of ideaA and ideaB, separated by a space) is a valid company name.
Otherwise, it is not a valid name.
Return the number of distinct valid names for the company.

Example 1:
Input: ideas = ["coffee","donuts","time","toffee"]
Output: 6
Explanation: The following selections are valid:
- ("coffee", "donuts"): The company name created is "doffee conuts".
- ("donuts", "coffee"): The company name created is "conuts doffee".
- ("donuts", "time"): The company name created is "tonuts dime".
- ("donuts", "toffee"): The company name created is "tonuts doffee".
- ("time", "donuts"): The company name created is "dime tonuts".
- ("toffee", "donuts"): The company name created is "doffee tonuts".
Therefore, there are a total of 6 distinct company names.

The following are some examples of invalid selections:
- ("coffee", "time"): The name "toffee" formed after swapping already exists in the original array.
- ("time", "toffee"): Both names are still the same after swapping and exist in the original array.
- ("coffee", "toffee"): Both names formed after swapping already exist in the original array.

Example 2:
Input: ideas = ["lack","back"]
Output: 0
Explanation: There are no valid selections. Therefore, 0 is returned.


Constraints:
2 <= ideas.length <= 5 * 104
1 <= ideas[i].length <= 10
ideas[i] consists of lowercase English letters.
All the strings in ideas are unique.

hint:
1 How can we divide the ideas into groups to make it easier to find valid pairs?
2 Group ideas that share the same suffix (all characters except the first) together and notice that a pair of ideas from the same group is invalid. What about pairs of ideas from different groups?
3 The first letter of the idea in the first group must not be the first letter of an idea in the second group and vice versa.
4 We can efficiently count the valid pairings for an idea if we already know how many ideas starting with a letter x are within a group that does not contain any ideas with starting letter y for all letters x and y.
"""
from typing import List


class NamingaCompany:
    def distinctNames(self, ideas: List[str]) -> int:
        res = 0
        ls = [set() for _ in range(26)]
        for w in ideas:
            ls[ord(w[0]) - ord('a')].add(w[1:])
        for i in range(25):
            for j in range(i + 1, 26):
                overlap = len(ls[i] & ls[j])
                res += 2 * (len(ls[i]) - overlap) * (len(ls[j]) - overlap)
        return res

