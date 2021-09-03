import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

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
    public int[] answerQueries(ArrayList<int[]> queries, int N){
        int len = queries.size();
        int[] res = new int[len];
        TreeSet<Integer> treeSet = new TreeSet<>();
        for(int i = 0; i < len; i++){
            int[] q = queries.get(i);
            int op = q[0];
            int n = q[1];
            if(op == 1){
                // set
                treeSet.add(i);
                res[i] = 2;
            } else {
                // get
                if(treeSet.ceiling(i) != null){
                     res[i] = 2;
                } else {
                    res[i] = -1;
                }
            }
        }
        return res;
    }

    public List<int[]> aboveAverageSubarrays(int[] A){
        // O(N^2)
        List<int[]> res = new ArrayList<>();
        int len = A.length;
        int[] preSum = new int[len + 1];
        for(int i = 0; i < len; i++){
            preSum[i + 1] = A[i] + preSum[i];
        }
        for(int s = 0; s < len; s++){
            for(int e = s; e < len; e++){
                int subSum = preSum[e + 1] - preSum[s];
                double subAvg = subSum * 1.0 / (e - s + 1);
                int remainCnt = len - (e - s + 1);
                if(remainCnt == 0){
                    res.add(new int[]{s, e});
                } else {
                    double remainAvg = (preSum[len] - subSum) * 1.0 / remainCnt;
                    if(subAvg > remainAvg){
                        res.add(new int[]{s, e});
                    }
                }
            }
        }
        return res;
    }
}



