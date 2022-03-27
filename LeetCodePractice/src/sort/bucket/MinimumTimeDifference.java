package sort.bucket;

import java.util.List;

/*
Given a list of 24-hour clock time points in "HH:MM" format, return the minimum minutes difference between any two time-points in the list.


Example 1:
Input: timePoints = ["23:59","00:00"]
Output: 1

Example 2:
Input: timePoints = ["00:00","23:59","00:00"]
Output: 0

Constraints:
2 <= timePoints.length <= 2 * 10^4
timePoints[i] is in the format "HH:MM".

analysis:
bucket sort
 */
public class MinimumTimeDifference {
    public int findMinDifference(List<String> timePoints) {
        boolean[] tick = new boolean[24 * 60];
        for (String time : timePoints) {
            String[] t = time.split(":");
            int h = Integer.parseInt(t[0]);
            int m = Integer.parseInt(t[1]);
            if (tick[h * 60 + m]) {
                return 0;
            }
            tick[h * 60 + m] = true;
        }
        int pre = 0, res = Integer.MAX_VALUE, least = Integer.MAX_VALUE, most = Integer.MIN_VALUE;
        for (int i = 0; i < tick.length; i++) {
            if (tick[i]) {
                if (least != Integer.MAX_VALUE) {
                    res = Math.min(res, i - pre);
                }
                least = Math.min(least, i);
                most = Math.max(most, i);
                pre = i;
            }
        }
        res = Math.min(res, 24 * 60 + least - most);
        return res;
    }

}
