package hashMap;

import java.util.HashMap;
import java.util.Map;

/*
You are given an array of desired filenames in the order of their creation. Since two files cannot have equal names, the one which comes later will have an addition to its name in a form of (k), where k is the smallest positive integer such that the obtained name is not used yet.

Return an array of names that will be given to the files.

Example

For names = ["doc", "doc", "image", "doc(1)", "doc"], the output should be
f‍‌‌‍‌‌‌‌‍‍‌‍‍‍‌‌‌‍ileNaming(names) = ["doc", "doc(1)", "image", "doc(1)(1)", "doc(2)"].
 */
public class UniqueFileNameGoogle {
    String[] generateUniqueFileName(String[] files){
        Map<String, Integer> map = new HashMap<>();
        int len = files.length;
        String[] res = new String[len];
        for(int i = 0; i < len; i++){
            String file =  files[i];
            if(!map.containsKey(file)){
                map.put(file, 0);
                res[i] = file;
            } else {
                int version = map.get(file);
                version++;
                String temp = file  + "(" + version + ")";
                while(map.containsKey(temp)){
                    version++;
                    temp = file  + "(" + version + ")";
                }
                map.put(temp, 0);
                res[i] = temp;
            }
        }
        return res;
    }
}

