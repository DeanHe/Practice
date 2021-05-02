package sweepLine.Intervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
Given the availability time slots arrays slots1 and slots2 of two people and a meeting duration duration, return the earliest time slot that works for both of them and is of duration duration.

If there is no common time slot that satisfies the requirements, return an empty array.

The format of a time slot is an array of two elements [start, end] representing an inclusive time range from start to end.

It is guaranteed that no two availability slots of the same person intersect with each other. That is, for any two time slots [start1, end1] and [start2, end2] of the same person, either start1 > end2 or start2 > end1.



Example 1:

Input: slots1 = [[10,50],[60,120],[140,210]], slots2 = [[0,15],[60,70]], duration = 8
Output: [60,68]
Example 2:

Input: slots1 = [[10,50],[60,120],[140,210]], slots2 = [[0,15],[60,70]], duration = 12
Output: []


Constraints:

1 <= slots1.length, slots2.length <= 10^4
slots1[i].length, slots2[i].length == 2
slots1[i][0] < slots1[i][1]
slots2[i][0] < slots2[i][1]
0 <= slots1[i][j], slots2[i][j] <= 10^9
1 <= duration <= 10^6

analysis:
sort
 */
public class MeetingScheduler {
    public List<Integer> minAvailableDuration(int[][] slots1, int[][] slots2, int duration) {
        List<Integer> res = new ArrayList<>();
        int len1 = slots1.length, len2 = slots2.length;
        int i1 = 0, i2 = 0, s1 = 0, s2 = 0, e1 = 0, e2 = 0;
        Arrays.sort(slots1, (a, b) -> a[0] - b[0]);
        Arrays.sort(slots2, (a, b) -> a[0] - b[0]);
        while (i1 < len1 && i2 < len2){
            s1 = slots1[i1][0];
            s2 = slots1[i1][1];
            e1 = slots2[i2][0];
            e2 = slots2[i2][1];
            if(e1 - s1 < duration){
                i1++;
            } else if(e2 - s2 < duration){
                i2++;
            } else {
                int maxStart =Math.max(s1, s2);
                int minEnd = Math.min(e1, e2);
                if(minEnd - maxStart >= duration){
                    res.add(maxStart);
                    res.add(maxStart + duration);
                    return res;
                } else {
                    if(e1 < e2){
                        i1++;
                    } else {
                        i2++;
                    }
                }
            }
        }
        return res;
    }
}

