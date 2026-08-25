package generic

import (
	"fmt"
	"testing"
)

func TestSet(t *testing.T) {
	set := NewSet[int](10)
	set.Add(1)
	set.Add(2)
	set.Add(3)

	if set.Len() != 3 {
		t.Fail()
	}

	if !set.Exists(1) {
		t.Fail()
	}

	set.Remove(1)
	if set.Exists(1) {
		t.Fail()
	}

	if set.Len() != 2 {
		t.Fail()
	}

	set.Range(func(a int) {
		fmt.Printf("%d\n", a)
	})

}
