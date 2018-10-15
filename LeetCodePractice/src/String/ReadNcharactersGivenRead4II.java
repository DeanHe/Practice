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
    char[] buffer = new char[4];
    int head = 0, tail = 0;
    public int read(char[] buf, int n) {
        // Write your code here
       int i = 0;
        while (i < n) {
            if (head == tail) {               // queue is empty
                head = 0;
                tail = read4(buffer);         // enqueue
                if (tail == 0) {
                    break;
                }
            }
            while (i < n && head < tail) {
                buf[i++] = buffer[head++];    // dequeue
            }
        }
        return i;
    }
}
