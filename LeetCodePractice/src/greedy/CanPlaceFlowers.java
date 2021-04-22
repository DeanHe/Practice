package greedy;

public class CanPlaceFlowers {
	public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int len = flowerbed.length, cnt = 0;
        for(int i = 0; i < len; i++){
            int pre = i - 1;
            int next = i + 1;
            if((pre < 0 || flowerbed[pre] == 0) && (next >= len || flowerbed[next] == 0)){
                if(flowerbed[i] == 0){
                    flowerbed[i] = 1;
                    cnt++;
                }
            }
            if(cnt >= n){
                return true;
            }
        }
        return false;
    }
}
