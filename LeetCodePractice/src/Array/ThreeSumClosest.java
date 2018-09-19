package Array;

import java.util.Arrays;

public class ThreeSumClosest {
	public int threeSumClosest(int[] num, int target) {
        int a, b, c, sum;
		int ans = num[0] + num[1] + num[num.length - 1];

		Arrays.sort(num);
		search: for (int i = 0; i < num.length - 2; i++) {
			a = num[i];
			for (int j = i + 1, k = num.length - 1; j < k;) {
				b = num[j];
				c = num[k];
				sum = a + b + c;
				if (Math.abs(target - sum) < Math.abs(target - ans)) {
					ans = sum;
				}
				if (sum > target) {
					k--;
				} else if (sum < target) {
					j++;
				}
				// sum == target
				else {
					ans = sum;
					break search;
				}
			}
		}
		return ans;
    }
}
