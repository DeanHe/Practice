package trie;

/*
Example 1:

Input: s = "abcde", words = ["a","bb","acd","ace"]
Output: 3
Explanation: There are three strings in words that are a subsequence of s: "a", "acd", "ace".
Example 2:

Input: s = "dsahjpjauf", words = ["ahjpjau","ja","ahbwzgqnuk","tnmlanowax"]
Output: 2
 */

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class NumberOfMatchingSubsequenceGoogleTest {
    private NumberOfMatchingSubsequencesGoogle nm;

    @Before
    public void setup() {
        nm = new NumberOfMatchingSubsequencesGoogle();
    }

    @Test
    public void testCase1() {
        String s = "abcde";
        String[] words = {"a", "bb", "acd", "ace"};
        List<String> res = nm.numMatchingSubseq(s, words);
        Assert.assertEquals(Arrays.asList("a", "acd", "ace"), res);
    }

    @Test
    public void testCase2() {
        String s = "dsahjpjauf";
        String[] words = {"ahjpjau", "ja", "ahbwzgqnuk", "tnmlanowax"};
        List<String> res = nm.numMatchingSubseq(s, words);
        Assert.assertEquals(Arrays.asList("ja", "ahjpjau"), res);
    }

    @Test
    public void testCase3() {
        String s = "btovxbkumc";
        String[] words = {"btovxbku", "to", "zueoxxxjme", "yjkclbkbtl"};
        List<String> res = nm.numMatchingSubseq(s, words);
        Assert.assertEquals(Arrays.asList("to", "btovxbku"), res);
    }
}
