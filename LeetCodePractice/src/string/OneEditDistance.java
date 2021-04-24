package string;

/*
Given two strings S and T, determine if they are both one edit distance apart.

Have you met this question in a real interview?  
Example
Example 1:

Input: s = "aDb", t = "adb" 
Output: true
Example 2:

Input: s = "ab", t = "ab" 
Output: false
Explanation:
s=t ,so they aren't one edit distance apart
*/
public class OneEditDistance {
    /**
     * @param s: a string
     * @param t: a string
     * @return: true if they are both one edit distance apart or false
     */
    public boolean isOneEditDistance(String s, String t) {
        // write your code here
        int sl = s.length();
        int tl = t.length();
        // make sure sl <= tl
        if (sl > tl) {
            return isOneEditDistance(t, s);
        }
        if (tl - sl > 1) {
            return false;
        }
        for (int i = 0; i < sl; i++) {
            if (s.charAt(i) != t.charAt(i)) {
                //replace case: check following substring match
                if (sl == tl) {
                    return s.substring(i + 1).equals(t.substring(i + 1));
                } else {
                    // delete from t case:
                    return s.substring(i).equals(t.substring(i + 1));
                }
            }
        }
        return sl != tl;
    }

    public boolean isOneEditDistanceII(String s, String t) {
        if (s == null || t == null)
            return false;

        int m = s.length();
        int n = t.length();

        if (Math.abs(m - n) > 1) {
            return false;
        }

        int i = 0;
        int j = 0;
        int count = 0;

        while (i < m && j < n) {
            if (s.charAt(i) == t.charAt(j)) {
                i++;
                j++;
            } else {
                count++;
                if (count > 1)
                    return false;

                if (m > n) {
                    i++;
                } else if (m < n) {
                    j++;
                } else {
                    i++;
                    j++;
                }
            }
        }

        if (i < m || j < n) {
            count++;
        }

        if (count == 1)
            return true;

        return false;
    }
}
