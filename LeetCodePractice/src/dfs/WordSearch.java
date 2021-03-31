package dfs;

//Given a 2D board and a word, find if the word exists in the grid.
//The word can be constructed from letters of sequentially adjacent cell, 
//where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.
public class WordSearch {
    int[] dir = new int[]{0, 1, 0, -1, 0};
	/**
     * @param board: A list of lists of character
     * @param word: A string
     * @return: A boolean
     */
    public boolean exist(char[][] board, String word) {
        // write your code here
        if(board == null || board.length == 0 || word == null || word.length() == 0){
            return false;
        }
        int rows = board.length;
        int cols = board[0].length;
        boolean[][] visited = new boolean[rows][cols];
        for(int r = 0; r < rows; r++){
            for(int c = 0; c < cols; c++){
                if(dfs(board, visited, word, 0, r, c)){
                    return true;
                }
            }
        }
        return false;
    }
    
    private boolean dfs(char[][] board, boolean[][] visited, String word, int pos, int r, int c){
        int rows = board.length, cols = board[0].length;
        if(pos == word.length()){
            return true;
        }
        if(r >= 0 && r < rows && c >= 0 && c < cols && !visited[r][c] && board[r][c] == word.charAt(pos)){
            visited[r][c] = true;
            for(int j = 0; j < dir.length - 1; j++){
                if(dfs(board, visited, word, pos + 1, r + dir[j], c + dir[j + 1])){
                    return true;
                }
            }
            visited[r][c] = false;
        }
        return false;
    }
}
