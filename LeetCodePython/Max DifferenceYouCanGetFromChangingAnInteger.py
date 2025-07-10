"""
You are given an integer num. You will apply the following steps to num two separate times:

Pick a digit x (0 <= x <= 9).
Pick another digit y (0 <= y <= 9). Note y can be equal to x.
Replace all the occurrences of x in the decimal representation of num by y.
Let a and b be the two results from applying the operation to num independently.

Return the max difference between a and b.

Note that neither a nor b may have any leading zeros, and must not be 0.

Example 1:
Input: num = 555
Output: 888
Explanation: The first time pick x = 5 and y = 9 and store the new integer in a.
The second time pick x = 5 and y = 1 and store the new integer in b.
We have now a = 999 and b = 111 and max difference = 888

Example 2:
Input: num = 9
Output: 8
Explanation: The first time pick x = 9 and y = 9 and store the new integer in a.
The second time pick x = 9 and y = 1 and store the new integer in b.
We have now a = 9 and b = 1 and max difference = 8

Constraints:
1 <= num <= 10^8

hints:
1 We need to get the max and min value after changing num and the answer is max - min.
2 Use brute force, try all possible changes and keep the minimum and maximum values.

Analysis:
Greedy
TC: O(logN)
"""

class MaxDifferenceYouCanGetFromChangingAnInteger:
    def maxDiff(self, num: int) -> int:
        min_num = str(num)
        max_num = str(num)
        for d in max_num:
            if d != '9':
                max_num = max_num.replace(d, '9')
                break
        for i, d in enumerate(min_num):
            if i == 0:
                if d != '1':
                    min_num = min_num.replace(d, '1')
                    break
            else:
                # find the highest digit not equals to 0, replace it with 0
                if d != '0' and d != min_num[0]:
                    min_num = min_num.replace(d, '0')
                    break
        return int(max_num) - int(min_num)