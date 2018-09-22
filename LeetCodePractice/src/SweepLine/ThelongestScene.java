package SweepLine;

import java.util.*;
/*A string, each character representing a scene. Between two identical characters is considered to be a continuous scene. For example: abcda, you can think of these five characters as the same scene. Or acafghbeb can think of two aca and beb scenes. If there is a coincidence between the scenes, then the scenes are combined. For example, abcab, where abca and bcab are coincident, then the five characters are considered to be the same scene. Give a string to find the longest scene.*/

// https://www.lintcode.com/problem/the-longest-scene/description
public class ThelongestScene {
	/**
     * @param str: The scene string
     * @return: Return the length longest scene
     */
    
    class Segment {
        public int start;
        public int end;
        public Segment(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
    class Cmp implements Comparator<Segment>{
       @Override
       public int compare(Segment a, Segment b){
            if(a.start == b.start) {
                return a.end - b.end;
            }
            return a.start - b.start;
          }
    }
    
    public int getLongestScene(String str) {
        // Write your code here
        Segment[] scene = new Segment[26];
        for(int i = 0; i < 26; i++) {
            scene[i] = new Segment(str.length(), -1);
        }
        for(int i = 0; i < str.length(); i++) {
            int index = str.charAt(i) - 'a';
            if(i < scene[index].start) {
                scene[index].start = i;
            }
            if(i > scene[index].end) {
                scene[index].end = i;
            }
        }
        Arrays.sort(scene, new Cmp());
        int ans = scene[0].end - scene[0].start + 1;
        for(int i = 1; i < 26; i++) {
            if(scene[i].end == -1) {
                break;
            }
            if(scene[i].start <= scene[i - 1].end) {
                scene[i].start = scene[i - 1].start;
                if(scene[i - 1].end > scene[i].end) {
                    scene[i].end = scene[i - 1].end;
                }
            }
            if(scene[i].end - scene[i].start + 1 > ans) {
                ans = scene[i].end - scene[i].start + 1;
            }
        }
        return ans;
    }
}
