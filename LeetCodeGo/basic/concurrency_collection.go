package basic

import "sync"

func concurrent_map() {
	mp := sync.Map{}
	wg := sync.WaitGroup{}
	wg.Add(2)

	go func() {
		defer wg.Done()
		for i := 0; i < 100; i++ {
			mp.Store(i, i)
		}
	}()
	go func() {
		defer wg.Done()
		for i := 0; i < 100; i++ {
			mp.Store(i, -i)
		}
	}()
	wg.Wait()
}

func main() {
	concurrent_map()
}
