package TwoPointers;
/*In a row of trees, the i-th tree produces fruit with type tree[i].
You start at any tree of your choice, then repeatedly perform the following steps:
Add one piece of fruit from this tree to your baskets.  If you cannot, stop.
Move to the next tree to the right of the current tree.  If there is no tree to the right, stop.
Note that you do not have any choice after the initial choice of starting tree: you must perform step 1, then step 2, then back to step 1, then step 2, and so on until you stop.
You have two baskets, and each basket can carry any quantity of fruit, but you want each basket to only carry one type of fruit each.
What is the total amount of fruit you can collect with this procedure?*/
public class FruitIntoBaskets {
	public int totalFruit(int[] tree) {
        int len = tree.length;
        int ans = 0, count = 0;
        for(int i = 0, first = 0, second = -1; i < len; i++){
            count++;
            if(tree[i] == tree[first]){
                first = i;
            } else if(second == -1 || tree[i] == tree[second]){
                second = i;
            } else {
                ans = Math.max(ans, count - 1);
                count = Math.abs(first - second) + 1;
                first = i - 1;
                second = i;
            }
        }
        ans = Math.max(ans, count);
        return ans;
    }
}
