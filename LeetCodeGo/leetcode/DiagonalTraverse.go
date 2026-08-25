package main

func findDiagonalOrder(mat [][]int) []int {
	rows := len(mat)
	cols := len(mat[0])
	res := []int{}
	r := 0
	c := 0
	for i := 0; i < rows*cols; i++ {
		res = append(res, mat[r][c])
		if (r+c)%2 == 0 {
			if c == cols-1 {
				r += 1
			} else if r == 0 {
				c += 1
			} else {
				r -= 1
				c += 1
			}
		} else {
			if r == rows-1 {
				c += 1
			} else if c == 0 {
				r += 1
			} else {
				r += 1
				c -= 1
			}
		}
	}
	return res
}
