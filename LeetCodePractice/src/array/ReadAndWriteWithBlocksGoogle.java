package array;

import java.nio.BufferOverflowException;

/*
Google Interview implements the read and write data API
using an existing API which can read/write a fixed size Block
 */
public class ReadAndWriteWithBlocksGoogle {

    static final int BLOCK_SIZE = 512;

    byte[] read(int offset, int length){
        int start = offset;
        int end = length - 1 + start;
        int startBlockIdx = (start + BLOCK_SIZE - 1) / BLOCK_SIZE;
        int endBlockIdx = (end + BLOCK_SIZE - 1) / BLOCK_SIZE;
        int totalBlocks = endBlockIdx - startBlockIdx + 1;
        int startShift = start % BLOCK_SIZE;
        byte[] blocks = readBlocks(startBlockIdx, totalBlocks);
        byte[] res = new byte[length];
        for(int i = 0; i < length; i++){
            res[i] = blocks[i + startShift];
        }
        return res;
    }

    void write(int offset, int length, byte[] data){
        if(length > data.length){
            throw new BufferOverflowException();
        }
        int start = offset;
        int end = length - 1 + start;
        int startBlockIdx = (start + BLOCK_SIZE - 1) / BLOCK_SIZE;
        int endBlockIdx = (end + BLOCK_SIZE - 1) / BLOCK_SIZE;
        int totalBlocks = endBlockIdx - startBlockIdx + 1;
        int startShift = start % BLOCK_SIZE;
        // incomplete
    }

    /*
    provided API
     */
    byte[] readBlocks(int startBlockIdx, int totalBlocks){
        return null;
    }

    /*
    provided API
     */
    void writeBlock(int startBlockIdx, int totalBlocks, byte[] data){

    }
}

