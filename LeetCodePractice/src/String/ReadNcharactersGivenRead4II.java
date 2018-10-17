package String;
//The API: int read4(char *buf) reads 4 characters at a time from a file.
//The return value is the actual number of characters read. For example, it returns 3 if there is only 3 characters left in the file.
//By using the read4 API, implement the function int read(char *buf, int n) that reads n characters from the file.
//The read function may be called multiple times for each test case.
public class ReadNcharactersGivenRead4II extends Reader4 {
	/**
     * @param buf destination buffer
     * @param n maximum number of characters to read
     * @return the number of characters read
     */
	char[] buf4 = new char[4];
    int read4EndPos = 0, read4curPos = 0;
    public int read(char[] buf, int n) {
        // Write your code here
        for(int i = 0; i < n; i++){
            if(read4curPos == read4EndPos){
                read4EndPos = read4(buf4);
                read4curPos = 0;
                if(read4EndPos == 0){
                    return i;
                }
            }
            buf[i] = buf4[read4curPos++];
        }
        return n;
    }
}
