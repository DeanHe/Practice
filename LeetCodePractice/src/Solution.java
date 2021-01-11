import tiktok.FindMaximumSummationOfSubarray;
import tiktok.IncrementalMemoryLeak;
import tiktok.MinimumCharacterTransformation;
import tiktok.RealProgrammerGame;
import tiktok.SelectPairs;
import tiktok.VersionControl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


public class Solution {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        SelectPairs f = new SelectPairs();
        int[] arr = {2, 7, 4, 1, 8, 1};
        int res = f.calculate(arr);
        System.out.println(res);
        /*
        OneDmineSweep oneDmineSweep = new OneDmineSweep();
        int[] S = {2, 6, 5, 6, 6, 6, 6, 6, 6, 6, 1};

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

    public double findMedianSortedArrays(int A[], int B[]) {
        int len = A.length + B.length;
        if (len % 2 == 0) {
            // even
            return (findKth(A, 0, B, 0, len / 2) + findKth(A, 0, B, 0, len / 2 + 1)) / 2.0;
        } else {
            // odd
            return findKth(A, 0, B, 0, len / 2 + 1);
        }
    }

    private double findKth(int[] A, int as, int[] B, int bs, int K) {
        int alen = A.length, blen = B.length;
        if(as >= alen){
            return B[bs + K - 1];
        } 
        if(bs >= blen){
            return A[as + K - 1];
        }
        if(K == 1){
            return Math.min(A[as], B[bs]);
        }
        if(as + K / 2 - 1 < alen && bs + K / 2 - 1 < blen){
            int ca = A[as + K / 2 - 1];
            int cb = B[bs + K / 2 - 1];
            if(ca < cb){
                return findKth(A, as + K / 2, B, bs, K - K / 2);
            } else {
                return findKth(A, as, B, bs + K / 2, K - K / 2);
            }
        } else if(as + K / 2 - 1 < alen && bs + K / 2 - 1 >= blen){
            return findKth(A, as + K / 2, B, bs, K - K / 2);
        } else {
            return findKth(A, as, B, bs + K / 2, K - K / 2);
        }
    }
}
