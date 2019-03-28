package BST;

/*    
 * Count[2] = Count[0] * Count[1]   (1 as root)
+ Count[1] * Count[0]  (2 as root)

take input 3 as example：
Count[3] = Count[0]*Count[2]  (1 as root)
+ Count[1]*Count[1]  (2 as root)
+ Count[2]*Count[0]  (3 as root)

the dp tranfer function is
Count[i] = ∑ Count[0...k] * [ k+1....i]     0<= k < i-1
when array is 1，2，3，4，.. i，.. n，the built BST has property below：
when the tree root with i，the left subtree is built from [0, i-1]， the right subtree is built from [i + 1, n]
*/
public class UniqueBinarySearchTrees {
	/**
     * @paramn n: An integer
     * @return: An integer
     */
    public int numTrees(int n) {
    	if(n < 0){
    		return 0;
    	}
    	if(n == 0 || n == 1){
    		return 1;
    	}
    	int[] dp = new int[n + 1];
    	dp[0] = 1;
    	dp[1] = 1;
    	for(int i = 2; i <= n; i++){
    		for(int j = 0; j < i; j++){
    			dp[i] += dp[j] * dp[i - 1 - j];
    		}
    	}
    	return dp[n];
    }
}
