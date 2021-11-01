package contest;

/*
An original string, consisting of lowercase English letters, can be encoded by the following steps:
Arbitrarily split it into a sequence of some number of non-empty substrings.
Arbitrarily choose some elements (possibly none) of the sequence, and replace each with its length (as a numeric string).
Concatenate the sequence as the encoded string.
For example, one way to encode an original string "abcdefghijklmnop" might be:

Split it as a sequence: ["ab", "cdefghijklmn", "o", "p"].
Choose the second and third elements to be replaced by their lengths, respectively. The sequence becomes ["ab", "12", "1", "p"].
Concatenate the elements of the sequence to get the encoded string: "ab121p".
Given two encoded strings s1 and s2, consisting of lowercase English letters and digits 1-9 (inclusive), return true if there exists an original string that could be encoded as both s1 and s2. Otherwise, return false.

Note: The test cases are generated such that the number of consecutive digits in s1 and s2 does not exceed 3.

Example 1:

Input: s1 = "internationalization", s2 = "i18n"
Output: true
Explanation: It is possible that "internationalization" was the original string.
- "internationalization"
  -> Split:       ["internationalization"]
  -> Do not replace any element
  -> Concatenate:  "internationalization", which is s1.
- "internationalization"
  -> Split:       ["i", "nternationalizatio", "n"]
  -> Replace:     ["i", "18",                 "n"]
  -> Concatenate:  "i18n", which is s2
Example 2:

Input: s1 = "l123e", s2 = "44"
Output: true
Explanation: It is possible that "leetcode" was the original string.
- "leetcode"
  -> Split:      ["l", "e", "et", "cod", "e"]
  -> Replace:    ["l", "1", "2",  "3",   "e"]
  -> Concatenate: "l123e", which is s1.
- "leetcode"
  -> Split:      ["leet", "code"]
  -> Replace:    ["4",    "4"]
  -> Concatenate: "44", which is s2.
Example 3:

Input: s1 = "a5b", s2 = "c5b"
Output: false
Explanation: It is impossible.
- The original string encoded as s1 must start with the letter 'a'.
- The original string encoded as s2 must start with the letter 'c'.
Example 4:

Input: s1 = "112s", s2 = "g841"
Output: true
Explanation: It is possible that "gaaaaaaaaaaaas" was the original string
- "gaaaaaaaaaaaas"
  -> Split:      ["g", "aaaaaaaaaaaa", "s"]
  -> Replace:    ["1", "12",           "s"]
  -> Concatenate: "112s", which is s1.
- "gaaaaaaaaaaaas"
  -> Split:      ["g", "aaaaaaaa", "aaaa", "s"]
  -> Replace:    ["g", "8",        "4",    "1"]
  -> Concatenate: "g841", which is s2.
Example 5:

Input: s1 = "ab", s2 = "a2"
Output: false
Explanation: It is impossible.
- The original string encoded as s1 has two letters.
- The original string encoded as s2 has three letters.


Constraints:

1 <= s1.length, s2.length <= 40
s1 and s2 consist of digits 1-9 (inclusive), and lowercase English letters only.
The number of consecutive digits in s1 and s2 does not exceed 3.

hint:
1 For s1 and s2, divide each into a sequence of single alphabet strings and digital strings. The problem now becomes comparing if two sequences are equal.
2 A single alphabet string has no variation, but a digital string has variations. For example: "124" can be interpreted as 1+2+4, 12+4, 1+24, and 124 wildcard characters.
3 There are four kinds of comparisons: a single alphabet vs another; a single alphabet vs a number, a number vs a single alphabet, and a number vs another number.
In the case of a number vs another (a single alphabet or a number), can you decrease the number by the min length of both?
4 There is a recurrence relation in the search which ends when either a single alphabet != another, or one sequence ran out, or both sequences ran out.

analysis:
diff means the number representation difference between s1[:i] and s2[:j]
 */
public class CheckIfAnOriginalStringExistsGivenTwoEncodedStrings {
    public boolean possiblyEquals(String s1, String s2) {
        int l1 = s1.length();
        int l2 = s2.length();
        Boolean[][][] mem = new Boolean[l1 + 1][l2 + 1][2000];
        return dfs(s1, s2, 0, 0, 0, mem);
    }

    private boolean dfs(String s1, String s2, int i, int j, int diff, Boolean[][][] mem) {
        if (i == s1.length() && j == s2.length()) {
            return diff == 0;
        }
        if (mem[i][j][diff + 1000] != null) {
            return mem[i][j][diff + 1000];
        }
        // Literal matching on both s1[i] and s2[j]
        if (i < s1.length() && j < s2.length() && diff == 0 && s1.charAt(i) == s2.charAt(j)) {
            if (dfs(s1, s2, i + 1, j + 1, 0, mem)) {
                return mem[i][j][1000] = true;
            }
        }
        // Literal matching on s1[i]
        if (i < s1.length() && !Character.isDigit(s1.charAt(i)) && diff > 0 && dfs(s1, s2, i + 1, j, diff - 1, mem)) {
            return mem[i][j][diff + 1000] = true;
        }
        // Literal matching on s2[j]
        if (j < s2.length() && !Character.isDigit(s2.charAt(j)) && diff < 0 && dfs(s1, s2, i, j + 1, diff + 1, mem)) {
            return mem[i][j][diff + 1000] = true;
        }
        // Wildcard matching on s1[i]
        for (int k = i, val = 0; k < s1.length() && Character.isDigit(s1.charAt(k)); k++) {
            val = val * 10 + (s1.charAt(k) - '0');
            if (dfs(s1, s2, k + 1, j, diff - val, mem)) {
                return mem[i][j][diff + 1000] = true;
            }
        }
        // Wildcard matching on s2[j]
        for (int k = j, val = 0; k < s2.length() && Character.isDigit(s2.charAt(k)); k++) {
            val = val * 10 + (s2.charAt(k) - '0');
            if (dfs(s1, s2, i, k + 1, diff + val, mem)) {
                return mem[i][j][diff + 1000] = true;
            }
        }
        return mem[i][j][diff + 1000] = false;
    }
}
