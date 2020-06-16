package String;

/*
Given a string s. Return all the words vertically in the same order in which they appear in s.
        Words are returned as a list of strings, complete with spaces when is necessary. (Trailing spaces are not allowed).
        Each word would be put on only one column and that in one column there will be only one word.



        Example 1:

        Input: s = "HOW ARE YOU"
        Output: ["HAY","ORO","WEU"]
        Explanation: Each word is printed vertically.
        "HAY"
        "ORO"
        "WEU"
        Example 2:

        Input: s = "TO BE OR NOT TO BE"
        Output: ["TBONTB","OEROOE","   T"]
        Explanation: Trailing spaces is not allowed.
        "TBONTB"
        "OEROOE"
        "   T"
        Example 3:

        Input: s = "CONTEST IS COMING"
        Output: ["CIC","OSO","N M","T I","E N","S G","T"]


        Constraints:

        1 <= s.length <= 200
        s contains only upper case English letters.
        It's guaranteed that there is only one space between 2 words.
*/

import java.util.*;

public class PrintWordsVertically {
    public List<String> printVertically(String s) {
        List<String> res = new ArrayList<>();
        int maxlen = 0;
        String[] arr = s.split(" ");
        for (String str : arr) {
            maxlen = Math.max(maxlen, str.length());
        }
        for (int r = 0; r < maxlen; r++) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < arr.length; i++) {
                if (r < arr[i].length()) {
                    sb.append(arr[i].charAt(r));
                } else {
                    sb.append(" ");
                }
            }
            while (sb.charAt(sb.length() - 1) == ' ') {
                sb.deleteCharAt(sb.length() - 1);
            }
            res.add(sb.toString());
        }
        return res;
    }
}
