import Contest.ClosestSubsequenceSum;
import DesignDataStructure.MinStackGeneric;
import Dijkstra.PathWithMinimumChange;


public class Solution {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        PathWithMinimumChange p = new PathWithMinimumChange();
        p.test();
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
    public int numDecodings(String s) {
        if(s == null || s.length() == 0){
            return 0;
        }
        int len = s.length();
        int[] dp = new int[len + 1];
        dp[0] = 1;
        dp[1] = s.charAt(0) == '0' ? 0 : 1;
        for(int i = 2; i <= len; i++){
           int lastOne = Integer.parseInt(s.substring(i - 1, i));
           int lastTwo = Integer.parseInt(s.substring(i - 2, i));
           if(lastOne >= 1 && lastOne <= 9){
               dp[i] += dp[i - 1];
           }
           if(lastTwo >= 10 && lastTwo <= 26){
               dp[i] += dp[i - 2];
           }
        }
        return dp[len];
    }
}
