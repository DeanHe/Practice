import Array.CountTripletOfAscending;
import Backtracking.PartitionToKEqualSumSubsetsII;
import BinarySearch.FindKthMissingNumber;
import DFS.DecompressByteArray;
import DFS.GenerateRandomMaze;
import DFS.ReorderRoutesToMakeAllPathsLeadToTheCityZero;
import DP.ImplementDiff;
import Scheduling.RearrangeStringkDistanceApart;
import SweepLine.JobSchedule;
import Trie.AutocompleteSystem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;


public class Solution {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RearrangeStringkDistanceApart rearrangeStringkDistanceApart = new RearrangeStringkDistanceApart();
		String res = rearrangeStringkDistanceApart.rearrangeString("aabbcc", 3);
		System.out.println(res);
/*
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
*/

		/*
		// test for gossip
		int[] move = {3,-4,6,-3};
		GraphNode n0 = new GraphNode(0);
		GraphNode n1 = new GraphNode(1);
		GraphNode n2 = new GraphNode(2);
		GraphNode n3 = new GraphNode(3);
		GraphNode n4 = new GraphNode(4);
		n0.neighbors.add(n1);
		n0.neighbors.add(n2);
		n1.neighbors.add(n0);
		n1.neighbors.add(n3);
		n2.neighbors.add(n0);
		n2.neighbors.add(n4);
		n3.neighbors.add(n1);
		n4.neighbors.add(n2);
		//  n4 - n2 - n0 - n1 -n3
		Message msg = new Message(123);
		n3.send(msg);
		System.out.println(n3.canReach);

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
		}*/

/*		BraceExpansion be = new BraceExpansion();
		String input = "{a,b,c}d{e,f}";
		String[] res = be.expand(input);
		int x = 0;
		Arrays.stream(res).forEach(a -> System.out.println(a));*/


	}
}
