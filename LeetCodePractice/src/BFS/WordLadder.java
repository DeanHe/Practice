package BFS;

import java.util.*;

public class WordLadder {
	/**
     * @param start, a string
     * @param end, a string
     * @param dict, a set of string
     * @return an integer
     */
   public int ladderLength(String start, String end, Set<String> dict) {
       // write your code here
       if(dict == null || dict.isEmpty()){
           return 0;
       }
       if(start.equals(end)){
           return 1;
       }
       dict.add(end);
       int dist = 1;
       HashSet<String> visited = new HashSet<>();
       Queue<String> queue = new LinkedList<>();
       queue.offer(start);
       while(!queue.isEmpty()){
           dist++;
           int size = queue.size();
           for(int i = 0; i < size; i++){
               String cur = queue.poll();
               visited.add(cur);
               ArrayList<String> neighbors = findNeighbors(cur, dict);
               for(String temp : neighbors){
                   if(end.equals(temp)){
                       return dist;
                   }
                   if(!visited.contains(temp)){
                       queue.offer(temp);
                   }
               }
           }
       }
       return 0;
   }
   private ArrayList<String> findNeighbors(String start, Set<String> dict){
       ArrayList<String> res = new ArrayList<>();
       int len = start.length();
       for(int i = 0; i < len; i++){
           for(char c = 'a'; c <= 'z'; c++){
               if(c != start.charAt(i)){
                   String temp = replace(start, i, c);
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
