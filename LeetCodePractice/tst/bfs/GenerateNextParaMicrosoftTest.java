package bfs;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

public class GenerateNextParaMicrosoftTest {
    private GenerateNextParaMicrosoft gn;
    @Before
    public void setup() {
        TreeMap<String, List<Double>> store = new TreeMap<>();
        store.put("x1", Arrays.asList(0.1, 0.2, 0.3, 0.4));
        store.put("x2", Arrays.asList(1.6, 1.7, 1.8, 1.9));
        store.put("x3", Arrays.asList(2.2, 2.3, 2.4, 2.5));
        store.put("x4", Arrays.asList(3.5, 3.6, 3.7, 3.8));
        gn = new GenerateNextParaMicrosoft(store);
    }

    @Test
    public void testCase1(){
        while(true){
            try {
                TreeMap<String, Double> pair = gn.generateNext();
                System.out.println(pair);
            } catch (Exception e){
                System.out.println(e);
                break;
            }
        }
    }

    @Test
    public void testCase2(){
        while(true){
            try {
                TreeMap<String, Double> pair = gn.generateNextDFS();
                System.out.println(pair);
            } catch (Exception e){
                System.out.println(e);
                break;
            }
        }
    }
}
