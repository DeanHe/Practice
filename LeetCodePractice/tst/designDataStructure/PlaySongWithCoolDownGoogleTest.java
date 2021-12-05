package designDataStructure;

import org.junit.Before;
import org.junit.Test;

public class PlaySongWithCoolDownGoogleTest {
    private PlaySongWithCoolDownGoogle ps;

    @Before
    public void setup() {
        ps = new PlaySongWithCoolDownGoogle();
    }

    @Test
    public void testCase1(){
        int[] songs = new int[]{9, 7, 5, 4, 3, 1, 8};
        ps.playList(songs, 3);
        for(int i = 0; i < 50; i++){
            System.out.println("pick song:" + ps.getSong());
        }
    }
}
