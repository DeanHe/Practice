package main

const MAX = 200000

func maxStability(n int, edges [][]int, k int) int {
	mustWeightMin := MAX
	start := -1
	end := 0
	uf := MakeUnionFind(n)
	for _, edge := range edges {
		a := edge[0]
		b := edge[1]
		weight := edge[2]
		must := edge[3]
		if end < 2*weight {
			end = 2 * weight
		}
		if must == 1 {
			if weight < mustWeightMin {
				mustWeightMin = weight
			}
			if !uf.union(a, b) {
				return -1
			}
		}
	}

	for start+1 < end {
		mid := (start + end) / 2
		if canFormMinSpanningTree(n, edges, k, mid, mustWeightMin, uf) {
			start = mid
		} else {
			end = mid
		}
	}
	if canFormMinSpanningTree(n, edges, k, end, mustWeightMin, uf) {
		return end
	}
	return start
}

func canFormMinSpanningTree(n int, edges [][]int, k int, target int, mustWeightMin int, initUF *UnionFind) bool {
	if mustWeightMin < target {
		return false
	}
	uf := MakeUnionFind(n)
	copy(uf.parent[:], initUF.parent[:])
	copy(uf.size[:], initUF.size[:])
	toUpgrade := make([][2]int, 0)
	upgradeAvailableCount := k
	for _, edge := range edges {
		a := edge[0]
		b := edge[1]
		weight := edge[2]
		must := edge[3]
		if must == 0 {
			if weight >= target {
				uf.union(a, b)
			} else if 2*weight >= target {
				toUpgrade = append(toUpgrade, [2]int{a, b})
			}
		}
	}
	for _, pair := range toUpgrade {
		a := pair[0]
		b := pair[1]
		if uf.findRoot(a) != uf.findRoot(b) {
			if upgradeAvailableCount <= 0 {
				return false
			}
			uf.union(a, b)
			upgradeAvailableCount--
		}
	}
	for i := 0; i < n; i++ {
		if uf.findRoot(i) != uf.findRoot(0) {
			return false
		}
	}
	return true
}

type UnionFind struct {
	parent []int
	size   []int
}

func MakeUnionFind(n int) *UnionFind {
	p := make([]int, n)
	sz := make([]int, n)
	for i := 0; i < n; i++ {
		p[i] = i
		sz[i] = 1
	}
	return &UnionFind{parent: p, size: sz}
}

func (uf *UnionFind) findRoot(x int) int {
	root := x
	for root != uf.parent[root] {
		root = uf.parent[root]
	}
	for root != uf.parent[x] {
		fa := uf.parent[x]
		uf.parent[x] = root
		x = fa
	}
	return root
}

func (uf *UnionFind) union(a int, b int) bool {
	aRoot := uf.findRoot(a)
	bRoot := uf.findRoot(b)
	if aRoot == bRoot {
		return false
	}
	if uf.size[aRoot] < uf.size[bRoot] {
		tmp := aRoot
		aRoot = bRoot
		bRoot = tmp
	}
	uf.parent[aRoot] = bRoot
	uf.size[bRoot] += uf.size[aRoot]
	uf.size[aRoot] = 0
	return true
}
