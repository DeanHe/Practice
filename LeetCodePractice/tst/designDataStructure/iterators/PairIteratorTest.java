package designDataStructure.iterators;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/*
Given a string iterator, implement a pair iterator to combine the adjacent string with same value.

Example1

Input: v1 = [foo, foo, bar, foo]
Output: {foo, 2}, {bar, 1}, {foo, 1}
 */
public class PairIteratorTest {
    private PairIterator pi;

    @Before
    public void setup() {
        List<String> ls = Arrays.asList("foo", "foo", "bar","tac", "tac", "tac", "foo", "foo");
        pi = new PairIterator(ls.iterator());
    }

    @Test
    public void testCase1(){
        while(pi.hasNext()){
            System.out.println(pi.next());
        }
        Assert.assertFalse(pi.hasNext());
    }
}
