package String;

/*
Sometimes people repeat letters to represent extra feeling, such as "hello" -> "heeellooo", "hi" -> "hiiii".  In these strings like "heeellooo", we have groups of adjacent letters that are all the same:  "h", "eee", "ll", "ooo".

For some given string S, a query word is stretchy if it can be made to be equal to S by any number of applications of the following extension operation: choose a group consisting of characters c, and add some number of characters c to the group so that the size of the group is 3 or more.

For example, starting with "hello", we could do an extension on the group "o" to get "hellooo", but we cannot get "helloo" since the group "oo" has size less than 3.  Also, we could do another extension like "ll" -> "lllll" to get "helllllooo".  If S = "helllllooo", then the query word "hello" would be stretchy because of these two extension operations: query = "hello" -> "hellooo" -> "helllllooo" = S.

Given a list of query words, return the number of words that are stretchy.



Example:
Input:
S = "heeellooo"
words = ["hello", "hi", "helo"]
Output: 1
Explanation:
We can extend "e" and "o" in the word "hello" to get "heeellooo".
We can't extend "helo" to get "heeellooo" because the group "ll" is not size 3 or more.


Constraints:

0 <= len(S) <= 100.
0 <= len(words) <= 100.
0 <= len(words[i]) <= 100.
S and all words in words consist only of lowercase letters

analysis:
two pointers
 */
public class ExpressiveWords {
    public int expressiveWords(String S, String[] words) {
        if(S == null || words == null){
            return 0;
        }
        int res = 0;
        for(String w : words){
            if(expressive(S, w)){
                res++;
            }
        }
        return res;
    }

    private boolean expressive(String s, String word) {
        if(word == null){
            return false;
        }
        int i = 0, j = 0;
        while(i < s.length() && j < word.length()){
            if(s.charAt(i) == word.charAt(j)){
                int l1 = getRepeatCharacterLength(s, i);
                int l2 = getRepeatCharacterLength(word, j);
                if(l1 < 3 && l1 != l2 || l1 >= 3 && l1 < l2){
                    return false;
                }
                i += l1;
                j += l2;
            } else {
                return false;
            }
        }
        return i == s.length() && j == word.length();
    }

    private int getRepeatCharacterLength(String str, int idx){
        int i = idx;
        while(i < str.length() && str.charAt(i) == str.charAt(idx)){
            i++;
        }
        return i - idx;
    }

}
