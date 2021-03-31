package dfs;

import java.util.ArrayList;
import java.util.List;

/*
#784

Given a string S, we can transform every letter individually to be lowercase or uppercase to create another string.

Return a list of all possible strings we could create. You can return the output in any order.



Example 1:

Input: S = "a1b2"
Output: ["a1b2","a1B2","A1b2","A1B2"]
Example 2:

Input: S = "3z4"
Output: ["3z4","3Z4"]
Example 3:

Input: S = "12345"
Output: ["12345"]
Example 4:

Input: S = "0"
Output: ["0"]


Constraints:

S will be a string with length between 1 and 12.
S will consist only of letters or digits.
 */
public class LetterCasePermutation {
    public List<String> letterCasePermutation(String s) {
        List<String> res = new ArrayList<>();
        char[] arr = new char[s.length()];
        dfs(res, arr, 0, s);
        return res;
    }

    private void dfs(List<String> res, char[] arr, int i, String s) {
        if(i == s.length()){
            res.add(String.valueOf(arr));
            return;
        }
        char c = s.charAt(i);
        if(Character.isAlphabetic(c)){
            arr[i] = Character.toLowerCase(c);
            dfs(res, arr, i + 1, s);
            arr[i] = Character.toUpperCase(c);
            dfs(res, arr, i + 1, s);
        } else {
            arr[i] = c;
            dfs(res, arr, i + 1, s);
        }
    }
}
