package BFS;
/*Given two words (start and end), and a dictionary, find all shortest transformation sequence(s) from start to end, such that:

Only one letter can be changed at a time
Each intermediate word must exist in the dictionary
Example
Given:
start = "hit"
end = "cog"
dict = ["hot","dot","dog","lot","log"]

Return

[
  ["hit","hot","dot","dog","cog"],
  ["hit","hot","lot","log","cog"]
]
Notice
All words have the same length.
All words contain only lowercase alphabetic characters.*/

import java.util.*;

public class WordLadderII {
	/**
     * @param start, a string
     * @param end, a string
     * @param dict, a set of string
     * @return a list of lists of string
     */
   public List<List<String>> findLadders(String start, String end, Set<String> dict) {
       // write your code here
       List<List<String>> res = new ArrayList<List<String>>();
       Map<String, List<String>> neighborsMap = new HashMap<String, List<String>>();
       // the smallest step from start to current node
       Map<String, Integer> distance = new HashMap<>();
       dict.add(start);
       dict.add(end);
       bfs(start, end, dict, neighborsMap, distance);
       List<String> path = new ArrayList<>();
       dfs(start, end, neighborsMap, distance, res, path);
       return res;
   }
   private void bfs(String start, String end, Set<String> dict, Map<String, List<String>> neighborsMap, Map<String, Integer> distance){
       //initial neighborsMap
       for(String temp : dict){
           neighborsMap.put(temp, new ArrayList<String>());
       }
       distance.put(start, 0);
       
       Queue<String> queue = new LinkedList<>();
       queue.offer(start);
       //
       while(!queue.isEmpty()){
           int size = queue.size();
           for(int i = 0; i < size; i++){
               String temp = queue.poll();
               List<String> neighbors = findNeighbors(temp, dict);
               for(String n : neighbors){
                   neighborsMap.get(n).add(temp);
                   if(!distance.containsKey(n)){
                       distance.put(n, distance.get(temp) + 1);
                       queue.offer(n);
                   }
               }
           }
       }
   }
   
   private void dfs(String start, String current, Map<String, List<String>> neighborsMap, Map<String, Integer> distance, List<List<String>> res, List<String> path){
       path.add(current);
       if(current.equals(start)){
           Collections.reverse(path);
           res.add(new ArrayList<String>(path));
           Collections.reverse(path);
       } else {
           List<String> list = neighborsMap.get(current);
           for(String nb : list){
               if(distance.containsKey(nb) && distance.get(current) == distance.get(nb) + 1){
                   dfs(start, nb, neighborsMap, distance, res, path);   
               }
           }
       }
       path.remove(path.size() - 1);
   }
   
   private List<String> findNeighbors(String s, Set<String> dict){
       List<String> res = new ArrayList<>();
       int len = s.length();
       for(int i = 0; i < len; i++){
           for(char c = 'a'; c <= 'z'; c++){
               String temp = s.substring(0, i) + c + s.substring(i + 1, len);
               if(dict.contains(temp)){
                   res.add(temp);
               }
           }
       }
       return res;
   }
}
