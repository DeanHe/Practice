package contest;

import java.util.*;

/*
An integer has sequential digits if and only if each digit in the number is one more than the previous digit.

        Return a sorted list of all the integers in the range [low, high] inclusive that have sequential digits.

        Example 1:

        Input: low = 100, high = 300
        Output: [123,234]
        Example 2:

        Input: low = 1000, high = 13000
        Output: [1234,2345,3456,4567,5678,6789,12345]

        Constraints:

        10 <= low <= high <= 10^9
*/
public class SequentialDigits {
    public List<Integer> sequentialDigits(int low, int high) {
        List<Integer> res = new ArrayList<>();
        for (int d = 1; d <= 9; d++) {
            int last = d, cur = d;
            while (cur <= high && last < 10) {
                if (cur >= low) {
                    res.add(cur);
                }
                last++;
                int temp = cur * 10 + last;
                if (temp > cur) {
                    cur = temp;
                } else {
                    break;
                }
            }
        }
        Collections.sort(res);
        return res;
    }
}
