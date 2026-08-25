package main

func maximumAmount(coins [][]int) int {
	rows := len(coins)
	cols := len(coins[0])
	const minInf = -1 << 30
	dp := make([][][]int, rows)
	for r := 0; r < rows; r++ {
		dp[r] = make([][]int, cols)
		for c := 0; c < cols; c++ {
			dp[r][c] = make([]int, 3)
			for i := 0; i < 3; i++ {
				dp[r][c][i] = minInf
			}
		}
	}
	// init firt cell
	dp[0][0][0] = coins[0][0]
	for i := 1; i <= 2; i++ {
		dp[0][0][i] = max(coins[0][0], 0)
	}
	// init first row
	for c := 1; c < cols; c++ {
		dp[0][c][0] = dp[0][c-1][0] + coins[0][c]
		for i := 1; i <= 2; i++ {
			dp[0][c][i] = max(dp[0][c-1][i]+coins[0][c], dp[0][c-1][i-1]+max(coins[0][c], 0))
		}
	}
	// init first column
	for r := 1; r < rows; r++ {
		dp[r][0][0] = dp[r-1][0][0] + coins[r][0]
		for i := 1; i <= 2; i++ {
			dp[r][0][i] = max(dp[r-1][0][i]+coins[r][0], dp[r-1][0][i-1]+max(coins[r][0], 0))
		}
	}
	// transition
	for r := 1; r < rows; r++ {
		for c := 1; c < cols; c++ {
			dp[r][c][0] = max(dp[r-1][c][0], dp[r][c-1][0]) + coins[r][c]
			for i := 1; i <= 2; i++ {
				dp[r][c][i] = max(
					max(dp[r-1][c][i], dp[r][c-1][i])+coins[r][c],
					max(dp[r-1][c][i-1], dp[r][c-1][i-1])+max(coins[r][c], 0),
				)
			}
		}
	}
	return dp[rows-1][cols-1][2]
}
