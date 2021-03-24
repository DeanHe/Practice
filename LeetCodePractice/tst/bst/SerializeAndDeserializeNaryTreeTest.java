package bst;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SerializeAndDeserializeNaryTreeTest {
    private SerializeAndDeserializeNaryTree snt;


    @Before
    public void setup() {
        snt = new SerializeAndDeserializeNaryTree();
    }

    @Test
    public void testCase1(){
        NNode a = new NNode(1);
        NNode b = new NNode(2);
        NNode c = new NNode(3);
        NNode d = new NNode(4);
        NNode e = new NNode(5);
        NNode f = new NNode(6);
        NNode g = new NNode(7);
        NNode h = new NNode(8);
        NNode i = new NNode(9);
        NNode j = new NNode(10);
        NNode k = new NNode(11);

        a.children.add(b);
        a.children.add(c);
        a.children.add(d);

        b.children.add(e);
        b.children.add(f);

        f.children.add(k);

        d.children.add(g);
        d.children.add(h);
        d.children.add(i);
        d.children.add(j);

        String input = snt.serialize(a);
        System.out.println(input);

        NNode root = snt.deserialize(input);
        Assert.assertTrue(root != null);
    }
}
