package main

func judgePoint24(cards []int) bool {
	var list []float64
	for _, n := range cards {
		list = append(list, float64(n))
	}
	return dfs(list)
}

func dfs(list []float64) bool {
	sz := len(list)
	if sz == 1 {
		if abs(list[0]-24) < 0.001 {
			return true
		}
		return false
	}

	for i := 0; i < sz; i++ {
		for j := i + 1; j < sz; j++ {
			candidates := compute(list[i], list[j])
			for _, cand := range candidates {
				ls := []float64{cand}
				// carry over unused cards
				for k := 0; k < sz; k++ {
					if k != i && k != j {
						ls = append(ls, list[k])
					}
				}
				if dfs(ls) {
					return true
				}
			}
		}
	}
	return false
}

func compute(a float64, b float64) []float64 {
	return []float64{a - b, b - a, a + b, a / b, b / a, a * b}
}

func abs(n float64) float64 {
	if n < 0 {
		return -n
	}
	return n
}
