package dfs;

import java.util.*;
/*
plan football game match, given a country to teams mapping:
e.g. {'Italy': [c1, c2], 'Spain': [c3], 'France': [c4, c5, c6]

and a list of previously happened matches:
[c3, c6], [c4, c2], [c5, c1]

plan the next round matches for all teams,
requires that no teams from same country will match against each other
teams had game before can't match again

tag: Google
 */

public class SoccerGameGoogle {
    public String[][] planMatches(Map<String, List<String>> countries, String[][] happenedMatches){
        Map<String, Set<String>> banned = new HashMap<>();
        Set<String> set = new HashSet<>();
        for(List<String> ls : countries.values()){
            for(String team : ls){
                banned.computeIfAbsent(team, x -> new HashSet<>()).addAll(ls);
                set.add(team);
            }
        }
        List<String> teams = new ArrayList<>(set);
        for(String[] match : happenedMatches){
            banned.computeIfAbsent(match[0], x -> new HashSet<>()).add(match[1]);
            banned.computeIfAbsent(match[1], x -> new HashSet<>()).add(match[0]);
        }
        List<String[]> res = new ArrayList<>();
        if(dfs(banned, res, teams, new boolean[teams.size()])){
            return res.toArray(new String[0][0]);
        }
        return null;
    }

    private boolean dfs(Map<String, Set<String>> banned, List<String[]> res, List<String> teams, boolean[] visited) {
        if(teams.size() == res.size() * 2){
            return true;
        }
        for(int i = 0; i < teams.size(); i++){
            if(!visited[i]){
                String a = teams.get(i);
                visited[i] = true;
                for(int j = 0; j < teams.size(); j++){
                    String b = teams.get(j);
                    if(!banned.get(a).contains(b) && !visited[j]){
                        visited[j] = true;
                        res.add(new String[]{a, b});
                        if(dfs(banned, res, teams, visited)){
                            return true;
                        }
                        visited[j] = false;
                        res.remove(res.size() - 1);
                    }
                }
                visited[i] = false;
            }
        }
        return false;
    }
}
