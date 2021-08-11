package reservoirSampling;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.IntStream;

/*
Given a 8 * 8 board, and 4 players represented by number 1 to 4, uniformly distribute the cell to each player, and requires that
each player's territory should be connected.
 */
public class BoardDistributeFourPlayerGoogle {
    Random random;

    int[][] distribute(int size) {
        int filled = 0;
        int[] dirs = {0, 1, 0, -1, 0};
        random = new Random();
        int[][] board = new int[size][size];
        int[] seeds = IntStream.range(1, 5).toArray();
        Map<Integer, ArrayList<Integer>> g = new HashMap<>();
        for (int n : seeds) {
            g.put(n, new ArrayList<>());
        }
        //init
        int[] begins = shuffle(seeds);
        g.get(begins[0]).add(0);
        g.get(begins[1]).add(size - 1);
        g.get(begins[2]).add(size * (size - 1));
        g.get(begins[3]).add(size * size - 1);
        while (filled < size * size) {
            for (int n : g.keySet()) {
                ArrayList<Integer> q = g.get(n);
                List<Integer> taken = new ArrayList<>();
                for (int idx : q) {
                    int r = idx / size;
                    int c = idx % size;
                    if (board[r][c] != 0) {
                        taken.add(idx);
                    }
                }
                q.removeAll(taken);
                if (!q.isEmpty()) {
                    int pick = random.nextInt(q.size());
                    int idx = q.remove(pick);
                    int r = idx / size;
                    int c = idx % size;
                    board[r][c] = n;
                    filled++;
                    for (int i = 0; i + 1 < dirs.length; i++) {
                        int nb_r = r + dirs[i];
                        int nb_c = c + dirs[i + 1];
                        if (nb_r >= 0 && nb_r < size && nb_c >= 0 && nb_c < size && board[nb_r][nb_c] == 0) {
                            int nb_idx = nb_r * size + nb_c;
                            q.add(nb_idx);
                        }
                    }
                }
            }
        }
        return board;
    }

    private int[] shuffle(int[] nums) {
        int[] res = nums.clone();
        int len = res.length;
        for (int i = 0; i < len; i++) {
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

