import bst.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;
import java.util.TreeSet;


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

    public int maxSumSubmatrix(int[][] matrix, int k) {
        boolean isRowLarger = false;
        int rows = matrix.length;
        int cols = matrix[0].length;
        if(rows > cols){
            isRowLarger = true;
        }
        int res = Integer.MIN_VALUE;
        int m = Math.min(rows, cols);
        int n = Math.max(rows, cols);
        for(int c1 = 0; c1 < cols; c1++){
            int[] arr = new int[rows];
            for(int c2 = c1; c2 < cols; c2++){
                for(int r = 0; r < rows; r++){
                    arr[r] += matrix[r][c2];
                }
                res = Math.max(res, helper(arr, k));
            }
        }
        return res;
    }

    private int helper(int[] preSum, int target){
        TreeSet<Integer> set = new TreeSet<>();
        set.add(0);
        int sum = 0, res = Integer.MIN_VALUE;
        for(int n : preSum){
            sum += n;
            Integer ceiling = set.ceiling(sum - target);
            if(ceiling != null){
                res = Math.max(res, sum - ceiling);
            }
            set.add(sum);
        }
        return res;
    }

    public int[] buildArray(int[] nums) {
        int len = nums.length;
        int[] res = new int[len];
        for(int i = 0; i < len; i++){
            res[i] = nums[nums[i]];
        }
        return res;
    }

}


