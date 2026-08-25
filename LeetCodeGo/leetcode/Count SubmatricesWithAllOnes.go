package main

func numSubmat(mat [][]int) int {
	rows := len(mat)
	cols := len(mat[0])
	heights := make([]int, cols)
	res := 0
	for r := 0; r < rows; r++ {
		for c := 0; c < cols; c++ {
			if mat[r][c] == 1 {
				heights[c] += 1
			} else {
				heights[c] = 0
			}
		}
		res += helper(heights)
	}
	return res
}

func helper(heights []int) int {
	res := 0
	cols := len(heights)
	total := make([]int, cols)
	stack := []int{}
	for c := 0; c < cols; c++ {
		for len(stack) > 0 {
			if heights[c] <= heights[stack[len(stack)-1]] {
				// stack.pop()
				stack = stack[:len(stack)-1]
			} else {
				break
			}
		}
		if len(stack) > 0 {
			pre := stack[len(stack)-1]
			total[c] = total[pre]
			total[c] += heights[c] * (c - pre)
		} else {
			total[c] = heights[c] * (c + 1)
		}
		stack = append(stack, c)
	}
	for _, n := range total {
		res += n
	}
	return res
}
