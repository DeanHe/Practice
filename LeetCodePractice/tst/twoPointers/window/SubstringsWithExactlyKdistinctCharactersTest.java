package twoPointers.window;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SubstringsWithExactlyKdistinctCharactersTest {
    private SubstringsWithExactlyKdistinctCharacters sw;

    @Before
    public void setup() {
        sw = new SubstringsWithExactlyKdistinctCharacters();
    }

    @Test
    public void testCase1(){
        Assert.assertEquals(2, sw.countkDist("abc", 2));
        Assert.assertEquals(3, sw.countkDist("aba", 2));
        Assert.assertEquals(3, sw.countkDist("aa", 1));
    }
}
