package UnionFind;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class VerifyOddEvenQueryGoogleTest {
    private VerifyOddEvenQueryGoogle vo;

    @Before
    public void setup() {
        vo = new VerifyOddEvenQueryGoogle();
    }

    @Test
    public void testCase1(){
        Assert.assertTrue(vo.verifyQueries(new String[]{ "2", "14", "odd" }));
        Assert.assertTrue(vo.verifyQueries(new String[]{ "8", "14", "even" }));
        Assert.assertFalse(vo.verifyQueries(new String[]{ "2", "7", "even" }));
    }

    @Test
    public void testCase2(){
        Assert.assertTrue(vo.verifyQueries(new String[]{ "2", "14", "odd" }));
        Assert.assertTrue(vo.verifyQueries(new String[]{ "8", "14", "odd" }));
        Assert.assertTrue(vo.verifyQueries(new String[]{ "2", "7", "even" }));
    }

    @Test
    public void testCase3(){
        Assert.assertTrue(vo.verifyQueries(new String[]{ "2", "14", "even" }));
        Assert.assertTrue(vo.verifyQueries(new String[]{ "2", "5", "odd" }));
        Assert.assertTrue(vo.verifyQueries(new String[]{ "6", "14", "odd" }));
    }

    @Test
    public void testCase4(){
        Assert.assertTrue(vo.verifyQueries(new String[]{ "2", "8", "even" }));
        Assert.assertTrue(vo.verifyQueries(new String[]{ "9", "15", "odd" }));
        Assert.assertTrue(vo.verifyQueries(new String[]{ "6", "15", "even" }));
        Assert.assertTrue(vo.verifyQueries(new String[]{ "2", "5", "odd" }));
    }

}
