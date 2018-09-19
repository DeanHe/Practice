import java.util.*;
import java.util.Iterator;

public class ZigzagIterator {
	Iterator<Integer> it1;
	Iterator<Integer> it2;
	int turns;
	
	public ZigzagIterator(List<Integer> v1, List<Integer> v2){
		this.it1 = v1.iterator();
		this.it2 = v2.iterator();
		turns = 0;
	}
	
	public int next(){
		if(!hasNext()){
			return 0;
		}
		if((turns % 2 == 1 && it1.hasNext()) || (!it2.hasNext())){
			return it1.next();
		} else if((turns % 2 == 0 && it2.hasNext()) || (!it1.hasNext())){
			return it2.next();
		}
		return 0;
	}
	
	public boolean hasNext(){
		return it1.hasNext() || it2.hasNext();
	}
}
