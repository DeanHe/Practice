package Math;

import java.util.HashSet;

/*iven a n point on the two-dimensional coordinate system, output the maximum area of the rectangle that consisting of four points. 
If it cannot form a rectangle, output 0*/
public class PlaneMaximumRectangle {
	class Point {
		int x, y;
		public Point(int x, int y) {
			this.x = x; 
			this.y = y;
		}
		@Override
		public boolean equals(Object obj) {
			if(!(obj instanceof Point)){
				return false;
			}
			if(obj == this){
				return true;
			}
			return this.x == ((Point)obj).x && this.y == ((Point)obj).y;
		}
		@Override
		public int hashCode() {
			return this.x * 10000 + this.y;
		}
	}
	/**
     * @param a: the points
     * @return: return the maximum rectangle area
     */
    public int getMaximum(int[][] a) {
    	HashSet<Point> set = new HashSet<>();
        for(int[] item : a){
        	Point point = new Point(item[0], item[1]);
        	set.add(point);
        }
        int len = a.length;
        int ans = 0;
        for(int i = 0; i < len; i++){
        	int[] p1 = a[i];
        	for(int j = 1 + 1; j < len; j++){
        		int[] p3 = a[j];
        		ans = Math.max(ans, area(p1, p3, set));
        	}
        }
        return ans;
    }
	private int area(int[] p1, int[] p3, HashSet<Point> set){
		int area = 0;
		if(p1[0] == p3[0] || p1[1] == p3[1]){
			return area;
		}
		Point p2 = new Point(Math.max(p1[0], p3[0]), Math.max(p1[1], p3[1]));
		Point p4 = new Point(Math.min(p1[0], p3[0]), Math.min(p1[1], p3[1]));
		if(set.contains(p2) && set.contains(p4)){
			area = Math.abs(p1[0] - p3[0]) * Math.abs(p1[1] - p3[1]);
		}
		return area;
	}
}
