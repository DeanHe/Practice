import BFS.ArrangeObjectGoogle;
import Trie.ShortEncodingOfWords;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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



		Pattern p = Pattern.compile("^\\s*\\w+(\\-\\w+)+:");
        Matcher matcher = p.matcher("  Connector-2-T1: Add a SNS Edge component as a Lambda component");
        if(matcher.find() && matcher.start() == 0) {
            //get the MatchResult Object
            int x =matcher.start();
            String a = matcher.group(0);
            String b = matcher.group(1);
            MatchResult result = matcher.toMatchResult();

            //Prints the offset after the last character matched.
            System.out.println("First Capturing Group - Match String end(): "+result.end());
        } else {
            System.out.println("Not found");
        }
		*/
    }
    public List<String> letterCasePermutation(String S) {
        List<String> res = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        dfs(res, sb, 0, S);
        return res;
    }

    private void dfs(List<String> res, StringBuilder sb, int i, String s) {
        if(i == s.length()){
            res.add(sb.toString());
            return;
        }
        char c = s.charAt(i);
        if(Character.isAlphabetic(c)){
            dfs(res, sb.append(Character.toUpperCase(c)), i + 1, s);
            sb.deleteCharAt(sb.length() - 1);
            dfs(res, sb.append(Character.toLowerCase(c)), i + 1, s);
            sb.deleteCharAt(sb.length() - 1);
        } else {
            dfs(res, sb.append(c), i + 1, s);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    public int shortestSubarray(int[] A, int K) {
        int len = A.length, res =  Integer.MAX_VALUE;
        Deque<Integer> deque = new ArrayDeque<>();
        int[] preSum = new int[len + 1];
        for(int i = 0; i < len; i++){
            preSum[i + 1] = preSum[i] + A[i];
        }
        for(int i = 0; i <= len; i++){
            while (!deque.isEmpty() && preSum[deque.peekLast()] > preSum[i]) {
                deque.pollLast();
            }
            while(!deque.isEmpty() && preSum[i] - preSum[deque.peekFirst()] >= K){
                res = Math.min(res, i - deque.pollFirst());
            }
            deque.offerLast(i);
        }
        return res != Integer.MAX_VALUE ? res : -1;
    }
}
