package main

func minimumDistance(nums []int) int {
	pos := make(map[int][]int)
	for i, x := range nums {
		pos[x] = append(pos[x], i)
	}
	res := -1
	for _, arr := range pos {
		if len(arr) >= 3 {
			for i := 2; i < len(arr); i++ {
				dist := 2 * (arr[i] - arr[i-2])
				if res == -1 || dist < res {
					res = dist
				}
			}
		}
	}
	return res
}
