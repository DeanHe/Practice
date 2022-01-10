package hashMap;

import java.util.*;

/*
parse all the input values:

for example:
T1 = 1
T2 = 2
T3 = T4 + T5
T4 = T6 - 8
T5 = T4 + T2
T6 = T2 - T1
T7 = T3
 */
public class ComputeExpressionInstacart {
    public Map<String, Integer> computeExpression(String[] input){
        Map<String, Integer> map = new HashMap<>();
        Queue<String> q = new LinkedList<>();
        for(String s : input){
            q.offer(s);
        }
        while(!q.isEmpty()){
            String s = q.poll();
            String[] arr = s.split("=");
            String left = arr[0].trim();
            String right = arr[1].trim();
            if(right.contains("+")){
                String[] rarr = right.split("\\+");
                String r0 = rarr[0].trim();
                String r1 = rarr[1].trim();
                if(map.containsKey(r0) && map.containsKey(r1)){
                    map.put(left, map.get(r0) + map.get(r1));
                } else {
                    q.offer(s);
                }
            } else if(right.contains("-")){
                String[] rarr = right.split("-");
                String r0 = rarr[0].trim();
                if(isNumber(r0)){
                    map.put(r0, Integer.valueOf(r0));
                }
                String r1 = rarr[1].trim();
                if(isNumber(r1)){
                    map.put(r1, Integer.valueOf(r1));
                }
                if(map.containsKey(r0) && map.containsKey(r1)){
                    map.put(left, map.get(r0) - map.get(r1));
                } else {
                    q.offer(s);
                }
            } else {
                if(map.containsKey(right)){
                    map.put(left, map.get(right));
                } else if(isNumber(right)){
                    map.put(left, Integer.valueOf(right));
                } else {
                    q.offer(s);
                }
            }
        }
        return map;
    }

    private boolean isNumber(String num){
        for(char c : num.toCharArray()){
            if(!Character.isDigit(c)){
                return false;
            }
        }
        return true;
    }
}
