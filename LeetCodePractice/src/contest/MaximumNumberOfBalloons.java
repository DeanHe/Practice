package contest;
/*
Given a string text, you want to use the characters of text to form as many instances of the word "balloon" as possible.

        You can use each character in text at most once. Return the maximum number of instances that can be formed.

        Example 1:

        Input: text = "nlaebolko"
        Output: 1
        Example 2:

        Input: text = "loonbalxballpoon"
        Output: 2
        Example 3:

        Input: text = "leetcode"
        Output: 0

        Constraints:

        1 <= text.length <= 10^4
        text consists of lower case English letters only.
*/
public class MaximumNumberOfBalloons {
    public int maxNumberOfBalloons(String text) {
        String word = "balloon";
        int[] textCnt = new int[26];
        int[] wdCnt = new int[26];
        char[] textArr = text.toCharArray();
        for(char c : textArr){
            textCnt[c - 'a']++;
        }
        char[] wdArr = word.toCharArray();
        for(char c : wdArr){
            wdCnt[c - 'a']++;
        }
        int min = text.length();
        for(char c : wdArr){
            min = Math.min(min, textCnt[c - 'a'] / wdCnt[c - 'a']);
        }
        return min;
    }
}
