package reservoirSampling;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;


public class BingoCardGoogleTest {
    private BingoCardGoogle bc;

    @Before
    public void setup() {
        bc = new BingoCardGoogle();
    }

    @Test
    public void testCase1() {
        int[][] board = bc.bingoBoard();
        for(int[] row : board){
            System.out.println(Arrays.toString(row));
        }
    }
}

