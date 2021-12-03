package sort;

import java.util.*;

/*
On an infinite number line (x-axis), we drop given squares in the order they are given.
The i-th square dropped (positions[i] = (left, side_length)) is a square with the left-most point being positions[i][0] and sidelength positions[i][1].
The square is dropped with the bottom edge parallel to the number line, and from a higher height than all currently landed squares. We wait for each square to stick before dropping the next.
The squares are infinitely sticky on their bottom edge, and will remain fixed to any positive length surface they touch (either the number line or another square). Squares dropped adjacent to each other will not stick together prematurely.
Return a list ans of heights. Each height ans[i] represents the current highest height of any square we have dropped, after dropping squares represented by positions[0], positions[1], ..., positions[i].

Example 1:

Input: [[1, 2], [2, 3], [6, 1]]
Output: [2, 5, 5]
Explanation:
After the first drop of positions[0] = [1, 2]: _aa _aa ------- The maximum height of any square is 2.
After the second drop of positions[1] = [2, 3]: __aaa __aaa __aaa _aa__ _aa__ -------------- The maximum height of any square is 5. The larger square stays on top of the smaller square despite where its center of gravity is, because squares are infinitely sticky on their bottom edge.
After the third drop of positions[1] = [6, 1]: __aaa __aaa __aaa _aa _aa___a -------------- The maximum height of any square is still 5. Thus, we return an answer of [2, 5, 5].
 
Example 2:

Input: [[100, 100], [200, 100]]
Output: [100, 100]
Explanation: Adjacent squares don't get stuck prematurely - only their bottom edge can stick to surfaces.
 

Note:

1 <= positions.length <= 1000.
1 <= positions[i][0] <= 10^8.
1 <= positions[i][1] <= 10^6.
*/
public class FallingSquares {

	public List<Integer> fallingSquares(int[][] positions) {
        List<Integer> res = new ArrayList<>();
        List<Square> squares = new ArrayList<>();
        int maxHeight = 0;
        for(int[] p : positions){
        	Square cur = new Square(p[0], p[0] + p[1], p[1]);
        	int curHeight = calculateHeight(squares, cur);
            maxHeight = Math.max(maxHeight, curHeight);
        	res.add(maxHeight);
        }
        return res;
    }
	
	private int calculateHeight(List<Square> squares, Square cur){
		int preMaxHeight = 0;
		for(Square sq : squares){
			if(sq.end <= cur.start){
				continue;
			}
			if(cur.end <= sq.start){
				continue;
			}
			preMaxHeight = Math.max(preMaxHeight, sq.height);
		}
		cur.height += preMaxHeight;
		squares.add(cur);
		return cur.height;
	}
	
	class Square {
		int start, end, height;
		public Square(int s, int e, int h){
			start = s;
			end = e;
			height = h;
		}
	}
	
	public List<Integer> fallingSquaresByTreeMap(int[][] positions) {
        List<Integer> res = new ArrayList<>();
        TreeMap<Integer, Integer> map = new TreeMap<>();  //start point of square : height
        map.put(0, 0);
        int maxHeight = 0;
        for(int[] p : positions){
        	int start = p[0];
        	int height = p[1];
        	int end = p[0] + p[1];
        	int from = map.floorKey(start);
        	int h = 0;
        	for(int temp : map.subMap(from, end).values()){
        		h = Math.max(h, temp);
        	}
        	height += h;
        	map.put(start, height);
        	maxHeight = Math.max(maxHeight, height);
        	res.add(maxHeight);
        	// remove interval within [start, end)
        	int lastHeight = map.floorEntry(end).getValue();
        	map.put(start, height);
        	map.put(end, lastHeight);
        	map.keySet().removeAll(new HashSet<>(map.subMap(start, false, end, false).keySet()));
        }
        return res;
    }
}
