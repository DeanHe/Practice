package BinarySearch;
/*An image is represented by a binary matrix with 0 as a white pixel and 1 as a black pixel. The black pixels are connected, i.e., there is only one black region. Pixels are connected horizontally and vertically. Given the location (x, y) of one of the black pixels, return the area of the smallest (axis-aligned) rectangle that encloses all black pixels.
Example
For example, given the following image:

[
  "0010",
  "0110",
  "0100"
]
and x = 0, y = 2,
Return 6.*/
public class SmallestRectangleEnclosingBlackPixels {
	/**
     * @param image: a binary matrix with '0' and '1'
     * @param x: the location of one of the black pixels
     * @param y: the location of one of the black pixels
     * @return: an integer
     */
	char[][] img;
	int rows, cols;
    public int minArea(char[][] image, int x, int y) {
        // write your code here
    	if (image == null || image.length == 0 || image[0].length == 0) {
    		return 0;
    	}
    	img = image;
    	rows = img.length;
    	cols = img[0].length;
    	
    	int left = findLeft(0, y);
    	int right = findRight(y, cols - 1);
    	int top = findTop(0, x);
    	int bot = findBottom(x, rows - 1);
    	return (right - left + 1) * (bot - top + 1);
    }
    
    private int findLeft(int start, int end) {
    	while (start + 1 < end) {
    		int mid = start + (end - start) / 2;
    		if (isEmptyColumn(mid)) {
    			start = mid;
    		} else {
    			end = mid;
    		}
    	}
    	if (isEmptyColumn(start)) {
    		return end;
    	}
    	return start;
    }
    
    private int findRight(int start, int end) {
    	while (start + 1 < end) {
    		int mid = start + (end - start) / 2;
    		if (isEmptyColumn(mid)) {
    			end = mid;
    		} else {
    			start = mid;
    		}
    	}
    	if (isEmptyColumn(end)) {
			return start;
		}
		return end;
    }
    
    private int findTop(int start, int end) {
    	while (start + 1 < end) {
    		int mid = start + (end - start) / 2;
    		if (isEmptyRow(mid)) {
    			start = mid;
    		} else {
    			end = mid;
    		}
    	}
    	if (isEmptyRow(start)) {
			return end;
		}
		return start;
    }
    
    private int findBottom(int start, int end) {
    	while (start + 1 < end) {
    		int mid = start + (end - start) / 2;
    		if (isEmptyRow(mid)) {
    			end = mid;
    		} else {
    			start = mid;
    		}
    	}
    	if (isEmptyRow(end)) {
			return start;
		}
		return end;
    }
    
    private boolean isEmptyColumn(int col) {
    	for (int r = 0; r < rows; r++) {
    		if (img[r][col] == '1') {
    			return false;
    		}
    	}
    	return true;
    }
    private boolean isEmptyRow(int row) {
    	for (int c = 0; c < cols; c++) {
    		if (img[row][c] == '1') {
    			return false;
    		}
    	}
    	return true;
    }
}
