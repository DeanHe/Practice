package trie;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class AutocompleteSystemTest {
    private AutocompleteSystem au;

    @Before
    public void setup() {
        String[] sentences = {
                "i love you",
                "island", "ironman",
                "i love leetcode"
        };
        int[] count = {5, 3, 2, 2};
        au = new AutocompleteSystem(sentences, count);
    }

    @Test
    public void testCase1() {
        List<String> res = new ArrayList<>();
        res = au.input('i');
        Assert.assertEquals(3, res.size());
        res = au.input(' ');
        Assert.assertEquals(2, res.size());
        res = au.input('a');
        Assert.assertEquals(0, res.size());
        res = au.input('#');
        Assert.assertEquals(0, res.size());
        res = au.input('i');
        Assert.assertFalse(res.contains("i a"));
    }
}
