package designDataStructure;

import org.junit.Before;
import org.junit.Test;

public class PlaySongGoogleTest {
    private PlaySongGoogle ps;

    @Before
    public void setup() {
        ps = new PlaySongGoogle();
    }

    @Test
    public void testCase1(){
        int[] songs = new int[]{9, 7, 5, 4, 3, 1};
        ps.playList(songs, 4);
        for(int i = 0; i < 50; i++){
            System.out.println("pick song:" + ps.getSong());
        }
    }
}
