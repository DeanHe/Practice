package String;

public class LongestSubstringWithoutRepeatingCharacters {
	/**
	 * @param s:
	 *            a string
	 * @return: an integer
	 */
	public int lengthOfLongestSubstring(String s) {
		// write your code here
		int[] cnt = new int[256];
		char[] sc = s.toCharArray();

		int ans = 0;
		for (int left = 0, right = 0; right < s.length(); right++) {
			cnt[sc[right]]++;
			while (cnt[sc[right]] > 1) {
				cnt[sc[left]]--;
				left++;
			}
			ans = Math.max(ans, right - left + 1);
		}
		return ans;
	}
}
