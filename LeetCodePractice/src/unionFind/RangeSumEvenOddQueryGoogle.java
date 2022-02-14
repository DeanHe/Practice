package unionFind;

import java.util.HashMap;
import java.util.Map;

/*
#Google

Assume an array A, at first, you have no information about A. there will be some queris <start, end, odd/even> in sequence.
each query means: A[start:end+1] is odd/even. Detect the first conflict.

Example:
<2, 14, odd>, <8, 14, even>, <2, 7, even>. when first and second comes in, we know 2->7 has to be odd-even=odd.
but when the third comes in, it is even (conflict with existing information about A)

analysis:
maintain another map track the odd property between element and root, update this map via findRoot method
 */
public class RangeSumEvenOddQueryGoogle {
    Map<Integer, Integer> parent = new HashMap<>();
    Map<Integer, Boolean> oddWithParent = new HashMap<>();

    public boolean verifyQueries(String[] queries) {
        int start = Integer.valueOf(queries[0]);
        parent.putIfAbsent(start, start);
        oddWithParent.putIfAbsent(start, false);
        int end = Integer.valueOf(queries[1]) + 1;
        parent.putIfAbsent(end, end);
        oddWithParent.putIfAbsent(end, false);
        boolean isOdd = queries[2].equals("odd") ? true : false;
        int root_start = findRoot(start);
        int root_end = findRoot(end);
        if (isOdd) {
            // odd case query
            if (root_start == root_end) {
                if((oddWithParent.get(start) ^ oddWithParent.get(end)) == false){
                    return false;
                }
            } else {
                parent.put(root_start, root_end);
                oddWithParent.put(root_start, true);
            }
        } else {
            // even case query
            if (root_start == root_end) {
                if((oddWithParent.get(start) ^ oddWithParent.get(end)) == true){
                    return false;
                }
            } else {
                parent.put(root_start, root_end);
                oddWithParent.put(root_start, false);
            }
        }
        return true;
    }

    private int findRoot(int x) {
        int root = x;
        boolean curOddWithParent = oddWithParent.get(root);
        while (!parent.get(root).equals(root)) {
            int fa = parent.get(root);
            curOddWithParent ^= oddWithParent.get(fa);
            root = fa;
        }
        oddWithParent.put(x, curOddWithParent);
        return root;
    }
}
