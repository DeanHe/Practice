package String;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class PrintStarsGoogleTest {
    private PrintStarsGoogle ps;

    @Before
    public void setup() {
        ps = new PrintStarsGoogle();
    }

    @Test
    public void testCase1(){
        List<String> res = ps.printStar(3);
        for(String str : res){
            System.out.println(str);
        }
        Assert.assertEquals(5, res.size());
    }

    @Test
    public void testCase2(){
        List<String> res = ps.printStar(2);
        for(String str : res){
            System.out.println(str);
        }
        Assert.assertEquals(3, res.size());
    }
}
