package main

import "sort"

func sortMatrix(grid [][]int) [][]int {
	rows := len(grid)
	cols := len(grid[0])
	diag := make(map[int]*[]int)
	res := make([][]int, rows)
	for r := 0; r < rows; r++ {
		res[r] = make([]int, cols)
	}
	for r := 0; r < rows; r++ {
		for c := 0; c < cols; c++ {
			d := r - c
			_, ok := diag[d]
			if !ok {
				diag[d] = &[]int{}
			}
			*diag[d] = append(*diag[d], grid[r][c])
		}
	}
	for d, arr := range diag {
		if d >= 0 {
			sort.Ints(*arr)
		} else {
			sort.Sort(sort.Reverse(sort.IntSlice(*arr)))
		}
	}
	for r := 0; r < rows; r++ {
		for c := 0; c < cols; c++ {
			d := r - c
			arr := diag[d]
			res[r][c] = (*arr)[len(*arr)-1]
			(*arr) = (*arr)[:len(*arr)-1]
		}
	}
	return res
}
