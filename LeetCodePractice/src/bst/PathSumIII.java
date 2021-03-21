package bst;


//Find the number of paths that sum to a given value.
//The path does not need to start or end at the root or a leaf, 
//but it must go downwards (traveling only from parent nodes to child nodes).
public class PathSumIII {

	public int pathSum(TreeNode root, int sum) {
        if(root == null){
            return 0;
        }
        int res = dfs(root, sum) + pathSum(root.left, sum) + pathSum(root.right, sum);
        return res;
    }
    
    private int dfs(TreeNode root, int sum){
        if(root == null){
            return 0;
        }
        int res = 0;
        if(root.val == sum){
            res += 1;
        } 
        res += dfs(root.left, sum - root.val) + dfs(root.right, sum - root.val);
        return res;
    }
}
