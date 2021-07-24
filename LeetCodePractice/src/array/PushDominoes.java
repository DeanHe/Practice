package array;
/*
There are n dominoes in a line, and we place each domino vertically upright. In the beginning, we simultaneously push some of the dominoes either to the left or to the right.

After each second, each domino that is falling to the left pushes the adjacent domino on the left. Similarly, the dominoes falling to the right push their adjacent dominoes standing on the right.

When a vertical domino has dominoes falling on it from both sides, it stays still due to the balance of the forces.

For the purposes of this question, we will consider that a falling domino expends no additional force to a falling or already fallen domino.

You are given a string dominoes representing the initial state where:

dominoes[i] = 'L', if the ith domino has been pushed to the left,
dominoes[i] = 'R', if the ith domino has been pushed to the right, and
dominoes[i] = '.', if the ith domino has not been pushed.
Return a string representing the final state.



Example 1:

Input: dominoes = "RR.L"
Output: "RR.L"
Explanation: The first domino expends no additional force on the second domino.
Example 2:


Input: dominoes = ".L.R...LR..L.."
Output: "LL.RR.LLRRLL.."


Constraints:

n == dominoes.length
1 <= n <= 10^5
dominoes[i] is either 'L', 'R', or '.'.

//analysis: track last seen L and R position
 */
public class PushDominoes {
    public String pushDominoes(String dominoes) {
        char[] arr = dominoes.toCharArray();
        int L = -1, R = -1;
        for(int i = 0; i <= arr.length; i++){
            if(i == arr.length || arr[i] == 'R'){
                //R..R, turn all to R
                if(R > L){
                    int l = R + 1;
                    while(l < i){
                        arr[l++] = 'R';
                    }
                }
                R = i;
            } else if(arr[i] == 'L'){
                if(L > R || (L == -1 && R == -1)){
                    //L..L, turn all to L
                    int l = L + 1;
                    while(l < i){
                        arr[l++] = 'L';
                    }
                } else { //R...L
                    int l = R + 1, r = i - 1;
                    while(l < r){
                        arr[l++] = 'R';
                        arr[r--] = 'L';
                    }
                }
                L = i;
            }
        }
        return String.valueOf(arr);
    }
}

