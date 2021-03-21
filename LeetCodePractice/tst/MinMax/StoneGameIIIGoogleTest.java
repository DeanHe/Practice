package MinMax;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StoneGameIIIGoogleTest {
    private StoneGameIIIGoogle sg;

    @Before
    public void setup() {
        sg = new StoneGameIIIGoogle();
    }

    @Test
    public void testCase1(){
        int[] input = {3, 3, 3, 3, 100};
        int score = sg.stoneGameIII(input);
        Assert.assertEquals(score, 103);
    }
}
