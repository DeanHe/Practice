package SweepLine;

import SweepLine.Intervals.Interval;

import java.util.*;
/*
A string, each character representing a scene. Between two identical characters is considered to be a continuous scene.
For example: abcda, you can think of these five characters as the same scene. Or acafghbeb can think of two aca and beb scenes.
If there is a coincidence between the scenes, then the scenes are combined. For example, abcab, where abca and bcab are coincident,
then the five characters are considered to be the same scene. Give a string to find the longest scene.

https://www.lintcode.com/problem/the-longest-scene/description

*/
public class ThelongestScene {
    /**
     * @param str: The scene string
     * @return: Return the length longest scene
     */
    public int getLongestScene(String str) {
        // find each alphabet leftmost and rightmost position
    	Interval[] scene = new Interval[26];
        for(int i = 0; i < 26; i++) {
            scene[i] = new Interval(str.length(), -1);
        }
        for(int i = 0; i < str.length(); i++) {
            int idx = str.charAt(i) - 'a';
            if(i < scene[idx].start) {
                scene[idx].start = i;
            }
            if(i > scene[idx].end) {
                scene[idx].end = i;
            }
        }
        Arrays.sort(scene, (a, b) -> {
            if(a.start == b.start) {
                return a.end - b.end;
            }
            return a.start - b.start;
        });
        // find the largest interval after merge intervals
        int res = scene[0].end - scene[0].start + 1;
        for(int i = 1; i < 26; i++) {
            if(scene[i].end == -1) {
                break;
            }
            if(scene[i].start <= scene[i - 1].end) { // overlap merge
                scene[i].start = scene[i - 1].start;
                if(scene[i - 1].end > scene[i].end) {
                    scene[i].end = scene[i - 1].end;
                }
            }
            if(res < scene[i].end - scene[i].start + 1) {
                res = scene[i].end - scene[i].start + 1;
            }
        }
        return res;
    }
}
