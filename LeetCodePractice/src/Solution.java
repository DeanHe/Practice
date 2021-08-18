import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class Solution {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        //System.out.println(res);
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

    public int canBeTypedWords(String text, String brokenLetters) {
        String[] arr = text.split(" ");
        int cnt = 0;
        for (String s : arr) {
            for (char c : brokenLetters.toCharArray()) {
                if (s.contains(String.valueOf(c))) {
                    cnt++;
                    break;
                }
            }
        }
        return arr.length - cnt;
    }

    public int minStoneSum(int[] piles, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (int n : piles){
            pq.offer(n);
        }
        while(k > 0 && !pq.isEmpty()){
            int top = pq.poll();
            top -= top / 2;
            pq.offer(top);
            k--;
        }
        int sum = 0;
        while(!pq.isEmpty()){
            sum += pq.poll();
        }
        return sum;
    }

    public String minWindow(String s, String t) {
        String res = "";
        int i = 0, j = 0, slen = s.length(), tlen = t.length(), minLen = slen + 1;
        Map<Character, Integer> cnt = new HashMap<>();
        for(char c : t.toCharArray()){
            cnt.put(c, cnt.getOrDefault(c, 0) + 1);
        }
        int unique = cnt.size();
        while(j < slen){
            char c = s.charAt(j);
            if(cnt.containsKey(c)){
                cnt.put(c, cnt.get(c) - 1);
                if(cnt.get(c) == 0){
                    unique--;
                }
            }
            j++;
            while (unique == 0){
                if(j - i < minLen){
                    minLen = j - i;
                    res = s.substring(i, j);
                }
                char ic = s.charAt(i);
                if(cnt.containsKey(ic)){
                    cnt.put(ic, cnt.get(ic) + 1);
                    if(cnt.get(ic) == 1){
                        unique++;
                    }
                }
                i++;
            }
        }
        return res;
    }
}


