"""
You have information about n different recipes. You are given a string array recipes and a 2D string array ingredients. The ith recipe has the name recipes[i], and you can create it if you have all the needed ingredients from ingredients[i]. A recipe can also be an ingredient for other recipes, i.e., ingredients[i] may contain a string that is in recipes.

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

hints:
1 Can we use a data structure to quickly query whether we have a certain ingredient?
2 Once we verify that we can make a recipe, we can add it to our ingredient data structure. We can then check if we can make more recipes as a result of this.

analysis:
TC: O(N + M + S)
Let n be the number of recipes, m be the total number of ingredients across all recipes, and s be the number of supplies.
"""
from collections import defaultdict, deque
from typing import List


class FindAllPossibleRecipesFromGivenSupplies:
    def findAllRecipes(self, recipes: List[str], ingredients: List[List[str]], supplies: List[str]) -> List[str]:
        res = []
        g = defaultdict(list)
        in_deg = {}
        recipes_set = set(recipes)
        for i in range(len(recipes)):
            in_deg[recipes[i]] = len(ingredients[i])
            for ingredient in ingredients[i]:
                g[ingredient].append(recipes[i])
        starts = []
        for supply in supplies:
            if supply not in in_deg:
                starts.append(supply)
        q = deque(starts)
        while q:
            sz = len(q)
            for _ in range(sz):
                cur = q.popleft()
                if cur in recipes_set:
                    res.append(cur)
                for nb in g[cur]:
                    in_deg[nb] -= 1
                    if in_deg[nb] == 0:
                        q.append(nb)
        return res




