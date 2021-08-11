package reservoirSampling;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;


public class BoardDistributeFourPlayerGoogleTest {
    private BoardDistributeFourPlayerGoogle bd;

    @Before
    public void setup() {
        bd = new BoardDistributeFourPlayerGoogle();
    }

    @Test
    public void testCase1() {
        int[][] board = bd.distribute(8);
        for(int[] row : board){
            System.out.println(Arrays.toString(row));
        }
    }
}

