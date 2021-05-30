import java.util.Arrays;
import java.util.PriorityQueue;


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

    public String maxValue(String n, int x) {
        StringBuilder sb = new StringBuilder(n);
        int i;
        if(sb.charAt(0) == '-'){
            for(i = 1; i < sb.length(); i++){
                if(sb.charAt(i) - '0' > x){
                    sb.insert(i, x);
                    break;
                }
            }
        } else {
            for(i = 0; i < sb.length(); i++){
                if(sb.charAt(i) - '0' < x){
                    sb.insert(i, x);
                    break;
                }
            }
        }
        if(i == sb.length()){
            sb.insert(i, x);
        }
        return sb.toString();
    }

    /*
    [3,3,2]
[1,2,3,2,1,2]
[5,1,4,3,2]
[2,1,2,4,5,2,1]
Your answer
[2,0,1,2,0,2]
[1,4,3,2,0,4,1]
     */
    public int[] assignTasks(int[] servers, int[] tasks) {
        int slen = servers.length, tlen = tasks.length;
        for (int i = 0; i < tlen; i++) {
            tasks[i] += i;
        }
        int[][] ls = new int[slen][3];
        for (int i = 0; i < slen; i++) {
            ls[i][0] = i;
            ls[i][1] = servers[i];
        }
        Arrays.sort(ls, (a, b) -> a[1] - b[1]);
        int[] res = new int[tlen];
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            // server idx : weight : free Time
            if (a[2] != b[2]) {
                return a[0] - b[0];
            } else if (a[1] != b[1]) {
                return a[1] - b[1];
            } else {
                return a[0] - b[0];
            }
        });
        int i = 0;
        for (int t = 0; t < tlen; t++) {
            if (!pq.isEmpty() && pq.peek()[2] <= t) {
                int[] cur = pq.poll();
                res[t] = cur[0];
                cur[2] = tasks[i];
                pq.offer(cur);
            } else {
                res[t] = ls[i][0];
                ls[i][2] = tasks[t];
                pq.offer(ls[i++]);
            }
        }
        return res;
    }
}


