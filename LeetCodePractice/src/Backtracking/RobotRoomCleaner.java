package Backtracking;

import java.util.HashSet;
import java.util.Set;

/*
Given a robot cleaner in a room modeled as a grid.
Each cell in the grid can be empty or blocked.
The robot cleaner with 4 given APIs can move forward, turn left or turn right. Each turn it made is 90 degrees.
When it tries to move into a blocked cell, its bumper sensor detects the obstacle and it stays on the current cell.
Design an algorithm to clean the entire room using only the 4 given APIs shown below.
interface Robot {
	  // returns true if next cell is open and robot moves into the cell.
	  // returns false if next cell is obstacle and robot stays on the current cell.
	  boolean move();

	  // Robot will stay on the same cell after calling turnLeft/turnRight.
	  // Each turn will be 90 degrees.
	  void turnLeft();
	  void turnRight();

	  // Clean the current cell.
	  void clean();
	}
Example:

Input:
room = [
  [1,1,1,1,1,0,1,1],
  [1,1,1,1,1,0,1,1],
  [1,0,1,1,1,1,1,1],
  [0,0,0,1,0,0,0,0],
  [1,1,1,1,1,1,1,1]
],
row = 1,
col = 3

Explanation:
All grids in the room are marked by either 0 or 1.
0 means the cell is blocked, while 1 means the cell is accessible.
The robot initially starts at the position of row=1, col=3.
From the top left corner, its position is one row below and three columns right.
Notes:

The input is only given to initialize the room and the robot's position internally. You must solve this problem "blindfolded".
In other words, you must control the robot using only the mentioned 4 APIs, without knowing the room layout and the initial robot's position.
The robot's initial position will always be in an accessible cell.
The initial direction of the robot will be facing up.
All accessible cells are connected, which means the all cells marked as 1 will be accessible by the robot.
Assume all four edges of the grid are all surrounded by wall.

time Complexity O(n) space Complexity O(n)
*/
public class RobotRoomCleaner {
    int[] dirs = {0, -1, 0, 1, 0};
    Set<String> visited = new HashSet<>();

    public void cleanRoom(Robot robot) {
        dfs(0, 0, 0, robot);
    }

    private void dfs(int r, int c, int facing, Robot robot) {
        visited.add(r + ":" + c);
        robot.clean();
        for (int i = 0; i < dirs.length - 1; i++) {
            if (robot.move()) {
                int nb_r = r + dirs[facing];
                int nb_c = c + dirs[facing + 1];
                if (!visited.contains(nb_r + ":" + nb_c)) {
                    dfs(nb_r, nb_c, facing, robot);
                    backToPreviousPosition(robot);
                }
            }
            facing++;
            facing = facing % (dirs.length - 1);
            robot.turnRight();
        }
    }

    private void backToPreviousPosition(Robot robot) {
        robot.turnRight();
        robot.turnRight();
        robot.move();
        robot.turnRight();
        robot.turnRight();
    }
}
