package unionFind;

import java.util.List;

/*
Given a rectangle area of [h, w], and some traps represented by circles
a mouse tries to go from left to right without being trapped.
check if it is possible
 */
public class mouseTrapPassGoogle {
    int[] parent;
    double[][] heightBound;
    List<Circle> traps;
    public boolean canPass(int[] rect, List<Circle> traps){
        int height = rect[0];
        int width = rect[1];
        this.traps = traps;
        int tlen = traps.size();
        parent = new int[tlen];
        heightBound = new double[tlen][2];
        for(int i = 0; i < tlen; i++){
            parent[i] = i;
            Circle trap = traps.get(i);
            heightBound[i][0] = trap.y - trap.r;
            heightBound[i][1] = trap.y + trap.r;
        }
        for(int i = 0; i < tlen; i++){
            for(int j = i + 1; j < tlen; j++){
                Circle a = traps.get(i);
                Circle b = traps.get(j);
                if(intersect(a, b)){
                    union(i, j);
                }
            }
        }
        for(int i = 0; i < tlen; i++){
            if(heightBound[i][0] <= 0 && heightBound[i][1] >= height){
                return false;
            }
        }
        return true;
    }

    private void union(int a, int b) {
        int a_root = findRoot(a);
        int b_root = findRoot(b);
        if(a_root != b_root){
            parent[a_root] = b_root;
            heightBound[b_root][0] = Math.min(heightBound[b_root][0], heightBound[a_root][0]);
            heightBound[b_root][1] = Math.max(heightBound[b_root][1], heightBound[a_root][1]);
        }
    }

    private int findRoot(int x){
        int root = x;
        while(parent[root] != root){
            root = parent[root];
        }
        while(x != root){
            int fa = parent[x];
            parent[x] = root;
            x = fa;
        }
        return root;
    }

    private boolean intersect(Circle a, Circle b){
        return (a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y) <= (a.r + b.r) * (a.r + b.r);
    }

    public class Circle {
        public double x, y, r;
        public Circle(double x, double y, double r){
            this.x = x;
            this.y = y;
            this.r = r;
        }
    }
}
