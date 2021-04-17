package sweepLine;

import java.util.Arrays;

/*
Give a array positive number means right paint move, negative means left paint move, start from position 0
Give a paint times threshold x
ask how many intervals are painted greater or equal to x times

example:
[1, -3, 1]; x = 2
return 2 as interval [0, 1],[-1, -2]  are painted >= 2 times
 */
public class BackForthPaintCount {
    private int paintMoreThanX(int[] move, int x) {
        int left = 0, right = 0, cur = 0, res = 0;
        for (int m : move) {
            cur += m;
            left = Math.min(left, cur);
            right = Math.max(right, cur);
        }
        int[] arr = new int[right - left + 1];
        int start = -left;
        for (int m : move) {
            int end = start + m;
            if (m > 0) {
                arr[start]++;
                arr[end]--;
            } else if (m < 0) {
                arr[start]--;
                arr[end]++;
            }
            start = end;
        }
        int preSum = 0;
        for(int a : arr){
            preSum += a;
            if(preSum >= x){
                res++;
            }
        }
        System.out.println(Arrays.toString(arr));
        System.out.println(res);
        return res;
    }
}
