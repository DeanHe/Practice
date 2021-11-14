package dp;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class EatPillsGoogleTest {
    private EatPillsGoogle ep;

    @Before
    public void setup() {
        ep = new EatPillsGoogle();
    }

    @Test
    public void testCase1() {
        ep.print();
    }
}
