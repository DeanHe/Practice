import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Solution {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<String> reStrings = subStringsKDist("a", 3);
		System.out.println(reStrings);
	}
	
	public boolean canPlaceFlowers(int[] flowerBed, int n){
		int count = 0;
		for(int i = 0; i < flowerBed.length; i++){
			if(flowerBed[i] == 0 
					&& (i == 0 || flowerBed[i - 1] == 0) 
					&& (i == flowerBed.length - 1 || flowerBed[i + 1] == 0)){
				flowerBed[i] = 1;
				count++;
				if(count >= n){
					return true;
				}
			}
		}
		return false;
	}
	
	public static List<String> subStringsKDist(String inputStr, int num)
	{
        // WRITE YOUR CODE HERE
        List<String> res = new ArrayList<>();
        int len = inputStr.length();
        int[] count = new int[26];
        int distinctCount;
        for(int i = 0; i < len; i++){
            Arrays.fill(count, 0);
            distinctCount = 0;
            for(int j = i; j < len; j++){
                if(count[inputStr.charAt(j) - 'a'] == 0){
                    distinctCount++;
                }
                count[inputStr.charAt(j) - 'a']++;
                if(distinctCount == num){
                    String temp = inputStr.substring(i, j + 1);
                    res.add(temp);
                }
            }
        }
        return res;
    }
	

}
