package String;
/*The API: int read4(char *buf) reads 4 characters at a time from a file.

The return value is the actual number of characters read. For example, it returns 3 if there is only 3 characters left in the file.

By using the read4 API, implement the function int read(char *buf, int n) that reads n characters from the file.

Example
Example 1

Input:
"filetestbuffer"
read(6)
read(5)
read(4)
read(3)
read(2)
read(1)
read(10)
Output:
6, buf = "filete"
5, buf = "stbuf"
3, buf = "fer"
0, buf = ""
0, buf = ""
0, buf = ""
0, buf = ""
Example 2

Input:
"abcdef"
read(1)
read(5)
Output:
1, buf = "a"
5, buf = "bcdef"
Notice
The read function may be called multiple times.*/
public class ReadNcharactersGivenRead4II extends Reader4 {
	/**
     * @param buf destination buffer
     * @param n maximum number of characters to read
     * @return the number of characters read
     */
	char[] temp = new char[4];
    int end = 0, start = 0;
    public int read(char[] buf, int n) {
        // Write your code here
        for(int i = 0; i < n; i++){
            if(start == end){      // temp is empty
                end = read4(temp);    // read in new data to temp
                start = 0;
                if(end == 0){
                    return i;
                }
            }
            buf[i] = temp[start++]; // read out from temp
        }
        return n;
    }
}
