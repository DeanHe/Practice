package designDataStructure;

import java.util.HashMap;
import java.util.Map;

/*
Given an integer stream of number between 1 to N, design a func and data structure
which can quickly tell the minimum missing number not received yet.

The func will be called multiple times

analysis:
union find
merge below then merge above
 */
public class FindMinimumMissingNumberGoogle {
    int least = 1;
    Map<Integer, Integer> parent = new HashMap<>();
    public int min() {
        return least;
    }

    public void receive(int num) {
        if(parent.containsKey(num)){
            return;
        }
        int below = num - 1;
        if(parent.containsKey(below)){
            parent.put(below, num);
        }
        int above = num + 1;
        if(parent.containsKey(above)){
            int root_above = findRoot(above);
            parent.put(num, root_above);
        } else {
            parent.put(num, num);
        }
        if(num == least){
            least = findRoot(num) + 1;
        }
    }

    private int findRoot(int x){
        int root = x;
        while(parent.get(root) != root){
            root = parent.get(root);
        }
        while(parent.get(x) != root){
            int fa = parent.get(x);
            parent.put(x, root);
            x = fa;
        }
        return root;
    }
}
