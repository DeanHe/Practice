package designDataStructure;

import java.util.List;
import java.util.Random;

/*
Design a playlist that initially has a list of songs and a cool down value k. The playlist should randomly return a song to play.
Implement the class:
- PlayList(int[] songs, int k): initialize the playlist.
- GetSong(): provide a song that is not played in the recent k times.

 */
public class PlaySongGoogle {
    int len, k, l, r;
    Random rand;
    int[] songs;

    public void playList(int[] songs, int k) {
        len = songs.length;
        this.k = k;
        this.songs = songs;
        rand = new Random();
        l = len - 1;
        r = len - 1;
    }

    public int getSong() {
        int pick = randRange(r + 1 - len, l + 1);
        while(pick < 0){
            pick = (pick + len) % len;
        }
        int song = songs[pick];
        int lt = l;
        while(lt < 0){
            lt = (lt + len) % len;
        }
        swap(pick, lt);
        l--;
        if (r - l > k) {
            r--;
        }
        return song;
    }

    private void swap(int a, int b) {
        int temp = songs[a];
        songs[a] = songs[b];
        songs[b] = temp;
    }

    private int randRange(int min, int max) {
        System.out.println(min + ":" + max);
        return rand.nextInt(max - min) + min;
    }
}
