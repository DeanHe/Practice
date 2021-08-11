package math;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BringingaGunToaTrainerFightGoogleTest {
    private BringingaGunToaTrainerFightGoogle bg;

    @Before
    public void setup() {
        bg = new BringingaGunToaTrainerFightGoogle();
    }

    @Test
    public void testCase1() {
        int[] dimensions = {3, 2};
        int[] your_position = {1, 1};
        int[] trainer_position = {2, 1};
        int distance = 4;
        int res = bg.solution(dimensions, your_position, trainer_position, distance);
        Assert.assertEquals(7, res);
    }

    @Test
    public void testCase2() {
        int[] dimensions = {300, 275};
        int[] your_position = {150, 150};
        int[] trainer_position = {185, 100};
        int distance = 500;
        int res = bg.solution(dimensions, your_position, trainer_position, distance);
        Assert.assertEquals(9, res);
    }

    @Test
    public void testCase3() {
        int[] dimensions = {2, 5};
        int[] your_position = {1, 2};
        int[] trainer_position = {1, 4};
        int distance = 11;
        int res = bg.solution(dimensions, your_position, trainer_position, distance);
        Assert.assertEquals(27, res);
    }

    @Test
    public void testCase4() {
        int[] dimensions = {23, 10};
        int[] your_position = {6, 4};
        int[] trainer_position = {3, 2};
        int distance = 23;
        int res = bg.solution(dimensions, your_position, trainer_position, distance);
        Assert.assertEquals(8, res);
    }

    @Test
    public void testCase5() {
        int[] dimensions = {23, 10};
        int[] your_position = {6, 4};
        int[] trainer_position = {3, 2};
        int distance = 1;
        int res = bg.solution(dimensions, your_position, trainer_position, distance);
        Assert.assertEquals(0, res);
    }

    @Test
    public void testCase6() {
        int[] dimensions = {23, 10};
        int[] your_position = {6, 4};
        int[] trainer_position = {6, 3};
        int distance = 1;
        int res = bg.solution(dimensions, your_position, trainer_position, distance);
        Assert.assertEquals(1, res);
    }


    @Test
    public void testCase7() {
        int[] dimensions = {10, 10};
        int[] your_position = {4, 4};
        int[] trainer_position = {3, 3};
        int distance = 5000;
        int res = bg.solution(dimensions, your_position, trainer_position, distance);
        Assert.assertEquals(739323, res);
    }

    @Test
    public void testCase8() {
        int[] dimensions = {1250, 1250};
        int[] your_position = {1000, 1000};
        int[] trainer_position = {500, 400};
        int distance = 10000;
        int res = bg.solution(dimensions, your_position, trainer_position, distance);
        Assert.assertEquals(196, res);
    }

    @Test
    public void testCase9() {
        int[] dimensions = {300, 275};
        int[] your_position = {150, 150};
        int[] trainer_position = {185, 100};
        int distance = 500;
        int res = bg.solution(dimensions, your_position, trainer_position, distance);
        Assert.assertEquals(9, res);
    }
}
