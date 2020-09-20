package Contest;

import java.util.HashSet;

public class SplitaStringIntoTheMaxNumberOfUniqueSubstrings {
    public int maxUniqueSplit(String s) {
        return dfs(s, new HashSet<>(),0);
    }

    private int dfs(String s, HashSet<String> set, int pos) {
        int res = 0;
        if(pos >= s.length()){
            return res;
        }
        for(int i = pos + 1; i <= s.length(); i++){
            String sub = s.substring(pos, i);
            if(!set.contains(sub)){
                set.add(sub);
                res = Math.max(res, dfs(s, set, i) + 1);
                set.remove(sub);
            }
        }
        return res;
    }

}
