package String;

import java.util.ArrayList;
import java.util.List;

/*
input: 2
output：
_*
***
_*

input: 3
output:
__*
_***
*****
_***
__*
 */
public class PrintStarsGoogle {
    List<String> printStar(int n){
        List<String> res = new ArrayList<>();
        int s = 0, e = 2 * (n - 1);
        while(s <= e){
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i <= e; i++){
                if(i >= s){
                    sb.append('*');
                } else {
                    sb.append(' ');
                }
            }
            e--;
            s++;
            if(res.isEmpty()){
                res.add(sb.toString());
            } else {
                res.add(0, sb.toString());
                res.add(sb.toString());
            }
        }
        return res;
    }
}
