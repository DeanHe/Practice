package hashMap;

import java.util.HashMap;
import java.util.Map;
/*
Suppose we have very large sparse vectors (most of the elements in vector are zeros)

Find a data structure to store them
Compute the Dot Product.


Follow-up:
What if one of the vectors is very small?

analysis:
loop through the smaller hashmap
 */
public class DotProductOfSparseVectors {
    int doProduct(int[] vector1, int[] vector2) {
        Map<Integer, Integer> m1 = new HashMap<>();
        Map<Integer, Integer> m2 = new HashMap<>();
        if (vector1.length < vector2.length) {
            int[] temp = vector1;
            vector1 = vector2;
            vector2 = temp;
        }
        for (int i = 0; i < vector1.length; i++) {
            m1.put(i, vector1[i]);
        }
        for (int i = 0; i < vector2.length; i++) {
            m2.put(i, vector2[i]);
        }
        int res = 0;
        for (Map.Entry<Integer, Integer> entry : m2.entrySet()) {
            res += entry.getValue() * m1.getOrDefault(entry.getKey(), 0);
        }
        return res;
    }
}
