package tiktok;
/*
Real Programmer Game (RPG) is about having a hero swinging sticks at a monster.
The monster has N Health Points (НР). It
is killed when HP drops to 0 or negative.
Each swing of the hero reduces monster's HP by a (evenly distributed) random number in
[0, M]

What's the probability for the hero to kill the monster in K swings?
Write a function, input N, м, K, return such prob
ability (in [0, 1]).
Constraints
0<=N<= 1000
0<M <= 1000
0<=K<= 1000

Hints
There are 10 test cases for this problem. A non
perfect solution could still earn partial
credit by
getting some of them correct.
Feel free to try out closed form math formula,
simulation, brute forced enumeration, etc.
The following information could be helpful too.
1. Scores are earned iff abs(your. _output
real_ answer) < 5e-6.
2. Some test cases have M == 1.
3. Some test cases have M <= 7 && K<= 8.

Example
N = 2,M = 1
,K = 3
Each swing deals damage 0 or 1. It's random, hence each with 50% chance.
All damage sequence with probability:
12.5% 0, 0, 0, fail
12.5% 0, 0, 1, fail
12.5% 0, 1, 0, fail
12.5% 0, 1, 1, win
12.5% 1, 0, 0, fail
12.5% 1, 0, 1, win
25.0% 1, 1, win
win = 50% aka 0.5.
3.
 */
public class RealProgrammerGame {
    double calculateProbability(int n, int m, int k){
        double[][] dp = new double[n + 1][k + 1];

        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= k; j++){

            }
        }
        return 1.0;
    }
}
