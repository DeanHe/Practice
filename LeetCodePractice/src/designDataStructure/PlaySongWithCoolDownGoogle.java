package designDataStructure;

import java.util.*;

/*
Design a playlist that initially has a list of songs and a cool down value k. The playlist should randomly return a song to play.
Implement the class:
- PlayList(int[] songs, int k): initialize the playlist.
- GetSong(): provide a song that is not played in the recent k times.

analysis:
maintain pointer p which loops at right side of songs: [n - k : n - 1]
 */
public class PlaySongWithCoolDownGoogle {
    int len, k, p;
    Random rand;
    int[] songs;
    public void playList(int[] songs, int k) {
        len = songs.length;
        this.k = k;
        p = len - 1;
        this.songs = songs;
        rand = new Random();
        shuffle(songs);
    }

    public int getSong() {
        int bound = len - k;
        int pick = rand.nextInt(bound);
        int song = songs[pick];
        swap(pick, p--);
        if(p < bound){
            p = len - 1;
        }
        return song;
    }

    private void shuffle(int[] arr){
        for(int i = 0; i < len; i++){
            int idx = randRange(i, len);
            swap(i, idx);
        }
    }

    private int randRange(int min, int max){
        return min + rand.nextInt(max - min);
    }
    private void swap(int a, int b) {
        int temp = songs[a];
        songs[a] = songs[b];
        songs[b] = temp;
    }
}
