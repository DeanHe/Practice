package main

import (
	"math"
	"sort"
)

func minCost(basket1 []int, basket2 []int) int64 {
	var res int64
	least := math.MaxInt
	freq := make(map[int]int)
	for _, x := range basket1 {
		freq[x]++
		if x < least {
			least = x
		}
	}
	for _, x := range basket2 {
		freq[x]--
		if x < least {
			least = x
		}
	}
	var to_swap []int
	for num, cnt := range freq {
		if cnt%2 == 1 {
			return -1
		}
		for i := 0; i < abs(cnt)/2; i++ {
			to_swap = append(to_swap, num)
		}
	}
	sort.Ints(to_swap)
	for i := 0; i < len(to_swap)/2; i++ {
		if least*2 < to_swap[i] {
			res += int64(least * 2)
		} else {
			res += int64(to_swap[i])
		}
	}
	return res
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
