package bfs;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/*
#821

Given a string s and a character c that occurs in s, return an array of integers answer where answer.length == s.length and answer[i] is the shortest distance from s[i] to the character c in s.

Example 1:
Input: s = "loveleetcode", c = "e"
Output: [3,2,1,0,1,0,0,1,2,2,1,0]
Example 2:

Input: s = "aaab", c = "b"
Output: [3,2,1,0]


Constraints:

1 <= s.length <= 104
s[i] and c are lowercase English letters.
c occurs at least once in s.
 */
public class ShortestDistanceToaCharacter {
    public int[] shortestToChar(String s, char c) {
        int len = s.length();
        int[] res = new int[len];
        Queue<Integer> q = new LinkedList<>();
        Arrays.fill(res, -1);
        for(int i = 0; i < len; i++){
            char sc = s.charAt(i);
            if(sc == c){
                q.offer(i);
                res[i] = 0;
            }
        }
        int step = 1;
        while(!q.isEmpty()){
            int size = q.size();
            for(int i = 0; i < size; i++){
                int idx = q.poll();
                if(idx > 0 && res[idx - 1] == -1){
                    q.offer(idx - 1);
                    res[idx - 1] = step;
                }
                if(idx + 1 < len && res[idx + 1] == -1){
                    q.offer(idx + 1);
                    res[idx + 1] = step;
                }
            }
            step++;
        }
        return res;
    }
}
