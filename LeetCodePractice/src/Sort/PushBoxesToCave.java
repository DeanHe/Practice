package Sort;

import java.util.Arrays;

/*
#Google
push boxes of different heights into a cave, the cave is represented by an array of different height allowed
heights[0] is the height start at cave entrance, and vice versa.
return the maximum boxes you can push
 */
public class PushBoxesToCave {
    public int countBoxes(int[] heights, int[] boxes){
        int cnt = 0;
        Arrays.sort(boxes);
        for(int i = 1; i < heights.length; i++){
            heights[i] = Math.min(heights[i - 1], heights[i]);
        }
        int j = 0; // box index
        for(int i = heights.length - 1; i >= 0; i--){
            if(heights[i] >= boxes[j]){
                j++;
                cnt++;
            }
        }
        return cnt;
    }

    public void test(){
        int[] heights = {6, 10, 5};
        int[] boxes = {10, 30, 6, 5, 8, 7};
        System.out.println(countBoxes(heights, boxes));
    }
}
