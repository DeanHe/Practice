package DesignDataStructure;

import java.util.HashMap;
import java.util.Map;

/*
implement your own hash map that support following operations:

insert x, y - insert an object with key x and value y
get x - return the value of an object with key x
addToKey x - add x to all keys in map
addToValue y add y to all values in map
 */
public class MyHashMap {
    int keyInc, valInc;
    Map<Integer, Integer> map;

    public MyHashMap() {
        keyInc = 0;
        valInc = 0;
        map = new HashMap<>();
    }

    public void insert(int key, int value) {
        int originKey = key - keyInc;
        int originVal = value - valInc;
        map.put(originKey, originVal);
    }

    public int get(int key) {
        int originKey = key - keyInc;
        if (!map.containsKey(originKey)) {
            return -1;
        }
        return map.get(originKey) + valInc;
    }

    public void addToKey(int num) {
        keyInc += num;
    }

    public void addToValue(int num) {
        valInc += num;
    }
}
