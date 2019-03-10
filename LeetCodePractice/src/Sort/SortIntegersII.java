package Sort;

public class SortIntegersII {
	/**
     * @param A an integer array
     * @return void
     */
	//merge sort
    public void sortIntegers2(int[] A) {
    	//merge sort
        if(A == null || A.length < 2){
            return;
        }
        int[] temp = new int[A.length];
        mergeSort(A, 0, A.length - 1, temp);
        quickSort(A, 0, A.length - 1);
    }
    private void mergeSort(int[] A, int start, int end, int[] temp){
        if(start >= end){
            return;
        }
        int mid = start + (end - start) / 2;
        mergeSort(A, start, mid, temp);
        mergeSort(A, mid + 1, end, temp);
        merge(A, start, mid, end, temp);
    }
    private void merge(int[] A, int start, int mid, int end, int[] temp){
        int left = start;
        int right = mid + 1;
        int i = start;
        while(left <= mid && right <= end){
            if(A[left] < A[right]){
                temp[i++] = A[left++];
            } else {
                temp[i++] = A[right++];
            }
        }
        while(left <= mid){
            temp[i++] = A[left++];
        }
        while(right <= end){
            temp[i++] = A[right++];
        }
        for(i = start; i <= end; i++){
            A[i] = temp[i];
        }
    }
    
    private void quickSort(int[] array, int start, int end){
        if(start >= end){
            return;
        }
        int index = partition(array, start, end);
        quickSort(array, start, index - 1);
        quickSort(array, index + 1, end);
    }
     
    private int partition(int[] array, int start, int end){
        int pivot = array[end];
        int i = start;
        for(int j = start; j < end; j++){
            if(array[j] <= pivot){
                swap(array, i++, j);
            }
        }
        swap(array, i, end);
        return i;
    }
     
    private void swap(int [] array, int i, int j){
        int temp;
        temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
