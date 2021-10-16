package string;

import java.util.ArrayList;
import java.util.List;

/*
Given an array of strings words and a width maxWidth, format the text such that each line has exactly maxWidth characters and is fully (left and right) justified.

You should pack your words in a greedy approach; that is, pack as many words as you can in each line. Pad extra spaces ' ' when necessary so that each line has exactly maxWidth characters.

Extra spaces between words should be distributed as evenly as possible. If the number of spaces on a line does not divide evenly between words, the empty slots on the left will be assigned more spaces than the slots on the right.

For the last line of text, it should be left-justified and no extra space is inserted between words.

Note:

A word is defined as a character sequence consisting of non-space characters only.
Each word's length is guaranteed to be greater than 0 and not exceed maxWidth.
The input array words contains at least one word.


Example 1:

Input: words = ["This", "is", "an", "example", "of", "text", "justification."], maxWidth = 16
Output:
[
   "This    is    an",
   "example  of text",
   "justification.  "
]
Example 2:

Input: words = ["What","must","be","acknowledgment","shall","be"], maxWidth = 16
Output:
[
  "What   must   be",
  "acknowledgment  ",
  "shall be        "
]
Explanation: Note that the last line is "shall be    " instead of "shall     be", because the last line must be left-justified instead of fully-justified.
Note that the second line is also left-justified becase it contains only one word.
Example 3:

Input: words = ["Science","is","what","we","understand","well","enough","to","explain","to","a","computer.","Art","is","everything","else","we","do"], maxWidth = 20
Output:
[
  "Science  is  what we",
  "understand      well",
  "enough to explain to",
  "a  computer.  Art is",
  "everything  else  we",
  "do                  "
]


Constraints:

1 <= words.length <= 300
1 <= words[i].length <= 20
words[i] consists of only English letters and symbols.
1 <= maxWidth <= 100
words[i].length <= maxWidth

analysis:
Greedy
 */
public class TextJustification {
    public List<String> fullJustify(String[] words, int maxWidth) {
        int left = 0;
        List<String> res = new ArrayList<>();
        while (left < words.length) {
            int right = findRight(left, words, maxWidth);
            res.add(justify(left, right, words, maxWidth));
            left = right + 1;
        }
        return res;
    }

    private int findRight(int left, String[] words, int maxWidth) {
        int right = left;
        int sum = words[right++].length();
        while (right < words.length && (sum + 1 + words[right].length()) <= maxWidth) {
            sum += 1 + words[right++].length();
        }
        return right - 1;
    }

    private String justify(int left, int right, String[] words, int maxWidth) {
        if (left == right) {
            return pad(words[left], maxWidth);
        }
        boolean isLastLine = right == words.length - 1;
        int spaceCnt = right - left;
        int totalSpace = maxWidth - wordsLength(left, right, words);
        String space = isLastLine ? " " : blank(totalSpace / spaceCnt);
        int remainder = isLastLine ? 0 : totalSpace % spaceCnt;
        StringBuilder sb = new StringBuilder();
        for (int i = left; i <= right; i++) {
            sb.append(words[i]).append(space).append(remainder-- > 0 ? " " : "");
        }
        return pad(sb.toString().trim(), maxWidth);
    }

    private int wordsLength(int left, int right, String[] words) {
        int res = 0;
        for (int i = left; i <= right; i++) {
            res += words[i].length();
        }
        return res;
    }

    private String pad(String str, int maxWidth) {
        return str + blank(maxWidth - str.length());
    }

    private String blank(int len) {
        return new String(new char[len]).replace('\0', ' ');
    }
}
