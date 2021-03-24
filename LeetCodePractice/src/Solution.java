import bfs.ArrangeObjectGoogle;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;


public class Solution {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        ArrangeObjectGoogle a = new ArrangeObjectGoogle();
        a.test();
        /*
        int[][] events = {{1, 0, 1, 1, 1}, {1, 0, 1, 1, 1}, {0, 1, 0, 1, 1}};
        LargestPerimeterIsland largestIsland = new LargestPerimeterIsland();
        int res = largestIsland.largestPerimeter(events);
        System.out.println(res);

		String x = "/abcd/erf";
		String[] xm = x.split("/", 1);
		RearrangeStringkDistanceApart rearrangeStringkDistanceApart = new RearrangeStringkDistanceApart();
		String res = rearrangeStringkDistanceApart.rearrangeString("aabbcc", 3);
		System.out.println(res);

		String X = "XMJYAUZ";
		String Y = "XMJAATZ";

		ImplementDiff implementDiff = new ImplementDiff();
		System.out.println(implementDiff.diff(X, Y));


		int[]   in = {4, 8, 10, 12, 14, 20, 2};
//		int[] level = {5,4,8,1,7,2,6,3};
//		ConstructBinarySearchTreeFromLevelOrderTraversals sol = new ConstructBinarySearchTreeFromLevelOrderTraversals();
//		TreeNode root = sol.constructTree(level);
//		System.out.println(root.val);
		TheEarliestMomentWhenEveryoneBecomeFriends mie = new TheEarliestMomentWhenEveryoneBecomeFriends();
		int[][] events = {{8,9,5}, {9,12,4}, {12,14,2}, {8,12,7}, {13,16,3}, {14,15,4}};
		int[][] logs = {{20190101,0,1}, {20190104,3,4}, {20190107,2,3}, {20190211,1,5}, {20190224,2,4}, {20190301,0,3}, {20190312,1,2}, {20190322,4,5}};
		int res = mie.earliestAcq(logs, 6);


		String[] sentences = {"i love you", "island","ironman", "i love leetcode"};
		AutocompleteSystem cs = new AutocompleteSystem(sentences, cnt);
		String test = "i a#";
		for(char c : test.toCharArray()){
			List<String> res = cs.input(c);
			System.out.println(res);
		}
		for(char c : test.toCharArray()){
			List<String> res = cs.input(c);
			System.out.println(res);
		}

     	BraceExpansion be = new BraceExpansion();
		String input = "{a,b,c}d{e,f}";
		String[] res = be.expand(input);
		int x = 0;
		Arrays.stream(res).forEach(a -> System.out.println(a));
		*/


    }

    public boolean wordPatternMatch(String pattern, String str) {
        // write your code here
        // char in pattern : word in str
        Map<Character, String> map = new HashMap<>();
        // matched word in str
        Set<String> visited = new HashSet<>();
        return dfs(pattern, str, map, visited);
    }
    private boolean dfs(String pattern, String str, Map<Character, String> map, Set<String> visited){
        if(pattern.length() == 0){
            return str.length() == 0;
        }
        char c = pattern.charAt(0);
        if(map.containsKey(c)){
            String match = map.get(c);
            if(!str.startsWith(match)){
                return false;
            }
            return dfs(pattern.substring(1), str.substring(match.length()), map, visited);
        }
        for(int i = 1; i <= str.length(); i++){
            String match = str.substring(0, i);
            if(!visited.contains(match)){
                visited.add(match);
                map.put(c, match);
                if( dfs(pattern.substring(1), str.substring(match.length()), map, visited)){
                    return true;
                }
                map.remove(c);
                visited.remove(match);
            }
        }
        return false;
    }
}

