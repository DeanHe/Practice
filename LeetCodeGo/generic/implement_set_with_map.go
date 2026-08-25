package generic

type Set[T comparable] map[T]struct{}

func NewSet[T comparable](n int) Set[T] {
	mp := make(map[T]struct{}, n)
	return Set[T](mp)
}

func (set Set[T]) Add(ele T) {
	set[ele] = struct{}{}
}

func (set Set[T]) Remove(ele T) {
	delete(set, ele)
}

func (set Set[T]) Exists(ele T) bool {
	_, ok := set[ele]
	return ok
}

func (set Set[T]) Range(f func(ele T)) {
	for key := range set {
		f(key)
	}
}

func (set Set[T]) Len() int {
	return len(set)
}
