package bfs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.TreeMap;

/*
#269
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
There may be multiple valid order of letters, return all topological sort combinations
r*/
public class AlienDictionaryGoogle {
	/**
     * @param words: a list of words
     * @return: a string which is correct order
     */
    public List<String> alienOrder(String[] words) {
        List<String> res = new ArrayList<>();
        if(words == null || words.length == 0){
            return res;
        }
        Map<Character, Set<Character>> graph = new HashMap<>();
        Map<Character, Integer> inDegree = new HashMap<>();
        //initialize degree map
        for(String word : words){
            for(char c : word.toCharArray()){
                inDegree.put(c, 0);
            }
        }
        //compare adjacent string & fill graph
        for(int i = 0; i < words.length - 1; i++){
            String cur = words[i];
            String post = words[i + 1];
            int len = Math.min(cur.length(), post.length());
            int j;
            for(j = 0; j < len; j++){
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
            if(j == len){ // special case
                if(cur.length() > post.length()){
                    return res;
                }
            }
        }
        // backtracking
        dfs(res, graph, inDegree, new StringBuilder());
        return res;
    }

    private void dfs(List<String> res, Map<Character, Set<Character>> graph, Map<Character, Integer> inDegree, StringBuilder sb) {
        if(sb.length() == inDegree.size()){
            res.add(sb.toString());
            return;
        }
        Set<Character> letters = inDegree.keySet();
        for(Character c : letters){
            if(inDegree.get(c) == 0){
                sb.append(c);
                inDegree.put(c, inDegree.get(c) - 1);
                if(graph.containsKey(c)){
                    for(char nb : graph.get(c)){
                        inDegree.put(nb, inDegree.getOrDefault(nb, 0) - 1);
                    }
                }
                dfs(res, graph, inDegree, sb);
                //compensate back
                if(graph.containsKey(c)){
                    for(char nb : graph.get(c)){
                        inDegree.put(nb, inDegree.getOrDefault(nb, 0) + 1);
                    }
                }
                inDegree.put(c, inDegree.get(c) + 1);
                sb.deleteCharAt(sb.length() - 1);
            }
        }
    }
}
