package Array;

import java.util.TreeMap;
/*
Given an array find count of triplet of (A[i], A[j], A[k])
where i < j < k and A[i] < A[j] < A[k];

test case:
int[] arr = {1, 2, 3, 5, 4};
 */
public class CountTripletOfAscending {
    public int countTriplet(int[] input){
        int res = 0;
        TreeMap<Integer, Integer> leftMap = new TreeMap<>();
        TreeMap<Integer, Integer> rightMap = new TreeMap<>();
        for(int i = 0; i < input.length; i++){
            int cur = input[i];
            if(i == 0){
                leftMap.put(cur, 0);
            } else {
                leftMap.put(cur, leftMap.headMap(cur).size());
            }
        }
        for(int i = input.length - 1; i >= 0; i--){
            int cur = input[i];
            if(i == input.length - 1){
                rightMap.put(cur, 0);
            } else {
                rightMap.put(cur, rightMap.tailMap(cur).size());
            }
        }
        for(int i = 1; i < input.length - 1; i++){
            int mid = input[i];
            res += leftMap.get(mid) * rightMap.get(mid);
        }
        return res;
    }
}
