package String;

public class RepeatedSubstringPattern {
	public boolean repeatedSubstringPattern(String s) {
        for(int len = s.length() / 2; len >= 1; len--){
        	if(s.length() % len == 0){
        		int repeat = s.length() / len;
        		String part = s.substring(0, len);
        		StringBuilder sb = new StringBuilder();
        		for(int i = 0; i < repeat; i++){
        			sb.append(part);
        		}
        		if(sb.toString().equals(s)){
        			return true;
        		}
        	}
        }
        return false;
    }
}
