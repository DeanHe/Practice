package designDataStructure;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/*
implement your own hash map that support following operations:

int get(int key);
void put(int key, int value);
void putAll(int value);
 */
public class MyHashMapGoogle {
    int snapshotId;
    int snapshot;
    Map<Integer, TreeMap<Integer, Integer>> map;

    public MyHashMapGoogle() {
        snapshotId = 0;
        map = new HashMap<>();
    }

    public void put(int key, int value) {
        map.computeIfAbsent(key, x -> new TreeMap<>()).put(snapshotId, value);
    }

    public int get(int key) {
        if(!map.containsKey(key)){
            if(snapshot == 0){
                return -1;
            }
            return snapshot;
        } else {
            int savedSnapshotId = map.get(key).lastKey();
            if(savedSnapshotId < snapshotId){
                return snapshot;
            } else {
                return map.get(key).lastEntry().getValue();
            }
        }
    }

    public void putAll(int num) {
        snapshot = num;
        snapshotId++;
    }

}
