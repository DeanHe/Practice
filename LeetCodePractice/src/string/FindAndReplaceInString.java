package string;

import java.util.HashMap;
import java.util.Map;

/*
To some string S, we will perform some replacement operations that replace groups of letters with new ones (not necessarily the same size).

Each replacement operation has 3 parameters: a starting index i, a source word x and a target word y.  The rule is that if x starts at position i in the original string S, then we will replace that occurrence of x with y.  If not, we do nothing.

For example, if we have S = "abcd" and we have some replacement operation i = 2, x = "cd", y = "ffff", then because "cd" starts at position 2 in the original string S, we will replace it with "ffff".

Using another example on S = "abcd", if we have both the replacement operation i = 0, x = "ab", y = "eee", as well as another replacement operation i = 2, x = "ec", y = "ffff", this second operation does nothing because in the original string S[2] = 'c', which doesn't match x[0] = 'e'.

All these operations occur simultaneously.  It's guaranteed that there won't be any overlap in replacement: for example, S = "abc", indexes = [0, 1], sources = ["ab","bc"] is not a valid test case.



Example 1:

Input: S = "abcd", indexes = [0, 2], sources = ["a", "cd"], targets = ["eee", "ffff"]
Output: "eeebffff"
Explanation:
"a" starts at index 0 in S, so it's replaced by "eee".
"cd" starts at index 2 in S, so it's replaced by "ffff".
Example 2:

Input: S = "abcd", indexes = [0, 2], sources = ["ab","ec"], targets = ["eee","ffff"]
Output: "eeecd"
Explanation:
"ab" starts at index 0 in S, so it's replaced by "eee".
"ec" doesn't starts at index 2 in the original S, so we do nothing.


Constraints:

0 <= S.length <= 1000
S consists of only lowercase English letters.
0 <= indexes.length <= 100
0 <= indexes[i] < S.length
sources.length == indexes.length
targets.length == indexes.length
1 <= sources[i].length, targets[i].length <= 50
sources[i] and targets[i] consist of only lowercase English letters.

analysis:
TC O(SN)
 */
public class FindAndReplaceInString {
    public String findReplaceString(String s, int[] indices, String[] sources, String[] targets) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < indices.length; i++){
            if(s.startsWith(sources[i], indices[i])){
                map.put(indices[i], i);
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < s.length();){
            if(map.containsKey(i)){
                int idx = map.get(i);
                sb.append(targets[idx]);
                i += sources[idx].length();
            } else {
                sb.append(s.charAt(i));
                i++;
            }
        }
        return sb.toString();
    }
}

