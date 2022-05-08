package contest;

import java.util.Map;
import java.util.TreeMap;

/*
You are given a 0-indexed 2D integer array flowers, where flowers[i] = [starti, endi] means the ith flower will be in full bloom from starti to endi (inclusive). You are also given a 0-indexed integer array persons of size n, where persons[i] is the time that the ith person will arrive to see the flowers.

Return an integer array answer of size n, where answer[i] is the number of flowers that are in full bloom when the ith person arrives.



Example 1:


Input: flowers = [[1,6],[3,7],[9,12],[4,13]], persons = [2,3,7,11]
Output: [1,2,2,2]
Explanation: The figure above shows the times when the flowers are in full bloom and when the people arrive.
For each person, we return the number of flowers in full bloom during their arrival.
Example 2:


Input: flowers = [[1,10],[3,3]], persons = [3,3,2]
Output: [2,2,1]
Explanation: The figure above shows the times when the flowers are in full bloom and when the people arrive.
For each person, we return the number of flowers in full bloom during their arrival.


Constraints:

1 <= flowers.length <= 5 * 10^4
flowers[i].length == 2
1 <= starti <= endi <= 10^9
1 <= persons.length <= 5 * 10^4
1 <= persons[i] <= 10^9
 */
public class NumberOfFlowersInFullBloom {
    public int[] fullBloomFlowers(int[][] flowers, int[] persons) {
        int[] res = new int[persons.length];
        TreeMap<Integer, Integer> axis = new TreeMap<>();
        for (int[] f : flowers) {
            int s = f[0];
            int e = f[1];
            axis.put(s, axis.getOrDefault(s, 0) + 1);
            axis.put(e + 1, axis.getOrDefault(e + 1, 0) - 1);
        }
        for (int cur : axis.keySet()) {
            if (cur != axis.firstKey()) {
                int pre = axis.lowerKey(cur);
                axis.put(cur, axis.get(cur) + axis.get(pre));
            }
        }
        for (int i = 0; i < persons.length; i++) {
            int day = persons[i];
            Map.Entry<Integer, Integer> entry = axis.floorEntry(day);
            res[i] = entry != null ? entry.getValue() : 0;
        }
        return res;
    }
}
