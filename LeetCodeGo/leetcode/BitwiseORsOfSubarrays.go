package main

func subarrayBitwiseORs(arr []int) int {
	res := make(map[int]struct{})
	cur := make(map[int]struct{})
	for _, num := range arr {
		next := make(map[int]struct{})
		for x := range cur {
			next[x|num] = struct{}{}
		}
		next[num] = struct{}{}
		cur = next
		for x := range cur {
			res[x] = struct{}{}
		}
	}
	return len(res)
}
