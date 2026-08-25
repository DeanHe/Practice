package main

import "container/heap"

type Item struct {
	totalTime  int64
	workerTime int
	index      int
}

type PriorityQueue []*Item

func (pq PriorityQueue) Len() int {
	return len(pq)
}

func (pq PriorityQueue) Less(i, j int) bool {
	if pq[i].totalTime != pq[j].totalTime {
		return pq[i].totalTime < pq[j].totalTime
	}

	if pq[i].workerTime != pq[j].workerTime {
		return pq[i].workerTime < pq[j].workerTime
	}

	if pq[i].index != pq[j].index {
		return pq[i].index < pq[j].index
	}

	return true
}

func (pq PriorityQueue) Swap(i, j int) {
	pq[i], pq[j] = pq[j], pq[i]
}

func (pq *PriorityQueue) Push(x any) {
	item := x.(*Item)
	*pq = append(*pq, item)
}

func (pq *PriorityQueue) Pop() any {
	old := *pq
	n := len(old)
	res := old[n-1]
	old[n-1] = nil
	*pq = old[0 : n-1]
	return res
}

func minNumberOfSeconds(mountainHeight int, workerTimes []int) int64 {
	pq := make(PriorityQueue, len(workerTimes))
	for i, workerTime := range workerTimes {
		pq[i] = &Item{totalTime: int64(workerTime), workerTime: workerTime, index: 1}
	}
	heap.Init(&pq)
	for mountainHeight > 1 {
		mountainHeight--
		item := heap.Pop(&pq).(*Item)
		item.index++
		newItem := &Item{
			totalTime:  item.totalTime + int64(item.workerTime*item.index),
			workerTime: item.workerTime,
			index:      item.index,
		}
		heap.Push(&pq, newItem)
	}
	item := heap.Pop(&pq).(*Item)
	return int64(item.totalTime)
}
