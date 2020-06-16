package OOD;
/*
Implement a simple client for GFS (Google File System, a distributed file system), it provides the following methods:

read(filename). Read the file with given filename from GFS.
write(filename, content). Write a file with given filename & content to GFS.
There are two private methods that already implemented in the base class:

readChunk(filename, chunkIndex). Read a chunk from GFS.
writeChunk(filename, chunkIndex, chunkData). Write a chunk to GFS.
To simplify this question, we can assume that the chunk size is chunkSize bytes. (In a real world system, it is 64M). The GFS Client's job is splitting a file into multiple chunks (if need) and save to the remote GFS server. chunkSize will be given in the constructor. You need to call these two private methods to implement read & write methods.
*/
import java.util.*;

//Definition of BaseGFSClient
class BaseGFSClient {
      private Map<String, String> chunk_list;
      public BaseGFSClient() {}
      public String readChunk(String filename, int chunkIndex) {
          // Read a chunk from GFS
    	  return "";
      }
      public void writeChunk(String filename, int chunkIndex,
                             String content) {
          // Write a chunk to GFS
      }
  }
 
public class GFSClient extends BaseGFSClient {

    public int chunkSize;
    public Map<String, Integer> chunkNum;

    public GFSClient(int chunkSize) {
        // initialize your data structure here
        this.chunkSize = chunkSize;
        this.chunkNum = new HashMap<>();
    }
    
    // @param filename a file name
    // @return conetent of the file given from GFS
    public String read(String filename) {
        // Write your code here
        if (!chunkNum.containsKey(filename))
            return null;

        StringBuffer content = new StringBuffer();

        for (int i = 0; i < chunkNum.get(filename); ++i) {
            String sub_content = readChunk(filename, i);
            if (sub_content != null)
                content.append(sub_content);
        }
        return content.toString();
    }

    // @param filename a file name
    // @param content a string
    // @return void
    public void write(String filename, String content) {
        // Write your code here
        int length = content.length();

        int num = (length - 1) / chunkSize + 1;
        chunkNum.put(filename, num);

        for (int i = 0; i < num; ++i) {
            int start = i * chunkSize;
            int end = i == num -1 ? length : (i + 1) * chunkSize; 
            String sub_content = content.substring(start, end);
            writeChunk(filename, i, sub_content);
        }
    }
}
