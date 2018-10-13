package BFS;

import java.util.*;

public class SimilarStringGroups {
    public int numSimilarGroups(String[] A) {
        Set<String> unchecked = new HashSet<>();
        for(String s : A){
            unchecked.add(s);
        }
        int group = 0;
        for(String s : A){
            if(unchecked.contains(s)){
                group++;
                Queue<String> queue = new LinkedList<>();
                queue.offer(s);
                while(!queue.isEmpty()){
                    String cur = queue.poll();
                    List<String> neighbors = new ArrayList<>();
                    for(String candidate_nb : unchecked){
                        if(areSimilar(cur, candidate_nb)){
                            neighbors.add(candidate_nb);
                        }
                    }
                    for(String nb : neighbors){
                        queue.offer(nb);
                        unchecked.remove(nb);
                    }
                }
            }
        }
        return group;
    }
    
    private boolean areSimilar(String s1, String s2){
        int diff = 0;
        if(s1.length() != s2.length()){
            return false;
        }
        for(int i = 0; i < s1.length(); i++){
            if(s1.charAt(i) != s2.charAt(i)){
                diff++;
                if(diff > 2){
                    return false;
                }
            }
        }
        return true;
    }
}
