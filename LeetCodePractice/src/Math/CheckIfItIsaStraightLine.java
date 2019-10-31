package Math;
/*
You are given an array coordinates, coordinates[i] = [x, y], where [x, y] represents the coordinate of a point. Check if these points make a straight line in the XY plane.

Input: coordinates = [[1,2],[2,3],[3,4],[4,5],[5,6],[6,7]]
Output: true

Input: coordinates = [[1,1],[2,2],[3,4],[4,5],[5,6],[7,7]]
Output: false
 

Constraints:

2 <= coordinates.length <= 1000
coordinates[i].length == 2
-10^4 <= coordinates[i][0], coordinates[i][1] <= 10^4
coordinates contains no duplicate point.
*/
public class CheckIfItIsaStraightLine {
	public boolean checkStraightLine(int[][] coordinates) {
        int a_x = coordinates[0][0], a_y = coordinates[0][1], b_x = coordinates[1][0], b_y = coordinates[1][1];
        for(int i = 2; i < coordinates.length; i++){
            int p_x = coordinates[i][0], p_y = coordinates[i][1];
            if(1.0 * (a_y - b_y) / (a_x - b_x) != 1.0 * (a_y - p_y) / (a_x - p_x)){
                return false;
            }
        }
        return true;
    }
}
