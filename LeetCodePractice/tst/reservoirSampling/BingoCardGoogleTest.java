package reservoirSampling;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;


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
        System.out.println("------");
    }

    @Test
    public void testCase2() {
        List<int[][]> res = bc.bingoBoardFollowUp(5);
        for(int[][] board : res){
            for(int[] row : board){
                System.out.println(Arrays.toString(row));
            }
            System.out.println("------");
        }

    }
}

