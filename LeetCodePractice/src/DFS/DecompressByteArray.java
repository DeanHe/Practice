package DFS;

/*
given a list of byte
 */
import java.util.ArrayList;
import java.util.List;

public class DecompressByteArray {
    public List<Byte> decompress(List<Byte> input){
       List<Byte> ls = new ArrayList<>();
       byte special = (byte)0xff;
       int idx = -1, offset = 0, copy = 0;
       for(int i = 0; i < input.size(); i++){
            if(input.get(i) == special){
                idx = i;
                offset = input.get(idx + 1);
                copy = input.get(idx + 2);
                break;
            } else {
                ls.add(input.get(i));
            }
       }
       if(idx != -1){
           for(int i = idx - offset, cnt = 0; cnt < copy; i++, cnt++){
               ls.add(input.get(i));
           }
           for(int i = idx + 3; i < input.size(); i++){
               ls.add(input.get(i));
           }
       }
       if(idx == -1){
            return ls;
       }
       return decompress(ls);
    }

    public void test(){
        String str = "notethatthesignispreserved,whichisntalwayswhatyouwant";
        System.out.println(str);
        List<Byte> input = new ArrayList<>();
        for(byte b : str.getBytes()){
            input.add(b);
        }
        input.add(15, (byte)0xff);
        input.add(16, (byte)10);
        input.add(17, (byte)5);

        input.add(5, (byte)0xff);
        input.add(6, (byte)4);
        input.add(7, (byte)3);
        List<Byte> output = decompress(input);
        byte[] out_byte = new byte[output.size()];
        for(int i = 0; i < output.size(); i++){
            out_byte[i] = output.get(i);
        }
        System.out.println(new String(out_byte));
    }
}
