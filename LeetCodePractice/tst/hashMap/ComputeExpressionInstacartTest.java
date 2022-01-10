package hashMap;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

public class ComputeExpressionInstacartTest {
    private ComputeExpressionInstacart ce;

    @Before
    public void setup() {
        ce = new ComputeExpressionInstacart();
    }

    @Test
    public void testCase1() {
        String[] input = {
                "T1 = 1",
                "T2 = 2",
                "T3 = T4 + T5",
                "T4 = T6 - 8",
                "T5 = T4 + T2",
                "T6 = T2 - T1",
                "T7 = T3"
        };
        Map<String, Integer> res = ce.computeExpression(input);
        for(Map.Entry<String, Integer> entry : res.entrySet()){
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
    }
}
