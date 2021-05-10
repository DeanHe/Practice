package sort;
/*
Given an integer array, sort it in ascending order. Use selection sort, bubble sort, insertion sort or any O(n2) algorithm.

Example
Example 1:
	Input:  [3, 2, 1, 4, 5]
	Output: [1, 2, 3, 4, 5]

	Explanation:
	return the sorted array.

Example 2:
	Input:  [1, 1, 2, 1, 1]
	Output: [1, 1, 1, 1, 2]

	Explanation:
	return the sorted array.

	analysis:

SORTING ALGORITHM	TIME COMPLEXITY	                        SPACE COMPLEXITY
                    Best Case	Average Case	Worst Case	Worst Case
Bubble sort	        O(N)	    O(N2)	        O(N2)	    O(1)
Selection sort	    O(N2)	    O(N2)	        O(N2)	    O(1)
Insertion sort	    O(N)	    O(N2)	        O(N2)	    O(1)

 */
public class SortIntegers {
    /**
     * @param array: an integer array
     * @return: nothing
     */
    public void selectionSort(int[] array) {
        for(int i = 0; i < array.length; i++){
            int minIdx = i;
            for(int j = i + 1; j < array.length; j++){
                if(array[j] < array[i]){
                    minIdx = j;
                }
            }
            if(minIdx != i){ // swap
                int temp = array[i];
                array[i] = array[minIdx];
                array[minIdx] = temp;
            }
        }
    }

    public void bubbleSort(int[] array) {
        for(int i = 0; i < array.length - 1; i++){
            for(int j = 0; j < array.length - 1 - i; j++){
                if(array[j] > array[j + 1]){ // swap
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }

    //insertion sort
    public void insertionSort(int[] array) {
        int j, key;
        for (int i = 1; i < array.length; i++) {
            key = array[i];
            j = i - 1;
            while (j >= 0 && key < array[j]) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = key;
        }
    }
}
