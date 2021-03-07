package Sort;
/*

tag:
merge sort template
quick sort template
 */
public class SortIntegersII {
    /**
     * @param A an integer array
     * @return void
     */
    //merge sort
    public void sortIntegers2(int[] A) {
        //merge sort
        if (A == null || A.length < 2) {
            return;
        }
        int[] temp = new int[A.length];
        mergeSort(A, 0, A.length - 1, temp);
        quickSort(A, 0, A.length - 1);
        heapSort(A);
    }

    private void mergeSort(int[] A, int start, int end, int[] temp) {
        if (start >= end) {
            return;
        }
        int mid = start + (end - start) / 2;
        mergeSort(A, start, mid, temp);
        mergeSort(A, mid + 1, end, temp);
        merge(A, start, mid, end, temp);
    }

    private void merge(int[] A, int start, int mid, int end, int[] temp) {
        int left = start;
        int right = mid + 1;
        int i = start;
        while (left <= mid && right <= end) {
            if (A[left] < A[right]) {
                temp[i++] = A[left++];
            } else {
                temp[i++] = A[right++];
            }
        }
        while (left <= mid) {
            temp[i++] = A[left++];
        }
        while (right <= end) {
            temp[i++] = A[right++];
        }
        for (i = start; i <= end; i++) {
            A[i] = temp[i];
        }
    }

    // quick sort
    private void quickSort(int[] array, int start, int end) {
        if (start >= end) {
            return;
        }
        int index = partition(array, start, end);
        quickSort(array, start, index - 1);
        quickSort(array, index + 1, end);
    }

    private int partition(int[] array, int start, int end) {
        int pivot = array[end];
        int i = start;
        for (int j = start; j < end; j++) {
            if (array[j] <= pivot) {
                swap(array, i++, j);
            }
        }
        swap(array, i, end);
        return i;
    }

    private void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    // heap sort
    private void heapSort(int[] array) {
        int len = array.length;
        // Build heap (rearrange array)
        for (int i = len / 2 - 1; i >= 0; i--) {
            heapify(array, len, i);
        }
        // One by one extract an element from heap
        for (int i = len - 1; i >= 0; i--) {
            // Move current root to end
            swap(array, i, 0);
            // call max heapify on the reduced heap
            heapify(array, i, 0);
        }
    }

    // To heapify a subtree rooted with node i which is
    // an index in arr[]. len is size of heap, this is a max heap
    private void heapify(int[] array, int len, int i) {
        int maxIdx = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        if (left < len && array[left] > array[maxIdx]) {
            maxIdx = left;
        }
        if (right < len && array[right] > array[maxIdx]) {
            maxIdx = right;
        }
        if (maxIdx != i) {
            swap(array, maxIdx, i);
        }
        heapify(array, len, maxIdx);
    }
}
