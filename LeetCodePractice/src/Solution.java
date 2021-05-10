import LinkedList.ListNode;
import bst.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;


public class Solution {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        /*
        int[][] events = {{1, 0, 1, 1, 1}, {1, 0, 1, 1, 1}, {0, 1, 0, 1, 1}};
        LargestPerimeterIsland largestIsland = new LargestPerimeterIsland();
        int res = largestIsland.largestPerimeter(events);
        System.out.println(res);

		string x = "/abcd/erf";
		string[] xm = x.split("/", 1);
		RearrangeStringkDistanceApart rearrangeStringkDistanceApart = new RearrangeStringkDistanceApart();
		string res = rearrangeStringkDistanceApart.rearrangeString("aabbcc", 3);
		System.out.println(res);

		string X = "XMJYAUZ";
		string Y = "XMJAATZ";

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


		string[] sentences = {"i love you", "island","ironman", "i love leetcode"};
		AutocompleteSystem cs = new AutocompleteSystem(sentences, cnt);
		string test = "i a#";
		for(char c : test.toCharArray()){
			List<string> res = cs.input(c);
			System.out.println(res);
		}
		for(char c : test.toCharArray()){
			List<string> res = cs.input(c);
			System.out.println(res);
		}

     	BraceExpansion be = new BraceExpansion();
		string input = "{a,b,c}d{e,f}";
		string[] res = be.expand(input);
		int x = 0;
		Arrays.stream(res).forEach(a -> System.out.println(a));
		*/
    }

    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> res = new ArrayList<>();
        if(s == null || s.length() == 0 || words == null || words.length == 0){
            return res;
        }
        int singleLen = words[0].length();
        int wordsLen = singleLen * words.length;
        Map<String, Integer> wmap = new HashMap<>();
        for(String w : words){
            wmap.put(w, wmap.getOrDefault(w, 0) + 1);
        }
        HashMap<String, Integer>[] smap = new HashMap[singleLen];
        for(int i = 0; i < singleLen; i++){
            smap[i] = new HashMap<>();
        }
        for(int i = 0; i + singleLen <= s.length(); i++){
            int idx = i % singleLen;
            String sub = s.substring(i, i + singleLen);
            smap[idx].put(sub, smap[idx].getOrDefault(sub, 0) + 1);
            if(i >= wordsLen){
                String head = s.substring(i - wordsLen, i - wordsLen + singleLen);
                smap[idx].put(head, smap[idx].get(head) - 1);
                if(smap[idx].get(head) == 0){
                    smap[idx].remove(head);
                }
            }
            if(wmap.equals(smap[idx])){
                res.add(i - wordsLen + singleLen);
            }
        }
        return res;
    }
}


