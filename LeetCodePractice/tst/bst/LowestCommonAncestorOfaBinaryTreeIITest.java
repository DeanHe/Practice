package bst;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LowestCommonAncestorOfaBinaryTreeIITest {
    private LowestCommonAncestorOfaBinaryTreeII lca;
    private TreeNode root;

    @Before
    public void setup() {
        lca = new LowestCommonAncestorOfaBinaryTreeII();
    }

    @Test
    public void testCase1(){
        /*
          1
         2 3
        4 5
         */
        root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        Assert.assertEquals(root, lca.lowestCommonAncestor(root, 4, 3));
        Assert.assertEquals(root.left, lca.lowestCommonAncestor(root, 5, 2));
        Assert.assertEquals(root.left, lca.lowestCommonAncestor(root, 5, 4));
        Assert.assertNull(lca.lowestCommonAncestor(root, 5, 10));
        Assert.assertNull(lca.lowestCommonAncestor(root, 7, 3));
    }
}
