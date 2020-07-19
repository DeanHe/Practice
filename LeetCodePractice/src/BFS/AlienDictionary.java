package BFS;

import java.util.*;
/*
There is a new alien language which uses the latin alphabet. However,
the order among letters are unknown to you.
You receive a list of non-empty words from the dictionary, where words are sorted lexicographically by the rules of this new language.
Derive the order of letters in this language.

1.You may assume all letters are in lowercase.
2.The dictionary is invalid, if a is prefix of b and b is appear before a.
3.If the order is invalid, return an empty string.
4.There may be multiple valid order of letters, return the smallest in normal lexicographical order

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

Explanation：
from "wrt"and"wrf" ,we can get 't'<'f'
from "wrt"and"er" ,we can get 'w'<'e'
from "er"and"ett" ,we can get 'r'<'t'
from "ett"and"rftt" ,we can get 'e'<'r'
So return "wertf"

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
        Map<Character, Integer> inDegree = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        //initialize degree map
        for(String word : words){
            char[] charArray = word.toCharArray();
            for(char c : charArray){
                inDegree.put(c, 0);
            }
        }
        //compare adjacent string & fill graph
        for(int i = 0; i < words.length - 1; i++){
            String cur = words[i];
            String post = words[i + 1];
            int len = Math.min(cur.length(), post.length());
            for(int j = 0; j < len; j++){
                char cur_c = cur.charAt(j);
                char post_c = post.charAt(j);
                if(cur_c != post_c){
                    graph.putIfAbsent(cur_c, new HashSet<>());
                    if(!graph.get(cur_c).contains(post_c)){
                    	graph.get(cur_c).add(post_c);
                        inDegree.put(post_c, inDegree.get(post_c) + 1);
                    }
                    break;
                }
            }
        }
        //BFS - use Queue to pop char in order
        // as we should return the topo order with lexicographical order
        // we should use PriorityQueue instead of a FIFO Queue
        Queue<Character> pq = new PriorityQueue<>();
        for(char c : inDegree.keySet()){
            if(inDegree.get(c) == 0){
                pq.offer(c);
            }
        }
        while(!pq.isEmpty()){
            char c = pq.poll();
            sb.append(c);
            if(graph.containsKey(c)){
                Set<Character> neighbors = graph.get(c);
                for(char nb : neighbors){
                    int inDeg = inDegree.get(nb);
                    if(inDeg > 0){
                        inDeg--;
                        inDegree.put(nb, inDeg);
                        if(inDeg == 0){
                            pq.offer(nb);
                        }
                    }
                }
            }
        }
        System.out.println(sb.toString());
        //avoid loops. only < possible -- eg: ["qd","ab"] res = qa
        if(sb.length() != inDegree.size()){
            return "";
        }
        return sb.toString();
    }
}
