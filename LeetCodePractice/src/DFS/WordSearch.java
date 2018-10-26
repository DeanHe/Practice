package DFS;

//Given a 2D board and a word, find if the word exists in the grid.
//The word can be constructed from letters of sequentially adjacent cell, 
//where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.
public class WordSearch {
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
        boolean[][] checked = new boolean[rows][cols];
        for(int r = 0; r < rows; r++){
            for(int c = 0; c < cols; c++){
                if(dfs(board, checked, word, 0, r, c)){
                    return true;
                }
            }
        }
        return false;
    }
    
    private boolean dfs(char[][] board, boolean[][] checked, String word, int i, int r, int c){
        int rows = board.length;
        int cols = board[0].length;
        if(i == word.length()){
            return true;
        }
        if(r < 0 || r >= rows || c < 0 || c >= cols){
            return false;
        }
        if(checked[r][c] == true){
            return false;
        }
        if(word.charAt(i) == board[r][c]){
            checked[r][c] = true;
            boolean res = dfs(board, checked, word, i + 1, r - 1, c) 
                        || dfs(board, checked, word, i + 1, r + 1, c) 
                        || dfs(board, checked, word, i + 1, r, c - 1) 
                        || dfs(board, checked, word, i + 1, r, c + 1);
            checked[r][c] = false;
            return res;
        } else {
            return false;
        }
    }
}
