package designDataStructure.iterators;

public class GoogleIteratorPair {

	String val;
	int cnt;
	public GoogleIteratorPair(String val, int cnt){
		this.val = val;
		this.cnt = cnt;
	}

	@Override
	public String toString() {
		return val + ":" + cnt;
	}
}


