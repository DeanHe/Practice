package string;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class MergeIpSubsetTest {
    private MergeIpSubset mi;

    @Before
    public void setup() {
        mi = new MergeIpSubset();
    }

    @Test
    public void testCase1() {
        String[] input = {
                "172.224.224.32/31",
                "172.224.224.34/31",
                "172.224.224.36/31",
                "146.75.169.110/31",
                "146.75.169.112/31",
                "146.75.169.114/31",
                "146.75.169.116/31",
                "146.75.169.118/31"
        };
        List<String> res = mi.merge(input);
        System.out.println(res);
    }
}
