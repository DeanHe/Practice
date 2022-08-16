package window;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
You are given a string s and an array of strings words of the same length. Return all starting indices of substring(s) in s that is a concatenation of each word in words exactly once,
in any order, and without any intervening characters.

You can return the answer in any order.

Example 1:
Input: s = "barfoothefoobarman", words = ["foo","bar"]
Output: [0,9]
Explanation: Substrings starting at index 0 and 9 are "barfoo" and "foobar" respectively.
The output order does not matter, returning [9,0] is fine too.

Example 2:
Input: s = "wordgoodgoodgoodbestword", words = ["word","good","best","word"]
Output: []

Example 3:
Input: s = "barfoofoobarthefoobarman", words = ["bar","foo","the"]
Output: [6,9,12]

Constraints:

1 <= s.length <= 104
s consists of lower-case English letters.
1 <= words.length <= 5000
1 <= words[i].length <= 30
words[i] consists of lower-case English letters.

analysis:
multiple start point sliding window,
create a array of single word Length hashmap

TC O(N)
SC O(single word Length)

 */
public class SubstringWithConcatenationOfAllWords {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> res = new ArrayList<>();
        if(s == null || s.length() == 0 || words == null || words.length == 0){
            return res;
        }
        int singleLen = words[0].length();
        int wordsLen = singleLen * words.length;
        Map<String, Integer> wmap = new HashMap<>();
        for(String w : words){
            wmap.put(w, wmap.getOrDefault(w, 0) + 1);
        }
        HashMap<String, Integer>[] smap = new HashMap[singleLen];
        for(int i = 0; i < singleLen; i++){
            smap[i] = new HashMap<>();
        }
        for(int i = 0; i + singleLen <= s.length(); i++){
            int idx = i % singleLen;
            String tail = s.substring(i, i + singleLen);
            smap[idx].put(tail, smap[idx].getOrDefault(tail, 0) + 1);
            if(i >= wordsLen){
                String head = s.substring(i - wordsLen, i - wordsLen + singleLen);
                smap[idx].put(head, smap[idx].get(head) - 1);
                if(smap[idx].get(head) == 0){
                    smap[idx].remove(head);
                }
            }
            if(wmap.equals(smap[idx])){
                res.add(i - wordsLen + singleLen);
            }
        }
        return res;
    }
}

