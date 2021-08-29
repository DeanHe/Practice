package reservoirSampling;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*
Given a 5x5 grid, create a bingo card with the following conditions.
-the middle square in the middle column must have a free space
-it must generate random numbers per column as follows below:
-col1 1-15
-col2 16-30
-col3 31-45
-col4 46-60
-col5 61-75

Follow up: create k different bingo cards. Bingo card 1 and bingo card 2 are different if each row of bingo card 1 is different than that of bingo card2.

analysis:
use a 5 digit 15 based number to represent a bingoBoard's row
the range of number is between [1, 15^5]
a unique number sequence with length 5 represents a bingoBoard
 */
public class BingoCardGoogle {
    Random random;

    int[][] bingoBoard() {
        random = new Random();
        int most = 1;
        for (int i = 0; i < 5; i++) {
            most *= 15;
        }
        int[] nums = new int[most];
        for (int i = 0; i < most; i++) {
            nums[i] = i + 1;
        }
        nums = shuffle(nums, 5);
        int[][] board = convert(nums, 0, 4);
        return board;
    }

    List<int[][]> bingoBoardFollowUp(int k) {
        random = new Random();
        List<int[][]> res = new ArrayList<>();
        int most = 1;
        for (int i = 0; i < 5; i++) {
            most *= 15;
        }
        int[] nums = new int[most];
        for (int i = 0; i < most; i++) {
            nums[i] = i + 1;
        }
        nums = shuffle(nums, 5 * k);
        for (int i = 0; i < k; i++) {
            int[][] board = convert(nums, 5 * i, 5 * i + 4);
            res.add(board);
        }
        return res;
    }

    private int[][] convert(int[] nums, int start, int end) {
        int[][] board = new int[5][5];
        int r = 0;
        for (int i = start; i <= end; i++) {
            int n = nums[i];
            for (int c = 0; c < 5; c++) {
                int d = n % 15;
                board[r][c] = d + 1 + 15 * c;
                n /= 15;
            }
            r++;
        }
        return board;
    }

    private int[] shuffle(int[] nums, int step) {
        int[] res = nums.clone();
        int len = res.length;
        for (int i = 0; i < step; i++) {
            int index = randRange(i, len);
            swap(res, i, index);
        }
        return res;
    }

    private int randRange(int min, int max) {
        return random.nextInt(max - min) + min;
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}

