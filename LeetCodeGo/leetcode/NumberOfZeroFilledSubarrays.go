package main

func zeroFilledSubarray(nums []int) int64 {
	res := int64(0)
	consecutive_zeros_cnt := int64(0)
	for _, num := range nums {
		if num == 0 {
			consecutive_zeros_cnt += 1
			res += consecutive_zeros_cnt
		} else {
			consecutive_zeros_cnt = 0
		}
	}
	return res
}
