package String;

public class RomantoInteger {
    public int romanToInt(String s) {
        int res =0;
        char[] temp = s.toCharArray();
        for(int i=0; i<temp.length; i++){
            switch(temp[i]){
                case 'I': 
                    if(i<temp.length-1 && temp[i+1]=='V'){
                        i++;
                        res +=4;
                    }
                    else if (i<temp.length-1 && temp[i+1]=='X'){
                        i++;
                        res +=9;
                    }
                    else{
                        res +=1;
                    }
                    break;
               case 'V':
                   res +=5;
                   break;
               case 'X':
                   if(i<temp.length-1 && temp[i+1]=='L'){
                        i++;
                        res +=40;
                    }
                    else if (i<temp.length-1 && temp[i+1]=='C'){
                        i++;
                        res +=90;
                    }
                    else{
                        res +=10;
                    }
                    break;
              case 'L':
                  res +=50;
                   break;
              case 'C':
                   if(i<temp.length-1 && temp[i+1]=='D'){
                        i++;
                        res +=400;
                    }
                    else if (i<temp.length-1 && temp[i+1]=='M'){
                        i++;
                        res +=900;
                    }
                    else{
                        res +=100;
                    }
                    break;
              case 'D':
                  res +=500;
                   break;
              case 'M':
                  res +=1000;
                   break;
             default:
                   break;
            }
        }
        return res;
    }
}
