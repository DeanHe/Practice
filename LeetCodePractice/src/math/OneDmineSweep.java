package math;

/*
Given a one dimension array H where H = [2,1,2,1,0], H[i] is the amount of mine in position i, len(H) is odd
S = [3, 5, 4, 3, 1], S[i] is the total amount of mines in position i and its nearby positions. the amount is non negative
which means S[i] = H[i - 1] + H[i] + H[i + 1]
return the maximum possible value of H[mid]
time complexity must be O(N)

test:
int[] S = {2, 6, 5, 6, 6, 6, 6, 6, 6, 6, 1};
int[] S = {3, 5, 4, 3, 1};
 */
public class OneDmineSweep {
    public int getMaxValueOfMidH(int[] data) {
        int res = Integer.MAX_VALUE;
        int len = data.length;
        int mid = len / 2;
        int l = mid, r = mid;
        int delta = 0;
        while (l >= 0) {
            int temp_min = Math.min(data[l], data[l + 1]);
            temp_min = Math.min(temp_min, data[l - 1]);
            res = Math.min(res, temp_min + delta);
            delta = data[l - 1] - data[l - 2];
            l -= 3;
        }
        res = Math.min(res, delta);
        delta = 0;
        while (r < len) {
            int temp_min = Math.min(data[r], data[r + 1]);
            temp_min = Math.min(temp_min, data[r - 1]);
            res = Math.min(res, temp_min + delta);
            delta = data[r + 1] - data[r + 2];
            r += 3;
        }
        res = Math.min(res, delta);
        return res;
    }
}
