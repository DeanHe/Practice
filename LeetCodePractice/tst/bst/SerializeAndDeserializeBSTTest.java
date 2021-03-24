package bst;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SerializeAndDeserializeBSTTest {
    private SerializeAndDeserializeBST bst;


    @Before
    public void setup() {
        bst = new SerializeAndDeserializeBST();
    }

    @Test
    public void testCase1(){
        TreeNode a = new TreeNode(2);
        TreeNode b = new TreeNode(1);
        TreeNode c = new TreeNode(3);

        a.left = b;
        a.right = c;

        String str = bst.serialize(a);
        System.out.println(str);

        TreeNode root = bst.deserialize(str);
        Assert.assertTrue(root != null);
    }

    @Test
    public void testCase2(){
        TreeNode a = new TreeNode(1);
        TreeNode b = new TreeNode(3);
        TreeNode c = new TreeNode(2);

        a.right = b;
        b.left = c;

        String str = bst.serialize(a);
        System.out.println(str);

        TreeNode root = bst.deserialize(str);
        Assert.assertTrue(root != null);
    }
}
