package BFS;
/*
Given two words beginWord and endWord, and a dictionary wordList, return the length of the shortest transformation sequence from beginWord to endWord, such that:

Only one letter can be changed at a time.
Each transformed word must exist in the word list.
Return 0 if there is no such transformation sequence.



Example 1:

Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
Output: 5
Explanation: As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog", return its length 5.
Example 2:

Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"]
Output: 0
Explanation: The endWord "cog" is not in wordList, therefore no possible transformation.


Constraints:

1 <= beginWord.length <= 100
endWord.length == beginWord.length
1 <= wordList.length <= 5000
wordList[i].length == beginWord.length
beginWord, endWord, and wordList[i] consist of lowercase English letters.
beginWord != endWord
All the strings in wordList are unique.
*/

import java.util.*;

public class WordLadder {
	/**
     * @param start, a string
     * @param end, a string
     * @param wordList, a list of string
     * @return an integer
     */
   public int ladderLength(String start, String end, List<String> wordList) {
       Set<String> dict = new HashSet<>(wordList);
       int dist = 1;
       HashSet<String> visited = new HashSet<>();
       Queue<String> queue = new LinkedList<>();
       queue.offer(start);
       visited.add(start);
       while(!queue.isEmpty()){
           int size = queue.size();
           for(int i = 0; i < size; i++){
               String cur = queue.poll();
               if(end.equals(cur)){
                   return dist;
               }
               List<String> neighbors = findNeighbors(cur, dict);
               for(String nb : neighbors){
                   if(!visited.contains(nb)){
                       queue.offer(nb);
                       visited.add(nb);
                   }
               }
           }
           dist++;
       }
       return 0;
   }
   private List<String> findNeighbors(String str, Set<String> dict){
       List<String> res = new ArrayList<>();
       int len = str.length();
       for(int i = 0; i < len; i++){
           for(char c = 'a'; c <= 'z'; c++){
               if(c != str.charAt(i)){
                   String temp = replace(str, i, c);
                   if(dict.contains(temp)){
                       res.add(temp);
                   }
               }
           }
       }
       return res;
   }
   
   private String replace(String s, int i, char c){
       char[] temp = s.toCharArray();
       temp[i] = c;
       return new String(temp);
   }
}
