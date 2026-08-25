package main

import "sort"

func largestSubmatrix(matrix [][]int) int {
	res := 0
    rows := len(matrix)
	cols := len(matrix[0])
	preSum := make([]int, cols)
	for r := 0; r < rows; r++ {
		row := append([]int(nil), matrix[r]...)
		for c := 0; c < cols; c++ {
			if row[c] == 1 {
				row[c] += preSum[c]
			}
			preSum[c] = row[c]
		}
		sort.Sort(sort.Reverse(sort.IntSlice(row)))
		for c := 0; c < cols; c++ {
			area := (c + 1) * row[c]
			if res < area {
				res = area
			}
		}
	}
	return res
}