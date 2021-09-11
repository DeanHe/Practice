package sort;

import java.util.Arrays;

/*
You are playing a game that contains multiple characters, and each of the characters has two main properties: attack and defense. You are given a 2D integer array properties where properties[i] = [attacki, defensei] represents the properties of the ith character in the game.

A character is said to be weak if any other character has both attack and defense levels strictly greater than this character's attack and defense levels. More formally, a character i is said to be weak if there exists another character j where attackj > attacki and defensej > defensei.

Return the number of weak characters.



Example 1:

Input: properties = [[5,5],[6,3],[3,6]]
Output: 0
Explanation: No character has strictly greater attack and defense than the other.
Example 2:

Input: properties = [[2,2],[3,3]]
Output: 1
Explanation: The first character is weak because the second character has a strictly greater attack and defense.
Example 3:

Input: properties = [[1,5],[10,4],[4,3]]
Output: 1
Explanation: The third character is weak because the second character has a strictly greater attack and defense.


Constraints:

2 <= properties.length <= 10^5
properties[i].length == 2
1 <= attacki, defensei <= 10^5

hint:
Sort the array on the basis of the attack values and group characters with the same attack together. How can you use these groups?
Characters in one group will always have a lesser attack value than the characters of the next group.
Hence, we will only need to check if there is a higher defense value present in the next groups.

analysis:
sort array by attack descending order, and defense by ascending order
thus can make sure when a and b's attack is different,
a's defense is the largest defense of same attack elements
b's defense is the smallest defense of same attack elements
which guarantees to find b by the week condition
 */
public class TheNumberOfWeakCharactersInTheGame {
    public int numberOfWeakCharacters(int[][] properties) {
        Arrays.sort(properties, (a, b) -> {
            if(a[0] != b[0]){
                return b[0] - a[0];
            }
            return a[1] - b[1];
        });
        int res = 0, maxDefense = 0;
        for(int[] property : properties){
            if(property[1] < maxDefense){
                res++;
            }
            maxDefense = Math.max(maxDefense, property[1]);
        }
        return res;
    }
}

