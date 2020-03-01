import java.util.*;

import Backtracking.BraceExpansion;
import UnionFind.TheEarliestMomentWhenEveryoneBecomeFriends;

public class Solution {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		int[]   in = {4, 8, 10, 12, 14, 20, 2};
//		int[] level = {5,4,8,1,7,2,6,3};
//		ConstructBinarySearchTreeFromLevelOrderTraversals sol = new ConstructBinarySearchTreeFromLevelOrderTraversals();
//		TreeNode root = sol.constructTree(level);
//		System.out.println(root.val);

/*		TheEarliestMomentWhenEveryoneBecomeFriends mie = new TheEarliestMomentWhenEveryoneBecomeFriends();
		int[][] events = {{8,9,5}, {9,12,4}, {12,14,2}, {8,12,7}, {13,16,3}, {14,15,4}};
		int[][] logs = {{20190101,0,1}, {20190104,3,4}, {20190107,2,3}, {20190211,1,5}, {20190224,2,4}, {20190301,0,3}, {20190312,1,2}, {20190322,4,5}};
		int res = mie.earliestAcq(logs, 6);*/

		BraceExpansion be = new BraceExpansion();
		String input = "{a,b,c}d{e,f}";
		String[] res = be.expand(input);
		int x = 0;
		Arrays.stream(res).forEach(a -> System.out.println(a));


	}

	public int[] smallerNumbersThanCurrent(int[] nums) {
		int len = nums.length;
		int[] res = Arrays.copyOf(nums, len);
		Arrays.sort(nums);
		Map<Integer, Integer> orderMap = new HashMap<>();
		for(int i = 0; i < len; i++){
			orderMap.putIfAbsent(nums[i], i);

		}
		for(int i = 0; i < len; i++){
			int cur = res[i];
			res[i] = orderMap.get(cur);
		}
		return res;
	}

	public String rankTeams(String[] votes) {
		int len = votes[0].length();
		Map<Character, int[]> rankMap = new HashMap<>();
		for(String vote : votes){
			char[] arr = vote.toCharArray();
			for(int i = 0; i < len; i++){
				char c = arr[i];
				int[] c_rank = rankMap.getOrDefault(c, new int[len]);
				c_rank[i]++;
				rankMap.put(c, c_rank);
			}
		}
		List<Character> list = new ArrayList<>(rankMap.keySet());
		Collections.sort(list, (a, b) -> {
			for(int i = 0; i < len; i++){
				if(rankMap.get(a)[i] != rankMap.get(b)[i]){
					return rankMap.get(b)[i] - rankMap.get(a)[i];
				}
			}
			return a - b;
		});
		StringBuilder sb = new StringBuilder();
		for(char c : list){
			sb.append(c);
		}
		return sb.toString();
	}
}
