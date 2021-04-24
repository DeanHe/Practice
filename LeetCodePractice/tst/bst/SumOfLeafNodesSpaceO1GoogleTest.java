package bst;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SumOfLeafNodesSpaceO1GoogleTest {
    private SumOfLeafNodesSpaceO1Google sol;

    @Before
    public void setup() {
        sol = new SumOfLeafNodesSpaceO1Google();
    }

    @Test
    public void testCase1(){
/*
          1
         2 3
        4 5
*/
        TreeNodeWithParent root = new TreeNodeWithParent(1);
        root.left = new TreeNodeWithParent(2);
        root.left.parent = root;
        root.right = new TreeNodeWithParent(3);
        root.right.parent = root;
        root.left.left = new TreeNodeWithParent(4);
        root.left.left.parent = root.left;
        root.left.right = new TreeNodeWithParent(5);
        root.left.right.parent = root.left;
        Assert.assertEquals(12, sol.sumLeafNode(root));
    }

    @Test
    public void testCase2(){
/*
Explanation：
      12
      / \
    3   7
   / \
 4   5
/
2
2+5+7 = 14
*/
        TreeNodeWithParent root = new TreeNodeWithParent(12);
        root.left = new TreeNodeWithParent(3);
        root.left.parent = root;
        root.right = new TreeNodeWithParent(7);
        root.right.parent = root;
        root.left.left = new TreeNodeWithParent(4);
        root.left.left.parent = root.left;
        root.left.right = new TreeNodeWithParent(5);
        root.left.right.parent = root.left;
        root.left.left.left = new TreeNodeWithParent(2);
        root.left.left.left.parent = root.left.left;
        Assert.assertEquals(14, sol.sumLeafNode(root));
    }

    @Test
    public void testCase3(){
/*
Explanation：
      12
      / \
    3   7
       / \
      4   9
           \
            2
3+4+2 = 9
*/
        TreeNodeWithParent root = new TreeNodeWithParent(12);
        root.left = new TreeNodeWithParent(3);
        root.left.parent = root;
        root.right = new TreeNodeWithParent(7);
        root.right.parent = root;
        root.right.left = new TreeNodeWithParent(4);
        root.right.left.parent = root.right;
        root.right.right = new TreeNodeWithParent(9);
        root.right.right.parent = root.right;
        root.right.right.right = new TreeNodeWithParent(2);
        root.right.right.right.parent = root.right.right;
        Assert.assertEquals(9, sol.sumLeafNode(root));
    }
}
