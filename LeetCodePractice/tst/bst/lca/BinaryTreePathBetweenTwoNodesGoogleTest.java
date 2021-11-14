package bst.lca;

import bst.TreeNode;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class BinaryTreePathBetweenTwoNodesGoogleTest {
    private BinaryTreePathBetweenTwoNodesGoogle bt;
    private TreeNode root;

    @Before
    public void setup() {
        bt = new BinaryTreePathBetweenTwoNodesGoogle();
    }

    @Test
    public void testCase1(){
        /*
           1
         2   3
        4 5 6 7
         */
        root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        List<Integer> ls = bt.getPath(root, root.left.left, root.right.left);
        Assert.assertTrue(ls.equals(Arrays.asList(4, 2, 1, 3, 6)));
    }
}
