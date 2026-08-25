package main

func largestGoodInteger(num string) string {
	max_i := -1
	for i := 0; i < len(num)-2; i++ {
		if num[i] == num[i+1] && num[i+1] == num[i+2] {
			if max_i == -1 {
				max_i = i
			} else if num[max_i] < num[i] {
				max_i = i
			}
		}
	}

	if max_i != -1 {
		return num[max_i : max_i+3]
	}
	return ""
}
