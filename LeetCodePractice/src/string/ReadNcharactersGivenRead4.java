package string;
/*
The API: int read4(char *buf) reads 4 characters at a time from a file.

The return value is the actual number of characters read. For example, it returns 3 if there is only 3 characters left in the file.

By using the read4 API, implement the function int read(char *buf, int n) that reads n characters from the file.

The read function will only be called once for each test case.

analysis:
scenario 1: input is end of file
secnario 2: temp will be fully copy over
*/
public class ReadNcharactersGivenRead4 extends Reader4 {
	/**
     * @param buf destination buffer
     * @param n maximum number of characters to read
     * @return the number of characters read
     */
    public int read(char[] buf, int n) {
        // Write your code here
    	// end of file flag
    	boolean eof = false;
    	// total bytes have read
    	int total = 0;
    	// temp buffer
    	char[] temp = new char[4];
    	while(!eof && total < n){
    		int read = read4(temp);
    		// check if it's the end of the file
    		eof = read < 4;
    		// get the actual read with n limit
    		if(total + read > n){
    			read = n - total;
    		}
    		// copy from temp buffer to buf
    		for(int i = 0; i < read; i++){
    			buf[total++] = temp[i];
    		}
    	}
    	return total;
    }
}
