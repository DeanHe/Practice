package BST;

import java.util.LinkedList;
import java.util.Queue;

/*
 int[][] matrix = {
				{2, 2, 4, 4},
				{2, 2, 4, 4},
				{3, 4, 5, 5},
				{6, 3, 5, 5},
		};
		QuadTree quadTree = new QuadTree();
		QuadNode root = quadTree.convert(matrix, matrix.length, 0, 0);
		Queue<QuadNode> queue = new LinkedList<>();
		queue.offer(root);
		while(!queue.isEmpty()){
			int size = queue.size();
			for(int i = 0; i < size; i++){
				QuadNode cur = queue.poll();
				if(cur.UL == null && cur.UR == null && cur.LL == null && cur.LR == null){
					System.out.print(cur.val + " ");
				} else {
					System.out.print("#");
				}
				if(cur.UL != null){
					queue.offer(cur.UL);
				}
				if(cur.UR != null){
					queue.offer(cur.UR);
				}
				if(cur.LL != null){
					queue.offer(cur.LL);
				}
				if(cur.LR != null){
					queue.offer(cur.LR);
				}
			}
			System.out.println();
		}
 */
public class QuadTree {
	public QuadNode convert(int[][] matrix, int len, int r, int c){
		QuadNode root = new QuadNode();
		if(len == 1){
			root.val = matrix[r][c];
			return root;
		}
		len = len / 2;
		root.UL = convert(matrix, len, r, c);
		root.UR = convert(matrix, len, r, c + len);
		root.LL = convert(matrix, len, r + len, c);
		root.LR = convert(matrix, len, r + len, c + len);
		return root;
	}
}
