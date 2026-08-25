package main

func constructProductMatrix(grid [][]int) [][]int {
    const MOD = 12345
	rows := len(grid)
	cols := len(grid[0])
	res := make([][]int, rows)
	for r := range res {
		res[r] = make([]int, cols)
	}
	suffix := int64(1)
	for r := rows - 1; r >= 0; r-- {
		for c := cols - 1; c >= 0; c-- {
			res[r][c] = int(suffix)
			suffix = (suffix * int64(grid[r][c])) % MOD
		}
	}
	prefix := int64(1)
	for r := 0; r < rows; r++ {
		for c := 0; c < cols; c++ {
			res[r][c] = int((int64(res[r][c]) * prefix) % MOD)
			prefix = (prefix * int64(grid[r][c])) % MOD
		}
	}
	return res
}
