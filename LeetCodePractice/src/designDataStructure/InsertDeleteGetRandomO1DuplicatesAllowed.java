package designDataStructure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;

/*
Design a data structure that supports all following operations in average O(1) time.

Note: Duplicate elements are allowed.
insert(val): Inserts an item val to the collection.
remove(val): Removes an item val from the collection if present.
getRandom: Returns a random element from current collection of elements. The probability of each element being returned is linearly related to the number of same value the collection contains.
Example:

// Init an empty collection.
RandomizedCollection collection = new RandomizedCollection();

// Inserts 1 to the collection. Returns true as the collection did not contain 1.
collection.insert(1);

// Inserts another 1 to the collection. Returns false as the collection contained 1. Collection now contains [1,1].
collection.insert(1);

// Inserts 2 to the collection, returns true. Collection now contains [1,1,2].
collection.insert(2);

// getRandom should return 1 with the probability 2/3, and returns 2 with the probability 1/3.
collection.getRandom();

// Removes 1 from the collection, returns true. Collection now contains [1,2].
collection.remove(1);

// getRandom should return 1 and 2 both equally likely.
collection.getRandom();
 */
public class InsertDeleteGetRandomO1DuplicatesAllowed {

    ArrayList<Integer> ls;
    HashMap<Integer, Set<Integer>> index;
    Random random;
    /** Initialize your data structure here. */
    public InsertDeleteGetRandomO1DuplicatesAllowed() {
        ls = new ArrayList<>();
        index = new HashMap<>();
        random = new Random();
    }

    /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
    public boolean insert(int val) {
        if(!index.containsKey(val)){
            index.put(val, new LinkedHashSet<>());
        }
        index.get(val).add(ls.size());
        ls.add(val);
        return index.get(val).size() == 1;
    }

    /** Removes a value from the collection. Returns true if the collection contained the specified element. */
    public boolean remove(int val) {
        if(!index.containsKey(val) || index.get(val).size() == 0){
            return false;
        }
        int pos = index.get(val).iterator().next();
        index.get(val).remove(pos);
        int lastVal = ls.get(ls.size() - 1);
        ls.set(pos, lastVal);
        index.get(lastVal).add(pos);
        index.get(lastVal).remove(ls.size() - 1);
        ls.remove(ls.size() - 1);
        return true;
    }

    /** Get a random element from the collection. */
    public int getRandom() {
        return ls.get(random.nextInt(ls.size()));
    }
}
/**
 * Your RandomizedCollection object will be instantiated and called as such:
 * RandomizedCollection obj = new RandomizedCollection();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */
