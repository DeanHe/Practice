
public class Solution {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

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
	

}
