package main

import (
	"cmp"
	"slices"
)

type Robot struct {
	position  int
	health    int
	direction string
	idx       int
}

func survivedRobotsHealths(positions []int, healths []int, directions string) []int {
	robots := []Robot{}
	for i := 0; i < len(positions); i++ {
		robots = append(robots, Robot{
			position:  positions[i],
			health:    healths[i],
			direction: string(directions[i]),
			idx:       i})
	}
	slices.SortFunc(robots, func(a, b Robot) int {
		return cmp.Compare(a.position, b.position)
	})
	stack := []Robot{}
	for _, robot := range robots {
		if robot.direction == "R" {
			stack = append(stack, robot)
		} else {
			collide(&stack, robot)
		}
	}
	slices.SortFunc(stack, func(a, b Robot) int {
		return cmp.Compare(a.idx, b.idx)
	})
	res := []int{}
	for _, robot := range stack {
		res = append(res, robot.health)
	}
	return res
}

func collide(stack *[]Robot, cur Robot) {
	for len(*stack) > 0 && (*stack)[len(*stack)-1].direction == "R" && (*stack)[len(*stack)-1].health < cur.health {
		*stack = (*stack)[:len(*stack)-1]
		cur.health--
	}
	if len(*stack) > 0 && (*stack)[len(*stack)-1].direction == "R" {
		if (*stack)[len(*stack)-1].health == cur.health {
			*stack = (*stack)[:len(*stack)-1]
		} else {
			(*stack)[len(*stack)-1].health--
		}
	} else {
		*stack = append(*stack, cur)
	}
}
