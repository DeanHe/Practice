import designDataStructure.iterators.PairIterator;

import java.util.*;

public class Solution {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Solution sol = new Solution();
        String[] input1 = {"3234.html", "xys.html", "7hsaa.html"};
        String[] input2 = {"3234.html", "sdhsfjdsh.html", "xys.html", "7hsaa.html"};
        System.out.println(sol.longestContinuousCommonHistory(input1, input2));
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

    public List<String> longestContinuousCommonHistory(String[] input1, String[] input2){
        List<String> res = new ArrayList<>();
        int[][] dp = new int[input1.length + 1][input2.length + 1];
        int most = 0, last1 = -1, last2 = -1;
        for(int i = 0; i < input1.length; i++){
            for(int j  = 0; j < input2.length; j++){
                if(input1[i].equals(input2[j])){
                    dp[i + 1][j + 1] = dp[i][j] + 1;
                    if(most < dp[i + 1][j + 1]){
                        most = dp[i + 1][j + 1];
                        last1 = i;
                        last2 = j;
                    }
                }
            }
        }
        while(last1 >= 0 && last2 >= 0 && input1[last1].equals(input2[last2])){
            res.add(0, input1[last1]);
            last1--;
            last2--;
        }
        return res;
    }

    public int[] sortEvenOdd(int[] nums) {
        int len = nums.length;
        int[] res = new int[len];
        List<Integer> odd = new ArrayList<>();
        List<Integer> even = new ArrayList<>();
        for(int i = 0; i < len; i++){
            if(i % 2 == 0){
                even.add(nums[i]);
            } else {
                odd.add(nums[i]);
            }
        }
        Collections.sort(even);
        Collections.sort(odd, Collections.reverseOrder());
        int o = 0, e = 0;
        for(int i = 0; i < len; i++){
            if(i % 2 == 0){
                res[i] = even.get(e++);
            } else {
                res[i] = odd.get(o++);
            }
        }
        return res;
    }

    public long smallestNumber(long num) {
        int[] cnt = new int[10];
        boolean positive = num >= 0;
        num = Math.abs(num);
        while(num > 0){
            int d = (int)(num % 10);
            cnt[d]++;
            num /= 10;
        }
        long res = 0;
        if(positive){
            for(int d = 1; d <= 9; d++){
                if(cnt[d] > 0){
                    cnt[d]--;
                    res = d;
                    break;
                }
            }
            for(int d = 0; d <= 9; d++){
                while(cnt[d] > 0){
                    res = res * 10 + d;
                    cnt[d]--;
                }
            }
            return res;
        } else {
            for(int d = 9; d >= 0; d--){
                while(cnt[d] > 0){
                    res = res * 10 + d;
                    cnt[d]--;
                }
            }
            return -res;
        }
    }

    public long coutPairs(int[] nums, int k) {
        int preCnt = 0;
        long res = 0;
        for(int i = 0; i < nums.length; i++){
            int n = nums[i];
            if(n % k == 0){
                if(i > 0){
                    res += i;
                }
                preCnt++;
            } else {
                res += preCnt;
            }
        }
        return res;
    }

    public int prefixCount(String[] words, String pref) {
        int res = 0;
        for(String w : words){
            if(w.startsWith(pref)){
                res++;
            }
        }
        return res;
    }

    public long minimalKSum(int[] nums, int k) {
        Arrays.sort(nums);
        long res = 0;
        int pre = 1;
        for(int n : nums){
            if(n == pre){
                continue;
            }
            int cnt = n - pre - 1;
            if(k > cnt){
                res += (pre + n) * cnt / 2;
                k -= cnt;
                pre = n;
            } else {
                break;
            }
        }
        if(k > 0){
            res += (pre + pre + k + 1) * k / 2;
        }
        return res;
    }
}



