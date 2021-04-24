package bfs;

import org.junit.Before;
import org.junit.Test;
import org.testng.Assert;

import java.util.List;

public class AlienDictionaryGoogleTest {
    private AlienDictionaryGoogle adg;
    @Before
    public void setup() {
        adg = new AlienDictionaryGoogle();
    }

    @Test
    public void testCase1(){
        String[] words = {
                "wrt",
                "wrf",
                "er",
                "ett",
                "rftt"
        };
        List<String> res = adg.alienOrder(words);
        System.out.println(res);
        Assert.assertEquals(1, res.size());
        Assert.assertEquals("wertf", res.get(0));
    }

    @Test
    public void testCase2(){
        String[] words = {
                "d",
                "ca",
                "cb",
                "cc",
        };
        List<String> res = adg.alienOrder(words);
        System.out.println(res);
        Assert.assertTrue(!res.isEmpty());
    }
}
