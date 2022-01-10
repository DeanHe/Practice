package trie;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShortestUniqueSubStringTest {
    private ShortestUniqueSubstring su;

    @Before
    public void setup() {
        su = new ShortestUniqueSubstring();
    }

    @Test
    public void testCase1() {
        String[] input = {"palantir", "pelantors","cheapair", "cheapoair"};
        String[] res = su.uniqueSubstrings(input);
        System.out.println(Arrays.toString(res));
    }
}
