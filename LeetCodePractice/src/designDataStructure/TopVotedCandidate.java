package designDataStructure;

import java.util.HashMap;
import java.util.Map;

/*
911. Online Election
In an election, the i-th vote was cast for persons[i] at time times[i].

Now, we would like to implement the following query function: TopVotedCandidate.q(int t) will return the number of the person that was leading the election at time t.

Votes cast at time t will count towards our query.  In the case of a tie, the most recent vote (among tied candidates) wins.



Example 1:

Input: ["TopVotedCandidate","q","q","q","q","q","q"], [[[0,1,1,0,0,1,0],[0,5,10,15,20,25,30]],[3],[12],[25],[15],[24],[8]]
Output: [null,0,1,1,0,0,1]
Explanation:
At time 3, the votes are [0], and 0 is leading.
At time 12, the votes are [0,1,1], and 1 is leading.
At time 25, the votes are [0,1,1,0,0,1], and 1 is leading (as ties go to the most recent vote.)
This continues for 3 more queries at time 15, 24, and 8.


Note:

1 <= persons.length = times.length <= 5000
0 <= persons[i] <= persons.length
times is a strictly increasing array with all elements in [0, 10^9].
TopVotedCandidate.q is called at most 10000 times per test case.
TopVotedCandidate.q(int t) is always called with t >= times[0].

analysis:
hashmap, binary search
 */
public class TopVotedCandidate {
    Map<Integer, Integer> leaderMap = new HashMap<>();
    int len, leader;
    int[] times;

    public TopVotedCandidate(int[] persons, int[] times) {
        this.times = times;
        len = persons.length;
        leader = -1;
        Map<Integer, Integer> cntMap = new HashMap<>();
        for (int i = 0; i < len; i++) {
            cntMap.put(persons[i], cntMap.getOrDefault(persons[i], 0) + 1);
            if (i == 0 || cntMap.get(leader) <= cntMap.get(persons[i])) {
                leader = persons[i];
            }
            leaderMap.put(times[i], leader);
        }

    }

    public int q(int t) {
        int s = 0, e = len - 1;
        while (s + 1 < e) {
            int mid = s + (e - s) / 2;
            if (times[mid] == t) {
                return leaderMap.get(times[mid]);
            } else if (times[mid] < t) {
                s = mid;
            } else {
                e = mid;
            }
        }
        if (t < times[e]) {
            return leaderMap.get(times[s]);
        }
        return leaderMap.get(times[e]);
    }
}

/**
 * Your TopVotedCandidate object will be instantiated and called as such:
 * TopVotedCandidate obj = new TopVotedCandidate(persons, times);
 * int param_1 = obj.q(t);
 */

