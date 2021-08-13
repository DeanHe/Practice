package bst;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FlipBinaryTreeLeafFacebookTest {
    private FlipBinaryTreeLeafFacebook fb;

    @Before
    public void setup() {
        fb = new FlipBinaryTreeLeafFacebook();
    }

    @Test
    public void testCase1(){
/*
          1
         1 1
*/
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(1);
        root.right = new TreeNode(1);
        TreeNode res = fb.flipLeaf(root, root.left);
        Assert.assertEquals(0, res.val);
    }

    @Test
    public void testCase2(){
/*
Explanation：
       1
      / \
    1   1
   / \
 0   0
*/
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(1);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(0);
        root.left.right = new TreeNode(0);
        TreeNode res = fb.flipLeaf(root, root.left.right);
        Assert.assertEquals(0, res.val);
    }

    @Test
    public void testCase3(){
/*
Explanation：
       0
      / \
     0   1
*/
        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(0);
        root.right = new TreeNode(1);
        TreeNode res = fb.flipLeaf(root, root.left);
        Assert.assertEquals(1, res.val);
    }
}
