package Math;

import java.util.LinkedList;

/*
Given a circular single linked list.Write a program that deletes every kth node until only one node is left.
After kth node is deleted, start the procedure from (k+1)th node.
 */
public class JosephusProblem {
    public int josephus(int n, int k){
        LinkedList<Integer> ls = new LinkedList<>();
        for(int i = 1; i <= n; i++){
            ls.add(i);
        }
        int i = 0;
        while(ls.size() != 1){
            i = (i + k - 1) % ls.size();
            int e = ls.remove(i);
            System.out.println(e);
        }
        return ls.get(0);
    }
}
