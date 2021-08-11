package math;

import java.util.HashMap;
import java.util.Map;

/*
Uh-oh -- you've been cornered by one of Commander Lambdas elite bunny trainers! Fortunately, you grabbed a beam weapon from an abandoned storeroom while you were running through the station, so you have a chance to fight your way out. But the beam weapon is potentially dangerous to you as well as to the bunny trainers: its beams reflect off walls, meaning you'll have to be very careful where you shoot to avoid bouncing a shot toward yourself!

Luckily, the beams can only travel a certain maximum distance before becoming too weak to cause damage. You also know that if a beam hits a corner, it will bounce back in exactly the same direction. And of course, if the beam hits either you or the bunny trainer, it will stop immediately (albeit painfully).

Write a function solution(dimensions, your_position, trainer_position, distance) that gives an array of 2 integers of the width and height of the room, an array of 2 integers of your x and y coordinates in the room, an array of 2 integers of the trainer's x and y coordinates in the room, and returns an integer of the number of distinct directions that you can fire to hit the elite trainer, given the maximum distance that the beam can travel.

The room has integer dimensions [1 < x_dim <= 1250, 1 < y_dim <= 1250]. You and the elite trainer are both positioned on the integer lattice at different distinct positions (x, y) inside the room such that [0 < x < x_dim, 0 < y < y_dim]. Finally, the maximum distance that the beam can travel before becoming harmless will be given as an integer 1 < distance <= 10000.

For example, if you and the elite trainer were positioned in a room with dimensions [3, 2], your_position [1, 1], trainer_position [2, 1], and a maximum shot distance of 4, you could shoot in seven different directions to hit the elite trainer (given as vector bearings from your location): [1, 0], [1, 2], [1, -2], [3, 2], [3, -2], [-3, 2], and [-3, -2]. As specific examples, the shot at bearing [1, 0] is the straight line horizontal shot of distance 1, the shot at bearing [-3, -2] bounces off the left wall and then the bottom wall before hitting the elite trainer with a total shot distance of sqrt(13), and the shot at bearing [1, 2] bounces off just the top wall before hitting the elite trainer with a total shot distance of sqrt(5).

Languages
=========

To provide a Java solution, edit Solution.java

Test cases
==========
Your code should pass the following test cases.
Note that it may also be run against hidden test cases not shown here.

-- Java cases --
Input:
Solution.solution([3,2], [1,1], [2,1], 4)
Output:
    7

Input:
Solution.solution([300,275], [150,150], [185,100], 500)
Output:
    9
 */
public class BringingaGunToaTrainerFightGoogle {
    public int solution(int[] dimensions, int[] your_position, int[] trainer_position, int distance) {
        int x_max_rooms = distance / dimensions[0] + 1;
        int y_max_rooms = distance / dimensions[1] + 1;
        int dsq = distance * distance;
        Map<String, Integer> candidates = new HashMap<>();
        for (int i = -x_max_rooms; i <= x_max_rooms; i++) {
            for (int j = -y_max_rooms; j <= y_max_rooms; j++) {
                int[] reflected_trainer = getLocation(dimensions, trainer_position, i, j);
                int d = dist(your_position, reflected_trainer);
                if (d <= dsq) {
                    String tan = angle(your_position, reflected_trainer);
                    if (d < candidates.getOrDefault(tan, Integer.MAX_VALUE)) {
                        candidates.put(tan, d);
                    }
                }
            }
        }
        for (int i = -x_max_rooms; i <= x_max_rooms; i++) {
            for (int j = -y_max_rooms; j <= y_max_rooms; j++) {
                int[] reflected_you = getLocation(dimensions, your_position, i, j);
                int d = dist(your_position, reflected_you);
                if (d <= dsq) {
                    String tan = angle(your_position, reflected_you);
                    if (candidates.containsKey(tan) && d < candidates.get(tan)) {
                        candidates.remove(tan);
                    }
                }
            }
        }
        return candidates.size();
    }

    private int[] getLocation(int[] dimensions, int[] position, int x_room, int y_room) {
        int x = project(dimensions[0], position[0], x_room);
        int y = project(dimensions[1], position[1], y_room);
        return new int[]{x, y};
    }

    private int project(int dimension, int pos, int room) {
        if (room % 2 == 0) {
            return room * dimension + pos;
        } else {
            return (room + 1) * dimension - pos;
        }
    }

    private int dist(int[] start, int[] end) {
        return (start[0] - end[0]) * (start[0] - end[0])
                + (start[1] - end[1]) * (start[1] - end[1]);
    }

    private int gcd(int a, int b) {
        while(b > 0){
           int ta = a;
           a = b;
           b = ta % b;
        }
        return a;
    }

    private String angle(int[] start, int[] end) {
        int x_diff = end[0] - start[0];
        int y_diff = end[1] - start[1];
        if(x_diff == 0 && y_diff == 0) {
            return "0:0";
        }
        int x_sign = x_diff > 0 ? 1 : -1;
        int y_sign = y_diff > 0 ? 1 : -1;
        if (x_diff == 0) {
            if (y_sign > 0) {
                return "0:1";
            } else {
                return "0:-1";
            }
        } else if (y_diff == 0) {
            if (x_sign > 0) {
                return "1:0";
            } else {
                return "-1:0";
            }
        } else {
            int x_diff_abs = Math.abs(x_diff);
            int y_diff_abs = Math.abs(y_diff);
            int common = gcd(x_diff_abs, y_diff_abs);
            return (x_sign * x_diff_abs / common) + ":" + (y_sign * y_diff_abs / common);
        }
    }
}

