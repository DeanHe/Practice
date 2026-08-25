package main

func countSquares(matrix [][]int) int {
	res := 0
	rows := len(matrix)
	cols := len(matrix[0])
	dp := make([][]int, rows)
	for r := 0; r < rows; r++ {
		dp[r] = make([]int, cols)
	}
	for r := 0; r < rows; r++ {
		dp[r][0] = matrix[r][0]
		res += dp[r][0]
	}
	for c := 1; c < cols; c++ {
		dp[0][c] = matrix[0][c]
		res += dp[0][c]
	}
	for r := 1; r < rows; r++ {
		for c := 1; c < cols; c++ {
			if matrix[r][c] == 1 {
				dp[r][c] = min(dp[r-1][c-1], dp[r-1][c], dp[r][c-1]) + 1
				res += dp[r][c]
			}
		}
	}
	return res
}
