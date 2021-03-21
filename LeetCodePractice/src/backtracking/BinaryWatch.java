package backtracking;

import java.util.*;

/*A binary watch has 4 LEDs on the top which represent the hours (0-11), and the 6 LEDs on the bottom represent the minutes (0-59).
For example, the above binary watch reads "3:25".

Given a non-negative integer n which represents the number of LEDs that are currently on, return all possible times the watch could represent.

Example:

Input: n = 1
Return: ["1:00", "2:00", "4:00", "8:00", "0:01", "0:02", "0:04", "0:08", "0:16", "0:32"]
Note:
The order of output does not matter.
The hour must not contain a leading zero, for example "01:00" is not valid, it should be "1:00".
The minute must be consist of two digits and may contain a leading zero, for example "10:2" is not valid, it should be "10:02".
Each LED represents a zero or one, with the least significant bit on the right.*/
public class BinaryWatch {
	public List<String> readBinaryWatch(int num) {
		int[] nums1 = { 8, 4, 2, 1 };
		int[] nums2 = { 32, 16, 8, 4, 2, 1 };
		List<String> res = new ArrayList<>();
		for (int i = 0; i <= num; i++) {
			List<Integer> hours = generateDigit(nums1, i);
			List<Integer> minutes = generateDigit(nums2, num - i);
			for (int hour : hours) {
				if (hour >= 12) {
					continue;
				}
				for (int min : minutes) {
					if (min >= 60) {
						continue;
					}
					res.add(hour + ":" + (min < 10 ? "0" + min : min));
				}
			}
		}
		return res;
	}

	private List<Integer> generateDigit(int[] arr, int count) {
		List<Integer> res = new ArrayList<>();
		dfs(arr, count, 0, 0, res);
		return res;
	}

	private void dfs(int[] arr, int count, int pos, int sum, List<Integer> res) {
		if (count == 0) {
			res.add(sum);
			return;
		}
		for (int i = pos; i < arr.length; i++) {
			dfs(arr, count - 1, i + 1, sum + arr[i], res);
		}
	}
}
