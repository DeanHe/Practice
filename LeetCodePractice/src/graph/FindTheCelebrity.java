package graph;
/*
Suppose you are at a party with n people (labeled from 0 to n - 1) and among them, there may exist one celebrity.
The definition of a celebrity is that all the other n - 1 people know him/her but he/she does not know any of them.
Now you want to find out who the celebrity is or verify that there is not one.
The only thing you are allowed to do is to ask questions like: "Hi, A. Do you know B?" to get information of whether A knows B.
You need to find out the celebrity (or verify there is not one) by asking as few questions as possible (in the asymptotic sense).

You are given a helper function bool knows(a, b) which tells you whether A knows B.
Implement a function int findCelebrity(n), your function should minimize the number of calls to knows.

Example
Given n = 2

2 // next n * (n - 1) lines 
0 knows 1
1 does not know 0
return 1 // 1 is celebrity

Notice
There will be exactly one celebrity if he/she is in the party.
Return the celebrity's label if there is a celebrity in the party. If there is no celebrity, return -1.

analysis:
two pointer
if A knows B, then A can't be celebrity, discard A, and B may be celebrity.
if A doesn't know B, then B can't be celebrity, discard B, and A may be celebrity.

complexity O(N)
*/
public class FindTheCelebrity {
	/**
     * @param n a party with n people
     * @return the celebrity's label or -1
     */
    public int findCelebrity(int n) {
        int celebrity = 0, s = 0, e = n - 1;
        while(s < e){
            if(knows(s, e)){
                s++;
            } else {
                e--;
            }
        }
        celebrity = s;
        for(int i = 0; i < n; i++){
        	if(celebrity != i){
                if(!knows(i, celebrity) || knows(celebrity, i)){
                    return -1;
                }
        	}
        }
        return celebrity;
    }
    /* The knows API is defined in the parent class Relation.*/
    boolean knows(int a, int b){
    	return false;
    }
}
