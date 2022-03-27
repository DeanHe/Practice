package bst.iterator;

import bst.SerializeAndDeserializeBinaryTree;
import bst.TreeNode;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

/*
implement a decoding tree iterator
the decoding tree node looks like:
          ()
         / | \
    (A)  (B " ")  (C)
    /|\               \
(G) (H "l") (F)      (D "o")
 |
(E "he")

input: "AGEHHDBD"
print "hello o"

tag: Google
 */
public class DecodingTreeIteratorTest {
    private DecodingTreeIterator dt;


    @Before
    public void setup() {

    }

    @Test
    public void testCase1(){
        String input = "AGEHHDBD";

        DecodingTreeNode root = new DecodingTreeNode("_");
        DecodingTreeNode a = new DecodingTreeNode("A");
        DecodingTreeNode b = new DecodingTreeNode("B");
        DecodingTreeNode c = new DecodingTreeNode("C");
        DecodingTreeNode d = new DecodingTreeNode("D");
        DecodingTreeNode e = new DecodingTreeNode("E");
        DecodingTreeNode f = new DecodingTreeNode("F");
        DecodingTreeNode g = new DecodingTreeNode("G");
        DecodingTreeNode h = new DecodingTreeNode("H");
        b.value = " ";
        d.value = "o";
        h.value = "l";
        e.value = "he";
        root.children.add(a);
        root.children.add(b);
        root.children.add(c);
        a.children.add(g);
        a.children.add(h);
        a.children.add(f);
        g.children.add(e);
        c.children.add(d);

        dt = new DecodingTreeIterator(input, root);
        StringBuilder sb = new StringBuilder();
        while(dt.hasNext()){
            sb.append(dt.next());
        }
        System.out.println(sb.toString());
    }
}
