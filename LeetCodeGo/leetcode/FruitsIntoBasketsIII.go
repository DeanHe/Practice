package main

type SegmentTree struct {
	size int
	data []int
}

func NewSegmentTree(n int) *SegmentTree {
	sz := NextPowerOf2(n)
	return &SegmentTree{sz, make([]int, 2*sz)}
}

// FindFirstGreaterOrEqual returns the index of the first element >= val, or -1 if none exist.
func (tree *SegmentTree) FindFirstGreaterOrEqual(target int) int {
	if tree.data[1] < target {
		return -1
	}
	i := 1
	for i < tree.size {
		if target <= tree.data[2*i] {
			i *= 2
		} else {
			i = 2*i + 1
		}
	}
	return i
}

func (tree *SegmentTree) Update(i int, val int) {
	tree.data[i] = val
	for i > 1 {
		i >>= 1
		tree.data[i] = Max(tree.data[2*i], tree.data[2*i+1])
	}
}

// nextPowerOf2 returns the smallest power of 2 greater than or equal to n.
func NextPowerOf2(n int) int {
	power := 1
	for power < n {
		power *= 2
	}
	return power
}

func Max(a int, b int) int {
	if a < b {
		return b
	}
	return a
}

func numOfUnplacedFruits(fruits []int, baskets []int) int {
	res := 0
	tree := NewSegmentTree(len(fruits))
	for i, b := range baskets {
		i += tree.size
		tree.Update(i, b)
	}
	for _, fruit := range fruits {
		i := tree.FindFirstGreaterOrEqual(fruit)
		if i == -1 {
			res += 1
		} else {
			tree.Update(i, -1)
		}
	}
	return res
}
