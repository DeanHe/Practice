package hashMap;

import binarySearch.FindFirstAndLastPositionOfElementInSortedArray;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/*
Given two vectors, find the dot product of the two vectors. (lots of repeating number)


    Input: A = [1,1,1,2,2,2,3,3,3] B = [1,1,1,2,2,2,3,3,3]
    Output: 1*1 + 1*1 + 1*1 + 2*2 + 2*2 + 2*2 + 3*3 + 3*3 + 3*3 = 42
    Input: A = [1,1,1,2,2,2,3,3,3] B = [1,1,1,1,2,2,3,3,3]
    Output: 1*1 + 1*1 + 1*1 + 2*1 + 2*2 + 2*2 + 3*3 + 3*3 + 3*3 = 40

    the vector is sorted

    These vectors have a lot of repeating numbers, what data structure to use for this? How do dot product with this new data structure?

 */
public class DotProductOfVectorsWithRepeatValues {
    FindFirstAndLastPositionOfElementInSortedArray helper;
    int doProduct(int[] vector1, int[] vector2) {
        helper = new FindFirstAndLastPositionOfElementInSortedArray();
        TreeMap<Integer, int[]> map1 = new TreeMap<>();
        TreeMap<Integer, int[]> map2 = new TreeMap<>();
        build(vector1, map1);
        build(vector2, map2);
        int res = 0;
        Iterator<Map.Entry<Integer, int[]>> i1 = map1.entrySet().iterator();
        Iterator<Map.Entry<Integer, int[]>> i2 = map2.entrySet().iterator();
        Map.Entry<Integer, int[]> entry1 = i1.next();
        Map.Entry<Integer, int[]> entry2 = i2.next();
        while(entry1 != null && entry2 != null){
            int[] range1 = entry1.getValue();
            int[] range2 = entry2.getValue();
            int maxStart = Math.max(range1[0], range2[0]);
            int minEnd = Math.min(range1[1], range2[1]);
            res += (minEnd - maxStart + 1) * entry1.getKey() * entry2.getKey();
            if(range1[1] > range2[1]){
                if(i2.hasNext()){
                    entry2 = i2.next();
                } else {
                    entry2 = null;
                }
            } else if(range1[1] < range2[1]){
                if(i1.hasNext()){
                    entry1 = i1.next();
                } else {
                    entry1 = null;
                }
            } else {
                if(i1.hasNext()){
                    entry1 = i1.next();
                } else {
                    entry1 = null;
                }
                if(i2.hasNext()){
                    entry2 = i2.next();
                } else {
                    entry2 = null;
                }
            }
        }
        return res;
    }

    private void build(int[] vector, TreeMap<Integer, int[]> map) {
        for(int i = 0; i < vector.length;){
            int target = vector[i];
            int[] range = helper.searchRange(vector, target);
            map.put(target, range);
            i = range[1] + 1;
        }
    }
}

