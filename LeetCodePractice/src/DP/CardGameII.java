package DP;
// https://www.lintcode.com/problem/card-game-ii/description
/*You are playing a card game with your friends, there are n cards in total. Each card costs cost[i] and inflicts damage[i] damage to the opponent. You have a total of totalMoney dollars and need to inflict at least totalDamage damage to win. And Each card can only be used once. Determine if you can win the game.*/
public class CardGameII {
	/**
     * @param cost: costs of all cards
     * @param damage: damage of all cards
     * @param totalMoney: total of money
     * @param totalDamage: the damage you need to inflict
     * @return: Determine if you can win the game
     */
    public boolean cardGame(int[] cost, int[] damage, int totalMoney, int totalDamage) {
        // Write your code here
        int[] dp = new int[totalMoney + 1];
        for(int i = 0; i < cost.length; i++){
            for(int j = totalMoney; j >= cost[i]; j--){
                dp[j] = Math.max(dp[j], dp[j - cost[i]] + damage[i]);
                if(dp[j] >= totalDamage){
                    return true;
                }
            }
        }
        return false;
    }
}
