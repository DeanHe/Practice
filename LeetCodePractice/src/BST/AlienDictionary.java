package BST;

import java.util.*;
/*There is a new alien language which uses the latin alphabet. However, the order among letters are unknown to you. You receive a list of non-empty words from the dictionary, where words are sorted lexicographically by the rules of this new language. Derive the order of letters in this language.

Example
Given the following words in dictionary,

[
  "wrt",
  "wrf",
  "er",
  "ett",
  "rftt"
]
The correct order is: "wertf"

Given the following words in dictionary,

[
  "z",
  "x"
]
The correct order is: "zx".

Notice
You may assume all letters are in lowercase.
You may assume that if a is a prefix of b, then a must appear before b in the given dictionary.
If the order is invalid, return an empty string.
There may be multiple valid order of letters, return the smallest in lexicographical order*/
public class AlienDictionary {
	/**
     * @param words: a list of words
     * @return: a string which is correct order
     */
    public String alienOrder(String[] words) {
        // Write your code here
        if(words == null || words.length == 0){
            return "";
        }
        Map<Character, Set<Character>> graph = new HashMap<>();
        Map<Character, Integer> indegree = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        //initialize degree map
        for(int i = 0; i < words.length; i++){
            char[] charArray = words[i].toCharArray();
            for(int j = 0; j < charArray.length; j++){
                indegree.put(charArray[j], 0);
            }
        }
        //compare adjacent string & fill map
        for(int i = 0; i < words.length - 1; i++){
            String cur = words[i];
            String post = words[i + 1];
            int len = Math.min(cur.length(), post.length());
            for(int j = 0; j < len; j++){
                char cur_c = cur.charAt(j);
                char post_c = post.charAt(j);
                if(cur_c != post_c){
                    Set<Character> neighbors = new HashSet<>();
                    if(graph.containsKey(cur_c)){
                        neighbors =graph.get(cur_c);
                    }
                    if(!neighbors.contains(post_c)){
                        neighbors.add(post_c);
                        graph.put(cur_c, neighbors);
                        int degree = indegree.get(post_c) + 1;
                        indegree.put(post_c, degree);
                    }
                    break;
                } else {
                    if(j + 1 == post.length() && j + 1 < cur.length()){
                        return "";
                    }
                }
            }
        }
        //BFS - use Queue to pop char in order
        // as we should return the topo order with lexicographical order
        // we should use PriorityQueue instead of a FIFO Queue
        Queue<Character> queue = new PriorityQueue<>();
        for(char c : indegree.keySet()){
            if(indegree.get(c) == 0){
                queue.offer(c);
            }
        }
        while(!queue.isEmpty()){
            char c = queue.poll();
            sb.append(c);
            if(graph.containsKey(c)){
                Set<Character> neighbors = graph.get(c);
                for(char nb : neighbors){
                    int degree = indegree.get(nb);
                    if(degree > 0){
                        degree--;
                        indegree.put(nb, degree);
                        if(degree == 0){
                            queue.offer(nb);
                        }
                    }
                }
            }
        }
        System.out.println(sb.toString());
        //avoid loops. only < possible -- eg: ["qd","ab"] res = qa
        if(sb.length() != indegree.size()){
            return "";
        }
        return sb.toString();
    }
}
