package Array;

import java.util.*;

public class FourSum {
	public List<List<Integer>> fourSum(int[] num, int target) {
		Arrays.sort(num);
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		if (num == null || num.length < 4) {
			return result;
		}
		for (int a = 0; a < num.length - 3; a++) {
			for (int d = num.length - 1; d > a + 2; d--) {
				int b = a + 1;
				int c = d - 1;
				int loc = target - num[a] - num[d];
				while (b < c) {
					if (num[b] + num[c] > loc) {
						c--;
					} else if (num[b] + num[c] < loc) {
						b++;
					} else {
						result.add(Arrays.asList(num[a], num[b++], num[c--], num[d]));
					}
				}
			}
		}

		Set<List<Integer>> set = new HashSet<List<Integer>>(result);
		result.clear();
		result.addAll(set);
		return result;
	}
}
