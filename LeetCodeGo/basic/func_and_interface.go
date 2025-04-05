package basic

type SearchEngine struct {
	recallers []Recaller
	sorter    Sorter
}

type SearchEngineII struct {
	recalls []func() []int
	sort    func([]int) []int
}

type Recaller interface {
	Recall() []int
}

type Sorter interface {
	Sort([]int) []int
}

func recall1() []int {
	return nil
}

func sort1([]int) []int {
	return nil
}

type Record struct {
}

func (Record) Recall() []int {
	return recall1()
}

func (Record) Sort(data []int) []int {
	return nil
}

func main() {
	ls := make([]int, 0, 10)
	se := SearchEngine{
		recallers: []Recaller{Record{}},
		sorter:    Record{},
	}

	se.recallers[0].Recall()
	se.sorter.Sort(ls)

	se2 := SearchEngineII{
		recalls: []func() []int{recall1},
		sort:    sort1,
	}
	se2.sort(ls)

	var empty any
}
