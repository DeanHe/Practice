package BFS;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class SlidingPuzzlePrintPath {
    int[] dirs = {0, 1, 0, -1, 0};
    int rows, cols;
    public String findPath(int[][] start, int[][] end) {
        rows = start.length;
        cols = start[0].length;
        String startStr = convertToString(start);
        String endStr = convertToString(end);
        State s = new State(startStr, "");
        Set<String> visited = new HashSet<>();
        Queue<State> q = new LinkedList<>();
        q.offer(s);
        visited.add(convertToString(start));
        while (!q.isEmpty()) {
            State cur = q.poll();
            if(endStr.equals(cur.matrixRep)){
                return cur.path;
            }
            for(State nbState : getNeighbors(cur, visited)){
                q.offer(nbState);
            }

        }
        return "No Path";
    }

    private List<State> getNeighbors(State cur, Set<String> visited){
        List<State> res = new ArrayList<>();
        int[][] curMatrix = convertToMatrix(cur.matrixRep);
        for(int r = 0; r < rows; r++){
            for(int c = 0; c < cols; c++){
                if(curMatrix[r][c] == 0){
                    for(int i = 0; i < dirs.length - 1; i++){
                        int nb_r = r + dirs[i];
                        int nb_c = c + dirs[i + 1];
                        String move;
                        if(i == 0){ // 0, 1
                            move = "r";
                        } else if(i == 1){ // 1, 0
                            move = "d";
                        } else if(i == 2){ // 0, -1
                            move = "l";
                        } else { // -1, 0
                            move = "u";
                        }
                        if(nb_r >= 0 && nb_r < rows && nb_c >= 0 && nb_c < cols){
                            String nbMatrixStr = swap(cur.matrixRep, r, c, nb_r, nb_c);
                            if(!visited.contains(nbMatrixStr)){
                                res.add(new State(nbMatrixStr, cur.path + move));
                                visited.add(nbMatrixStr);
                            }
                        }
                    }
                    return res;
                }
            }
        }
        return res;
    }

    private String swap(String s, int r, int c, int nb_r, int nb_c){
        int i = r * cols + c;
        int j = nb_r * cols + nb_c;
        char[] arr = s.toCharArray();
        char t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
        return String.valueOf(arr);
    }

    private String convertToString(int[][] matrix){
        StringBuilder sb = new StringBuilder();
        for(int r = 0; r < rows; r++){
            for(int c = 0; c < cols; c++){
                sb.append(matrix[r][c]);
            }
        }
        return sb.toString();
    }

    private int[][] convertToMatrix(String matrixRep){
        int[][] res = new int[rows][cols];
        int len = matrixRep.length();
        for(int i = 0; i < len; i++){
            int r = i / cols;
            int c = i % cols;
            res[r][c] = Integer.valueOf(matrixRep.substring(i, i + 1));
        }
        return res;
    }

    private class State {
        String path;
        String matrixRep;
        public State(String matrixRep, String path){
            this.matrixRep = matrixRep;
            this.path = path;
        }
    }
}
