package bst;

/*Given a list of numbers, construct a bst from it(you need to insert nodes one-by-one with the given order to get the bst) and find the distance between two given nodes.

Example
input:
numbers = [2,1,3]
node1 = 1
node2 = 3
output:
2
Notice
If two nodes do not appear in the bst, return -1
We guarantee that there are no duplicate nodes in bst
The node distance means the number of edges between two nodes*/
public class BSTNodeDistance {
	/**
     * @param numbers: the given list
     * @param node1: the given node1
     * @param node2: the given node2
     * @return: the distance between two nodes
     */
    public int bstDistance(int[] numbers, int node1, int node2) {
        // Write your code here
        if(numbers == null || numbers.length == 0){
            return -1;
        }
        int result = 0;
        boolean exist1 = false;
        boolean exist2 = false;
        
        TreeNode root = new TreeNode(numbers[0]);
        if(numbers[0] == node1){
            exist1 = true;
        }
        if(numbers[0] == node2){
            exist2 = true;
        }
        for(int i = 1; i < numbers.length; i++){
            if(numbers[i] == node1){
                exist1 = true;
            }
            if(numbers[i] == node2){
                exist2 = true;
            }
            constructBST(root, numbers[i]);
        }
        if(!(exist1 && exist2)){
            return -1;
        }
        TreeNode LCA = findLCA(root, node1, node2);
        result = findDistance(LCA, node1) + findDistance(LCA, node2);
        return result;
        
    }
    private void constructBST(TreeNode root, int val){
        if(val < root.val){
            if(root.left == null){
                root.left = new TreeNode(val);
            } else {
                constructBST(root.left, val);
            }
        } else if(val > root.val){
            if(root.right == null){
                root.right = new TreeNode(val);
            } else {
                constructBST(root.right, val);
            }
        }
    }
    private TreeNode findLCA(TreeNode root, int node1, int node2){
        if(node1 < root.val && node2 < root.val){
            return findLCA(root.left, node1, node2);
        } else if(node1 > root.val && node2 > root.val){
            return findLCA(root.right, node1, node2);
        } else {
            return root;
        }
    }
    private int findDistance(TreeNode root, int node){
        if(root.val == node){
            return 0;
        } else if(root.val > node){
            return 1 + findDistance(root.left, node);
        } else {
            return 1 + findDistance(root.right, node);
        }
    }
}