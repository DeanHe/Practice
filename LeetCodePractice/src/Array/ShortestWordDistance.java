package Array;
/*
Given a list of words and two wordsword1_and_word2, return the shortest distance between these two words in the list.

Example:
Assume that words =["practice", "makes", "perfect", "coding", "makes"].
Input: word1 = “coding”, word2 = “practice”
Output: 3
Input: word1 = "makes", word2 = "coding"
Output: 1

Note:
You may assume that word1 does not equal to word2, and _word1 _and _word2 _are both in the list.
 */
public class ShortestWordDistance {
    public int shortestDistance(String[] words, String word1, String word2) {
        int i1 = -1, i2 = -1;
        int res = Integer.MAX_VALUE;
        for(int i = 0; i < words.length; i++){
            if(words[i].equals(word1)){
                i1 = i;
                if(i2 != -1){
                    res = Math.min(res, i1 - i2);
                }
            } else if(words[i].equals(word2)){
                res = Math.min(res, i2 - i1);
            }
        }
        return res;
    }
}
