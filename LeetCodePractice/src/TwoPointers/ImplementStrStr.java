package TwoPointers;

public class ImplementStrStr {
	public int strStr(String source, String target) {
        // Write your code here
        if(source == null || target == null){
            return -1;
        }
        if(target.length() == 0){
            return 0;
        }
        if(target.length() == 0){
            return 0;
        }
        int i = 0;
        int j = 0;
        while(i <= source.length() - target.length()){
            while(j < target.length() && source.charAt(i + j) == target.charAt(j)){
                j++;
            }
            if(j == target.length()){
                return i;
            }
            i++;
            j = 0;
        }
        return -1;
    }
}
