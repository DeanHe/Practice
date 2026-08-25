package main

import "strconv"

func isValidSudoku(board [][]byte) bool {
	rows := len(board)
	cols := len(board[0])
	set := make(map[string]struct{})
	for r := 0; r < rows; r++ {
		for c := 0; c < cols; c++ {
			if board[r][c] != '.' {
				hash_row := strconv.Itoa(r) + string('(') + string(board[r][c]) + string(')')
				hash_col := string('(') + string(board[r][c]) + string(')') + strconv.Itoa(c)
				sr := r - r%3
				sc := c - c%3
				hash_square := strconv.Itoa(sr) + string('(') + string(board[r][c]) + string(')') + strconv.Itoa(sc)
				if _, ok := set[hash_row]; ok {
					return false
				} else {
					set[hash_row] = struct{}{}
				}
				if _, ok := set[hash_col]; ok {
					return false
				} else {
					set[hash_col] = struct{}{}
				}
				if _, ok := set[hash_square]; ok {
					return false
				} else {
					set[hash_square] = struct{}{}
				}
			}
		}
	}
	return true
}
