package Math;

public class ReverseInteger {
	public int reverse(int x) {
        long ret = 0;
        boolean negative = false;
        if(x < 0 ){
            negative = true;
            x = -x;
        }
        while(x > 0){
           ret = ret*10 + x%10;
           if(ret> Integer.MAX_VALUE){
               return 0;
           }
             x = x/10;
        }        
        if(negative){
            ret = -ret;
        }        
        return (int)ret;
    }
}
