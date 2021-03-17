package SweepLine;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class TextNormalizationTest {
    private TextNormalization tn;

    @Before
    public void setup() {
        tn = new TextNormalization();
    }

    @Test
    public void testCase1(){
        List<Span> inputs = new ArrayList<>();
        Span s1 = new Span(0, 3);
        s1.set.add('a');
        inputs.add(s1);
        Span s2 = new Span(2, 7);
        s2.set.add('b');
        inputs.add(s2);
        Span s3 = new Span(4, 8);
        s3.set.add('c');
        inputs.add(s3);
        Span s4 = new Span(6, 10);
        s4.set.add('d');
        inputs.add(s4);
        List<Span> res = tn.normalize(inputs);
        for (Span s : res) {
            System.out.println(s);
        }
        Assert.assertTrue(res.size() > 0);
    }
}
