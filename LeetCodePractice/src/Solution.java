import java.util.*;

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
    public int[] getOrder(int[][] tasks) {
        int len = tasks.length;
        int[][] ls = new int[len][3];
        for (int i = 0; i < len; i++) {
            ls[i][0] = i;
            ls[i][1] = tasks[i][0];
            ls[i][2] = tasks[i][1];
        }
        Arrays.sort(ls, (a, b) -> a[1] - b[1]);
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            if (a[2] != b[2]) {
                return a[2] - b[2];
            } else {
                return a[0] - b[0];
            }
        });
        int[] res = new int[len];
        int i = 0;
        int j = 0;
        int endTime = ls[j][1];
        while(i < len){
            while(j < len && ls[j][1] <= endTime){
                pq.offer(ls[j]);
                j++;
            }
            if(pq.isEmpty()){
                endTime = ls[j][1];
            } else {
                int[] task = pq.poll();
                res[i] = task[0];
                endTime += task[2];
            }
        }
        return res;
    }

    public List<Integer> twoOutOfThree(int[] nums1, int[] nums2, int[] nums3) {
        List<Integer> res = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        for(int n : Arrays.stream(nums1).distinct().toArray()){
            map.put(n, map.getOrDefault(n, 0) + 1);
        }
        for(int n : Arrays.stream(nums2).distinct().toArray()){
            map.put(n, map.getOrDefault(n, 0) + 1);
        }
        for(int n : Arrays.stream(nums3).distinct().toArray()){
            map.put(n, map.getOrDefault(n, 0) + 1);
        }
        for(int n : map.keySet()){
            if(map.get(n) >= 2){
                res.add(n);
            }
        }
        return res;
    }

    public boolean areNumbersAscending(String s) {
        int pre = 0;
        for(String str : s.split(" ")){
            try {
               int cur = Integer.parseInt(str);
               if(cur <= pre){
                   return false;
               }
               pre = cur;
            } catch (NumberFormatException e) {

            }
        }
        return true;
    }

    Set<Character> vowels = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u'));
    public long countVowels(String word) {
        int len = word.length();
        long res = 0;
        long[] dp = new long[len + 1];
        for(int i = 0; i < len; i++){
            char c = word.charAt(i);
            if(vowels.contains(c)){
                dp[i + 1] = dp[i] + i + 1;
            } else {
                dp[i + 1] = dp[i];
            }
            res += dp[i + 1];
        }
        return res;
    }

    private boolean isVowelSub(String s){
        Set<Character> visited = new HashSet<>();
        for(char c : s.toCharArray()){
            if(!vowels.contains(c)){
                return false;
            }
            visited.add(c);
        }
        return visited.size() == 5;
    }
}



