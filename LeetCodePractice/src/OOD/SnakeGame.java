package OOD;

import java.util.Queue;

/*
Design a Snake game that is played on a device with screen size = width x height. Play the game online if you are not familiar with the game.

The snake is initially positioned at the top left corner (0,0) with length = 1 unit.

You are given a list of food's positions in row-column order. When a snake eats the food, its length and the game's score both increase by 1.

Each food appears one by one on the screen. For example, the second food will not appear until the first food was eaten by the snake.

When a food does appear on the screen, it is guaranteed that it will not appear on a block occupied by the snake.

Example:
Given width = 3, height = 2, and food = [[1,2],[0,1]].

Snake snake = new Snake(width, height, food);

Initially the snake appears at position (0,0) and the food at (1,2).

|S| | |
| | |F|

snake.move("R"); -> Returns 0

| |S| |
| | |F|

snake.move("D"); -> Returns 0

| | | |
| |S|F|

snake.move("R"); -> Returns 1 (Snake eats the first food and right after that, the second food appears at (0,1) )

| |F| |
| |S|S|

snake.move("U"); -> Returns 1

| |F|S|
| | |S|

snake.move("L"); -> Returns 2 (Snake eats the second food)

| |S|S|
| | |S|

snake.move("U"); -> Returns -1 (Game over because snake collides with border)
 */
public class SnakeGame {
    int[][] food;
    int height, width, score, r, c, foodIdx;
    boolean[][] snakeBody;
    Queue<int[]> queue;
    /** Initialize your data structure here.
     @param width - screen width
     @param height - screen height
     @param food - A list of food positions
     E.g food = [[1,1], [1,0]] means the first food is positioned at [1,1], the second is at [1,0]. */
    public SnakeGame(int width, int height, int[][] food) {
        this.width = width;
        this.height = height;
        this.food = food;
        r = 0;
        c = 0;
        score = 0;
        foodIdx = 0;
        snakeBody = new boolean[height][width];
        queue.offer(new int[]{0, 0});
        snakeBody[r][c] = true;
    }

    /** Moves the snake.
     @param direction - 'U' = Up, 'L' = Left, 'R' = Right, 'D' = Down
     @return The game's score after the move. Return -1 if game over.
     Game over when snake crosses the screen boundary or bites its body. */
    public int move(String direction) {
        if(score == -1){
            return score;
        }
        if(direction.equals("U")){
            r--;
        } else if(direction.equals("D")){
            r++;
        } else if(direction.equals("L")){
            c--;
        } else if(direction.equals("R")){
            c++;
        }
        if(r < 0 || r >= height || c < 0 || c >= width){
            score = -1;
            return score;
        }
        //not food position
        if(foodIdx == food.length || (r == food[foodIdx][0] && c == food[foodIdx][1])){
            int[] tail = queue.poll();
            int tail_r = tail[0];
            int tail_c = tail[1];
            snakeBody[tail_r][tail_c] = false;
        } else { // eat
            score++;
            foodIdx++;
        }
        if(snakeBody[r][c]){ // meet self
            score = -1;
            return score;
        } else {
            queue.offer(new int[]{r, c});
            snakeBody[r][c] = true;
        }
        return score;
    }

    /**
     * Your SnakeGame object will be instantiated and called as such:
     * SnakeGame obj = new SnakeGame(width, height, food);
     * int param_1 = obj.move(direction);
     */
}
