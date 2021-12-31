package bfs;

import java.util.*;

/*
You have information about n different recipes. You are given a string array recipes and a 2D string array ingredients. The ith recipe has the name recipes[i],
and you can create it if you have all the needed ingredients from ingredients[i]. Ingredients to a recipe may need to be created from other recipes, i.e., ingredients[i] may contain a string that is in recipes.
You are also given a string array supplies containing all the ingredients that you initially have, and you have an infinite supply of all of them.
Return a list of all the recipes that you can create. You may return the answer in any order.
Note that two recipes may contain each other in their ingredients.


Example 1:
Input: recipes = ["bread"], ingredients = [["yeast","flour"]], supplies = ["yeast","flour","corn"]
Output: ["bread"]
Explanation:
We can create "bread" since we have the ingredients "yeast" and "flour".

Example 2:
Input: recipes = ["bread","sandwich"], ingredients = [["yeast","flour"],["bread","meat"]], supplies = ["yeast","flour","meat"]
Output: ["bread","sandwich"]
Explanation:
We can create "bread" since we have the ingredients "yeast" and "flour".
We can create "sandwich" since we have the ingredient "meat" and can create the ingredient "bread".

Example 3:
Input: recipes = ["bread","sandwich","burger"], ingredients = [["yeast","flour"],["bread","meat"],["sandwich","meat","bread"]], supplies = ["yeast","flour","meat"]
Output: ["bread","sandwich","burger"]
Explanation:
We can create "bread" since we have the ingredients "yeast" and "flour".
We can create "sandwich" since we have the ingredient "meat" and can create the ingredient "bread".
We can create "burger" since we have the ingredient "meat" and can create the ingredients "bread" and "sandwich".


Constraints:
n == recipes.length == ingredients.length
1 <= n <= 100
1 <= ingredients[i].length, supplies.length <= 100
1 <= recipes[i].length, ingredients[i][j].length, supplies[k].length <= 10
recipes[i], ingredients[i][j], and supplies[k] consist only of lowercase English letters.
All the values of recipes and supplies combined are unique.
Each ingredients[i] does not contain any duplicate values.

hint:
1 Can we use a data structure to quickly query whether we have a certain ingredient?
2 Once we verify that we can make a recipe, we can add it to our ingredient data structure. We can then check if we can make more recipes as a result of this.

analysis:
Topological sort
 */
public class FindAllPossibleRecipesFromGivenSupplies {
    public List<String> findAllRecipes(String[] recipes, List<List<String>> ingredients, String[] supplies) {
        List<String> res = new ArrayList<>();
        Set<String> recipeSet = new HashSet<>(Arrays.asList(recipes));
        Map<String, List<String>> g = new HashMap<>();
        Map<String, Integer> indeg = new HashMap<>();
        for(int i = 0; i < recipes.length; i++){
            String to = recipes[i];
            List<String> froms = ingredients.get(i);
            indeg.put(to, froms.size());
            for(String from : froms){
                g.computeIfAbsent(from, x -> new ArrayList<>()).add(to);
            }
        }
        Queue<String> q = new LinkedList<>();
        for(String supply : supplies){
            if(!indeg.containsKey(supply)){
                q.offer(supply);
            }
        }
        while(!q.isEmpty()){
            String cur = q. poll();
            if(recipeSet.contains(cur)){
                res.add(cur);
            }
            for(String nb : g.getOrDefault(cur, new ArrayList<>())){
                if(indeg.containsKey(nb)){
                    int cnt = indeg.get(nb);
                    indeg.put(nb, --cnt);
                    if(cnt == 0){
                        q.offer(nb);
                    }
                }
            }
        }
        return res;
    }
}
