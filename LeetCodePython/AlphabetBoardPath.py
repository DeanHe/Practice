"""
On an alphabet board, we start at position (0, 0), corresponding to character board[0][0].
Here, board = ["abcde", "fghij", "klmno", "pqrst", "uvwxy", "z"], as shown in the diagram below.

We may make the following moves:

'U' moves our position up one row, if the position exists on the board;
'D' moves our position down one row, if the position exists on the board;
'L' moves our position left one column, if the position exists on the board;
'R' moves our position right one column, if the position exists on the board;
'!' adds the character board[r][c] at our current position (r, c) to the answer.
(Here, the only positions that exist on the board are positions with letters on them.)

Return a sequence of moves that makes our answer equal to target in the minimum number of moves.  You may return any path that does so.

Example 1:
Input: target = "leet"
Output: "DDR!UURRR!!DDD!"

Example 2:
Input: target = "code"
Output: "RR!DDRR!UUL!R!"

Constraints:
1 <= target.length <= 100
target consists only of English lowercase letters.

hint:
1 Create a hashmap from letter to position on the board.
2 Now for each letter, try moving there in steps, where at each step you check if it is inside the boundaries of the board.
"""


class AlphabetBoardPath:
    def alphabetBoardPath(self, target: str) -> str:
        res = []
        m = {c: [i // 5, i % 5] for i, c in enumerate("abcdefghijklmnopqrstuvwxyz")}
        r, c = 0, 0
        for letter in target:
            nxt_r, nxt_c = m[letter]
            if nxt_c < c:
                res.append('L' * (c - nxt_c))
            if nxt_r < r:
                res.append('U' * (r - nxt_r))
            if nxt_r > r:
                res.append('D' * (nxt_r - r))
            if nxt_c > c:
                res.append('R' * (nxt_c - c))
            res.append('!')
            r, c = nxt_r, nxt_c
        return "".join(res)
