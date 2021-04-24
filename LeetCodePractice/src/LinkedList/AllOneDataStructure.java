package LinkedList;

import java.util.*;

/*Implement a data structure supporting the following operations:

Inc(Key) - Inserts a new key with value 1. Or increments an existing key by 1. Key is guaranteed to be a non-empty string.
Dec(Key) - If Key's value is 1, remove it from the data structure. Otherwise decrements an existing key by 1. If the key does not exist, this function does nothing. Key is guaranteed to be a non-empty string.
GetMaxKey() - Returns one of the keys with maximal value. If no element exists, return an empty string "".
GetMinKey() - Returns one of the keys with minimal value. If no element exists, return an empty string "".
Challenge: Perform all these in O(1) time complexity.
*/
public class AllOneDataStructure {
	// each Bucket contains all the keys with the same count
	private class Bucket {
		int count;
		Set<String> keySet;
		Bucket next;
		Bucket pre;
		public Bucket(int cnt){
			count = cnt;
			keySet = new HashSet<>();
		}
	}
	// maintain a doubly linked list of Buckets
	private Bucket head;
	private Bucket tail;
	// for accessing a specific Bucket among the Bucket list in O(1) time
	private Map<Integer, Bucket> countBucketMap;
	// keep track of count of keys
	private Map<String, Integer> keyCountMap;
	
    /** Initialize your data structure here. */
    public AllOneDataStructure() {
        head = new Bucket(Integer.MIN_VALUE);
        tail = new Bucket(Integer.MAX_VALUE);
        head.next = tail;
        tail.pre = head;
        countBucketMap = new HashMap<>();
        keyCountMap = new HashMap<>();
    }
    
    /** Inserts a new key <Key> with value 1. Or increments an existing key by 1. */
    public void inc(String key) {
        if(keyCountMap.containsKey(key)){
        	changeKey(key, 1);
        } else {
        	keyCountMap.put(key, 1);
        	if(head.next.count != 1){
        		addBucketAfter(new Bucket(1), head);
        	}
        	head.next.keySet.add(key);
        	countBucketMap.put(1, head.next);
        }
    }
    
    /** Decrements an existing key by 1. If Key's value is 1, remove it from the data structure. */
    public void dec(String key) {
    	if(keyCountMap.containsKey(key)){
    		int count = keyCountMap.get(key);
    		if(count == 1){
    			keyCountMap.remove(key);
    			removeKeyFromBucket(head.next, key);
    		} else {
    			changeKey(key, -1);
    		}
        }
    }
    
    /** Returns one of the keys with maximal value. */
    public String getMaxKey() {
        if(tail.pre == head){
        	return "";
        } else {
        	return tail.pre.keySet.iterator().next();
        }
    }
    
    /** Returns one of the keys with Minimal value. */
    public String getMinKey() {
        if(head.next == tail){
        	return "";
        } else {
        	return head.next.keySet.iterator().next();
        }
    }
    
    // helper function to make change on given key according to offset
    private void changeKey(String key, int offset){
    	int count = keyCountMap.get(key);
    	keyCountMap.put(key, count + offset);
    	Bucket curBucket = countBucketMap.get(count);
    	Bucket newBucket;
    	if(countBucketMap.containsKey(count + offset)){
    		newBucket = countBucketMap.get(count + offset);
    	} else {
    		newBucket = new Bucket(count + offset);
    		countBucketMap.put(count + offset, newBucket);
    		if(offset == 1){
    			addBucketAfter(newBucket, curBucket);
    		} else {
    			//offset == -1
    			addBucketAfter(newBucket, curBucket.pre);
    		}
    	}
    	newBucket.keySet.add(key);
    	removeKeyFromBucket(curBucket, key);
    }
    
    private void removeKeyFromBucket(Bucket bucket, String key){
    	bucket.keySet.remove(key);
    	if(bucket.keySet.size() == 0){
    		removeBucketFromList(bucket);
    		countBucketMap.remove(bucket.count);
    	}
    }
    
    private void removeBucketFromList(Bucket bucket){
    	bucket.pre.next = bucket.next;
    	bucket.next.pre = bucket.pre;
    	bucket.next = null;
    	bucket.pre = null;
    }
    
    private void addBucketAfter(Bucket newBucket, Bucket preBucket){
    	newBucket.next = preBucket.next;
    	newBucket.pre = preBucket;
    	preBucket.next.pre = newBucket;
    	preBucket.next = newBucket;
    }
}
/**
 * Your AllOne object will be instantiated and called as such:
 * AllOne obj = new AllOne();
 * obj.inc(key);
 * obj.dec(key);
 * string param_3 = obj.getMaxKey();
 * string param_4 = obj.getMinKey();
 */