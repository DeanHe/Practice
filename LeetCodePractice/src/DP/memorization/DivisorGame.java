package DP.memorization;
/*
Alice and Bob take turns playing a game, with Alice starting first.

Initially, there is a number N on the chalkboard.  On each player's turn, that player makes a move consisting of:

Choosing any x with 0 < x < N and N % x == 0.
Replacing the number N on the chalkboard with N - x.
Also, if a player cannot make a move, they lose the game.

Return True if and only if Alice wins the game, assuming both players play optimally.
*/
public class DivisorGame {
	Boolean[] mem;
    public boolean divisorGame(int N) {
    	mem = new Boolean[N + 1];
        return canWin(N);
    }
    private boolean canWin(int N){
    	if(mem[N] != null){
    		return mem[N];
    	}
    	for(int i = 1; i < N; i++){
    		if(N % i == 0){
    			if(!canWin(N - i)){
    				return mem[i] = true;
    			}
    		}
    	}
    	return mem[N] = false;
    }
}
