package main

import "fmt"

func getHappyString(n int, k int) string {
	res := ""
	dfs(n, &k, "", &res)
	return res
}

func dfs(n int, k *int, cur string, res *string) {
	candidates := []string{"a", "b", "c"}
	if len(cur) == n {
		*k--
		if *k == 0 {
			*res = cur
			fmt.Println(*res)
		}
		return
	}
	for _, c := range candidates {
		if len(cur) == 0 || cur[len(cur)-1:] != c {
			cur += c
			dfs(n, k, cur, res)
			cur = cur[:len(cur)-1]
		}
	}
}
