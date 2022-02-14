package reservoirSampling;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/*
Given an array of integers, find the most occurring element of the array and return any one of its indexes randomly with equal probability.

Examples:

Input:
arr[] = [-1, 4, 9, 7, 7, 2, 7, 3, 0, 9, 6, 5, 7, 8, 9]

Output:
Element with maximum frequency present at index 6
OR
Element with maximum frequency present at Index 3
OR
Element with maximum frequency present at index 4
OR
Element with maximum frequency present at index 12

All outputs above have equal probability.
 */
public class FindAniIndexOfMaximumOccurringElementWithEqualProbability {
    public int findRandomIndexOfMax(int arr[]){
        Random rand = new Random();
        Map<Integer, Integer> freq = new HashMap<>();
        int target = 0, maxFreq = 0;
        for(int n : arr){
            freq.put(n, freq.getOrDefault(n, 0) + 1);
            if(freq.get(n) > maxFreq){
                maxFreq = freq.get(n);
                target = n;
            }
        }
        int res = 0, count = 1;
        for(int i = 0; i < arr.length; i++){
            if(arr[i] == target){
                int prob = rand.nextInt(count);
                if(prob == 0){
                    res = i;
                }
                count++;
            }
        }
        return res;
    }
}
