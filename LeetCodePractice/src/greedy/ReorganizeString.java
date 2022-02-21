package greedy;

/*
Given a string S, check if the letters can be rearranged so that two characters that are adjacent to each other are not the same.

If possible, output any possible result.  If not possible, return the empty string.

Example 1:
Input: S = "aab"
Output: "aba"

Example 2:
Input: S = "aaab"
Output: ""
Note:

S will consist of lowercase letters and have length in range [1, 500].
*/

public class ReorganizeString {
    public String reorganizeString(String S) {
        int len = S.length();
        char[] res = new char[len];
        int[] cnt = new int[26];
        int maxCnt = 0, maxLetterIdx = 0;
        for(char c : S.toCharArray()){
            int idx = c - 'a';
            cnt[idx]++;
            if(cnt[idx] > maxCnt){
                maxCnt = cnt[idx];
                maxLetterIdx = idx;
            }
        }
        if(maxCnt > (len + 1) / 2){
            return "";
        }
        int i = 0;
        while(cnt[maxLetterIdx] > 0){
            res[i] = (char)('a' + maxLetterIdx);
            cnt[maxLetterIdx]--;
            i += 2;
        }
        for(int j = 0; j < cnt.length; j++){
            while(cnt[j] > 0){
                if(i > len - 1){
                    i = 1;
                }
                res[i] = (char)('a' + j);
                cnt[j]--;
                i += 2;
            }
        }
        return String.valueOf(res);
    }
}
