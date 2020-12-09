package tiktok;
/*
Jina recently got a computer with two memory sticks. The computer will allocate requested
memory from the largest available memory stick if possible (or from the first memory stick
if both have the same avail able memory), if neither of these two memory sticks has enough
available memory, it will cause the computer Out of Memory (a.k.a ооM).

Jina is so exciting and writes down a hello world program, but soon Jina observes a
memory leak on her program, her prog ram will allocate i bytes at the i th second starting
from when the program started. Jina is very curious when the computer will be OOM after
starting her program and the available memory of each memory stick when Oом. Can you
help her figure it out?

Input:
The first line of the input gives the number of test cases N. And N cases follow. Each test
case consists of a single line containing two integers M1 and M2 indicating the total
memory in bytes of the first and second memory stick in Jina's computer bef ore starting
Jina's program.

Output:
For each test case, output one line containing three integers t, m1, m2, where t is the time
in seconds that computer will be OOM after starting the program, and m1, m2 are the
available memory in bytes of the first an d second memory sticks, respectively, when Jina's
computer OOM.

Limits:
1<=N<=1000
1 <= M1 <= 10 ∧ 18
1 <= M2 <= 10 ∧ 18

Example:
Input:
2
2 2
8 11

Output:
3 1 0
6 0 4

Explanation:
For test case 1:
0s:2 2
1s:1 2
2s:1 0

For test
case 2:
0s:8 11
1s:8 10
2s:8 8
3s:5 8
4s:5 4
5s:0 4
6s:OOM
 */
public class IncrementalMemoryLeak {
    public int[] outOfMemory(int m1, int m2){
        return dfs(0, m1, m2);
    }

    private int[] dfs(int n, int m1, int m2){
        if(m1 < m2){
            if(n <= m2){
                return dfs(n + 1, m1, m2 - n);
            } else {
                return new int[]{n, m1, m2};
            }
        } else {
            if (n <= m1) {
                return dfs(n + 1, m1 - n, m2);
            } else {
                return new int[]{n, m1, m2};
            }
        }
    }
}
