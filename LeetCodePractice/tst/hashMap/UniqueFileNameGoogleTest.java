package hashMap;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

public class UniqueFileNameGoogleTest {
    private UniqueFileNameGoogle uf;

    @Before
    public void setup() {
        uf = new UniqueFileNameGoogle();
    }

    @Test
    public void testCase1() {
        String[] files = {"doc", "doc", "image", "doc(1)", "doc"};
        String[] res = uf.generateUniqueFileName(files);
        System.out.println(Arrays.toString(res));
    }
}
