package main

func robotSim(commands []int, obstacles [][]int) int {
	res := 0
	x, y := 0, 0
	dirs := [][]int{{0, 1}, {1, 0}, {0, -1}, {-1, 0}}
	d := 0
	obs := make(map[int]map[int]bool)
	for _, obstacle := range obstacles {
		if obs[obstacle[0]] == nil {
			obs[obstacle[0]] = make(map[int]bool)
		}
		obs[obstacle[0]][obstacle[1]] = true
	}
	for _, cmd := range commands {
		if cmd == -1 {
			d = (d + 1) % 4
		} else if cmd == -2 {
			d = (d + 3) % 4
		} else {
			dx := dirs[d][0]
			dy := dirs[d][1]
			for i := 0; i < cmd; i++ {
				if obs[x+dx] != nil && obs[x+dx][y+dy] {
					break
				}
				x += dx
				y += dy
			}
			dist := x*x + y*y
			if dist > res {
				res = dist
			}
		}
	}
	return res
}
