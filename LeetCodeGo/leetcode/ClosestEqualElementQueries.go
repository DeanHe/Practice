package main

func solveQueries(nums []int, queries []int) []int {
	numIndexes := make(map[int][]int)
	idxToPos := make(map[int]int)
	for i, x := range nums {
		idxToPos[i] = len(numIndexes[x])
		numIndexes[x] = append(numIndexes[x], i)
	}
	res := make([]int, len(queries))
	for i, q := range queries {
		num := nums[q]
		pos := idxToPos[q]
		prePos := (pos - 1 + len(numIndexes[num])) % len(numIndexes[num])
		postPos := (pos + 1) % len(numIndexes[num])
		toPrePosDist := abs(q - numIndexes[num][prePos])
		toPostPosDist := abs(q - numIndexes[num][postPos])
		minDist := min(toPrePosDist, toPostPosDist, abs(len(nums) - toPrePosDist), abs(len(nums) - toPostPosDist))
		if minDist == 0 {
			minDist = -1
		}
		res[i] = minDist
	}
	return res
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
