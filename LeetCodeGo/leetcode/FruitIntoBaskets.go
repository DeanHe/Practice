package main

func totalFruit(fruits []int) int {
	res := 0
	basket := make(map[int]int)
	l := 0
	for r, fruit := range fruits {
		basket[fruit]++
		for len(basket) > 2 {
			basket[fruits[l]]--
			if basket[fruits[l]] == 0 {
				delete(basket, fruits[l])
			}
			l++
		}
		if res < r-l+1 {
			res = r - l + 1
		}
	}
	return res
}
