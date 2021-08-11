package reservoirSampling;

import java.util.Random;

/*
Given a 5x5 grid, create a bingo card with the folliwing condtions.
-the middle square in the middle column must have a free space
-it must generate random numbers per column as follows below:
-col1 1-15
-col2 16-30
-col3 31-45
-col4 46-60
-col5 61-75

Follow up: create k different bingo cards. Bingo card 1 and bingo card 2 are different if each row of bingo card 1 is different than that of bingo card2.

Any algorihtm better than rejection sampling?
 */
public class BingoCardGoogle {
    Random random;
    int[][] bingoBoard(){
        random = new Random();
        int[][] board = new int[5][5];
        int[][] nums = new int[5][15];
        int n = 1;
        for(int r = 0; r < 5; r++){
            for(int c = 0; c < 15; c++){
                nums[r][c] = n++;
            }
        }
        for(int c = 0; c < 5; c++){
            int[] temp = shuffle(nums[c], c == 2 ? 4 : 5);
            for(int r = 0; r < 5; r++){
                if(c == 2){
                    if(r < 2){
                        board[r][c] = temp[r];
                    } else if(r > 2){
                        board[r][c] = temp[r - 1];
                    }
                } else {
                    board[r][c] = temp[r];
                }
            }
        }
        return board;
    }

    private int[] shuffle(int[] nums, int step) {
        int[] res = nums.clone();
        int len = res.length;
        for(int i = 0; i < step; i++){
            int index = randRange(i, len);
            swap(res, i, index);
        }
        return res;
    }

    private int randRange(int min, int max){
        return random.nextInt(max - min) + min;
    }

    private void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}

