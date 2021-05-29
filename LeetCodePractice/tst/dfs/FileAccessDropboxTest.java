package dfs;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FileAccessDropboxTest {
    private FileAccessDropbox fa;


    @Before
    public void setup() {
        fa = new FileAccessDropbox();
    }

    @Test
    public void testCase1(){
        String root = "A";
        Map<String, List<String>> g = new HashMap<>();
        g.put("A", Arrays.asList("B", "E"));
        g.put("B", Arrays.asList("C", "D"));
        g.put("E", Arrays.asList("F"));
        Set<String> access = new HashSet<>();
        access.add("B");
        access.add("E");
        boolean res = fa.isAccessable(root, "F", g, access);
        Assert.assertTrue(res);
    }

    @Test
    public void testCase2(){
        String root = "A";
        Map<String, List<String>> g = new HashMap<>();
        g.put("A", Arrays.asList("B", "E"));
        g.put("B", Arrays.asList("C", "D"));
        g.put("E", Arrays.asList("F"));
        Set<String> access = new HashSet<>();
        access.add("A");
        access.add("B");
        access.add("F");
        fa.conciseAccess(root, g, access);
        Assert.assertEquals(1, access.size());
    }

    @Test
    public void testCase3(){
        String root = "A";
        Map<String, List<String>> g = new HashMap<>();
        g.put("A", Arrays.asList("B", "E"));
        g.put("B", Arrays.asList("C", "D"));
        g.put("C", Arrays.asList("G", "H"));
        g.put("E", Arrays.asList("F"));
        Set<String> access = new HashSet<>();
        access.add("B");
        access.add("G");
        access.add("E");
        access.add("F");
        fa.conciseAccess(root, g, access);
        Assert.assertEquals(2, access.size());
    }
}
