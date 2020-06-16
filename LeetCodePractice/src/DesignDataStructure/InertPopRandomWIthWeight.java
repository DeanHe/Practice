package DesignDataStructure;

import java.util.ArrayList;
import java.util.Random;

public class InertPopRandomWIthWeight {
	ArrayList<Integer> ls;
	ArrayList<Double> weights;
	Random random;
	/** Initialize your data structure here. */
    public InertPopRandomWIthWeight() {
    	ls = new ArrayList<>();
    	random = new Random();
    }
    
    /** Inserts a value to the set */
    public void insert(int val, double weight) {
    	weights.add(weight);
        ls.add(val);
    }
    
    /** pop a random value from the set */
    public int popAndRemove() {
    	int pos = random.nextInt(ls.size());
    	int last = ls.size() - 1;
    	double percent = pos * 1.0 / last;
    	int i;
    	for(i = 0; i < weights.size(); i++){
    		percent -= weights.get(i);
    		if(percent <= 0){
    			break;
    		}
    	}
    	int val = ls.get(i);
        int lastVal = ls.get(last);
        ls.set(i, lastVal);
        ls.remove(last);
        weights.remove(i);
        return val;
    }
}
