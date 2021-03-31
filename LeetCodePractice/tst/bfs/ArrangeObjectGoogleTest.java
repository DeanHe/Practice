package bfs;

import org.junit.Before;
import org.junit.Test;
import org.testng.Assert;

import java.util.Arrays;
import java.util.List;

public class ArrangeObjectGoogleTest {
    private ArrangeObjectGoogle aa;
    @Before
    public void setup() {
        aa = new ArrangeObjectGoogle();
    }

    @Test
    public void testCase1(){
        List<String[]> rules = Arrays.asList(
                new String[]{ "1", "3", "right" },
                new String[]{ "2", "3", "right" },
                new String[]{ "1", "2", "up" },
                new String[]{ "1", "2", "left" },
                new String[]{ "2", "3", "up" }
        );
        int[][] res = aa.arange(3, rules);
        for(int[] arr : res){
            System.out.println(Arrays.toString(arr));
        }
        Assert.assertTrue(res.length > 0);
    }
}
