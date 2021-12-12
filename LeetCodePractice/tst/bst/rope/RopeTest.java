package bst.rope;

import bst.TreeNode;
import bst.lca.LowestCommonAncestorOfaBinaryTreeII;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RopeTest {


    @Before
    public void setup() {

    }

    @Test
    public void testCase1(){
        /*

                     9  (total 22)
                   /       \
                 6                6
               /   \            /     \
              6    3           2        1
         Hello_  my_          / \      / \
                             2   4    1   6
                           na  me_i  s   _simon
         */
        Rope leaf1 = new Rope("Hello ");
        Rope leaf2 = new Rope("my ");
        Rope lvl11 = leaf1.concat(leaf2);
        Rope leaf3 = new Rope("na");
        Rope leaf4 = new Rope("me i");
        Rope lvl12 = leaf3.concat(leaf4);
        Rope leaf5 = new Rope("s");
        Rope leaf6 = new Rope(" simon");
        Rope lvl13 = leaf5.concat(leaf6);
        Rope lvl21 = lvl12.concat(lvl13);
        Rope lvl31 = lvl11.concat(lvl21);
        System.out.println(lvl31.getLength());
        String res = lvl31.subString(2, 10);
        System.out.println(res);
    }
}
