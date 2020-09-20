package SweepLine;
/*
Given n ranges of the form L and R, the task is to find the maximum occurred integer in all the ranges. If more than one such integer exits, print the smallest one.
0 <= Li, Ri < 1000000.
Examples :

Input : L1 = 1 R1 = 15
        L2 = 4 R2 = 8
        L3 = 3 R3 = 5
        L3 = 1 R3 = 4
Output : 4

Input : L1 = 1 R1 = 15
        L2 = 5 R2 = 8
        L3 = 9 R3 = 12
        L4 = 13 R4 = 20
        L5 = 21 R5 = 30
Output : 5
Numbers having maximum occurrence i.e 2  are 5, 6,
7, 8, 9, 10, 11, 12, 13, 14, 15. The smallest number
among all are 5.
*/
public class MaximumOccurredIntegerInNranges {
	int MAX = 1000000; 
	// Return the maximum occurred element in all ranges.
	int maximumOccuredElement(int[] L, int[] R) { 
        int[] axis = new int[MAX];
        int len = L.length;
        // Adding +1 at Li index and  
        // substracting 1 at Ri index. 
        for(int i = 0; i < len; i++){
        	axis[L[i]] = 1;
        	axis[R[i] - 1] = -1;
        }
        // Finding prefix sum and index 
        // having maximum prefix sum. 
        int most = axis[0];
        int ind = 0;
        for(int i = 1; i < MAX; i++){
        	axis[i] += axis[i - 1];
        	if(most < axis[i]){
        		most = axis[i];
        		ind = i;
        	}
        }
        return ind;
    } 
}
