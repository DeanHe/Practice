package hashMap;
/*
        Given the string croakOfFrogs,which represents a combination of the string"croak"from different frogs,that is,multiple frogs can croak at the same time,so multiple “croak” are mixed.
        Return the minimum number of different frogs to finish all the croak in the given string.
        A valid"croak"means a frog is printing 5letters ‘c’, ’r’, ’o’, ’a’, ’k’ sequentially.
        The frogs have to print all five letters to finish a croak.If the given string is not a combination of valid"croak"return-1.

        Example 1:
        Input:croakOfFrogs="croakcroak"
        Output:1
        Explanation:One frog yelling"croak"twice.

        Example 2:
        Input:croakOfFrogs="crcoakroak"
        Output:2
        Explanation:The minimum number of frogs is two.
        The first frog could yell"crcoakroak".
        The second frog could yell later"crcoakroak".

        Example 3:
        Input:croakOfFrogs="croakcrook"
        Output:-1
        Explanation:The given string is an invalid combination of"croak"from different frogs.

        Example 4:
        Input:croakOfFrogs="croakcroa"
        Output:-1


        Constraints:
        1<=croakOfFrogs.length<=10^5
        All characters in the string are:'c','r','o','a'or'k'.
*/
public class MinimumNumberOfFrogsCroaking {
    public int minNumberOfFrogs(String croakOfFrogs) {
        int len = croakOfFrogs.length(), res = 0, frog = 0;
        int c = 0, r = 0, o = 0, a = 0, k = 0;
        for(int i = 0; i < len; i++){
            char letter = croakOfFrogs.charAt(i);
            switch (letter) {
                case 'c':
                    c++;
                    frog++;
                    break;
                case 'r':
                    r++;
                    break;
                case 'o':
                    o++;
                    break;
                case 'a':
                    a++;
                    break;
                case 'k':
                    k++;
                    frog--;
                    break;
            }
            res = Math.max(res, frog);
            if((c < r) || (r < o) ||(o < a) ||(a < k)){
                return -1;
            }
        }
        if(frog == 0 && c == r && r == o && o == a && a == k){
            return res;
        }
        return -1;
    }
}
