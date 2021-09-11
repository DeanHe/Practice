package contest;

import java.util.Arrays;

/*
There are n rooms you need to visit, labeled from 0 to n - 1. Each day is labeled, starting from 0. You will go in and visit one room a day.

Initially on day 0, you visit room 0. The order you visit the rooms for the coming days is determined by the following rules and a given 0-indexed array nextVisit of length n:

Assuming that on a day, you visit room i,
if you have been in room i an odd number of times (including the current visit), on the next day you will visit the room specified by nextVisit[i] where 0 <= nextVisit[i] <= i;
if you have been in room i an even number of times (including the current visit), on the next day you will visit room (i + 1) mod n.
Return the label of the first day where you have been in all the rooms. It can be shown that such a day exists. Since the answer may be very large, return it modulo 109 + 7.



Example 1:

Input: nextVisit = [0,0]
Output: 2
Explanation:
- On day 0, you visit room 0. The total times you have been in room 0 is 1, which is odd.
  On the next day you will visit room nextVisit[0] = 0
- On day 1, you visit room 0, The total times you have been in room 0 is 2, which is even.
  On the next day you will visit room (0 + 1) mod 2 = 1
- On day 2, you visit room 1. This is the first day where you have been in all the rooms.
Example 2:

Input: nextVisit = [0,0,2]
Output: 6
Explanation:
Your room visiting order for each day is: [0,0,1,0,0,1,2,...].
Day 6 is the first day where you have been in all the rooms.
Example 3:

Input: nextVisit = [0,1,2,0]
Output: 6
Explanation:
Your room visiting order for each day is: [0,0,1,1,2,2,3,...].
Day 6 is the first day where you have been in all the rooms.


Constraints:

n == nextVisit.length
2 <= n <= 10^5
0 <= nextVisit[i] <= i

analysis:
https://leetcode.com/problems/first-day-where-you-have-been-in-all-the-rooms/discuss/1445203/Very-concise-C%2B%2B-DP-solution-O(n)-with-explanation
odd[i] means the number of days to first arrive room i in odd time
even[i] means the number of days to first arrive room i in even time
d means days needed to move from room nextVisit[i] to i

TC O(N)
 */
public class FirstDayWhereYouHaveBeenInAllTheRooms {
    int MOD = (int)(1e9 + 7);
    public int firstDayBeenInAllRooms(int[] nextVisit) {
        int rooms = nextVisit.length;
        long[] odd = new long[rooms];
        long[] even = new long[rooms];
        odd[0] = 0;
        even[0] = 1;
        for(int i = 1; i < rooms; i++){
            odd[i] = (even[i - 1] + 1) % MOD;
            long d = (odd[i] - odd[nextVisit[i]] + MOD) % MOD;
            even[i] = (odd[i] + 1 + d) % MOD;
        }
        return (int)odd[rooms - 1];
    }
}

