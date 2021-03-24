package bst;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SerializeAndDeserializeBinaryTreeTest {
    private SerializeAndDeserializeBinaryTree sdt;


    @Before
    public void setup() {
        sdt = new SerializeAndDeserializeBinaryTree();
    }

    @Test
    public void testCase1(){
        TreeNode a = new TreeNode(2);
        TreeNode b = new TreeNode(1);
        TreeNode c = new TreeNode(3);

        a.left = b;
        a.right = c;

        String str = sdt.serialize(a);
        System.out.println(str);

        TreeNode root = sdt.deserialize(str);
        Assert.assertTrue(root != null);
    }
}
