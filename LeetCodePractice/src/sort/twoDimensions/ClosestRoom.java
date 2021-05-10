package sort.twoDimensions;

import java.util.Arrays;
import java.util.TreeSet;
/*
There is a hotel with n rooms. The rooms are represented by a 2D integer array rooms where rooms[i] = [roomIdi, sizei] denotes that there is a room with room number roomIdi and size equal to sizei. Each roomIdi is guaranteed to be unique.

You are also given k queries in a 2D array queries where queries[j] = [preferredj, minSizej]. The answer to the jth query is the room number id of a room such that:

The room has a size of at least minSizej, and
abs(id - preferredj) is minimized, where abs(x) is the absolute value of x.
If there is a tie in the absolute difference, then use the room with the smallest such id. If there is no such room, the answer is -1.

Return an array answer of length k where answer[j] contains the answer to the jth query.



Example 1:

Input: rooms = [[2,2],[1,2],[3,2]], queries = [[3,1],[3,3],[5,2]]
Output: [3,-1,3]
Explanation: The answers to the queries are as follows:
Query = [3,1]: Room number 3 is the closest as abs(3 - 3) = 0, and its size of 2 is at least 1. The answer is 3.
Query = [3,3]: There are no rooms with a size of at least 3, so the answer is -1.
Query = [5,2]: Room number 3 is the closest as abs(3 - 5) = 2, and its size of 2 is at least 2. The answer is 3.
Example 2:

Input: rooms = [[1,4],[2,3],[3,5],[4,1],[5,2]], queries = [[2,3],[2,4],[2,5]]
Output: [2,1,3]
Explanation: The answers to the queries are as follows:
Query = [2,3]: Room number 2 is the closest as abs(2 - 2) = 0, and its size of 3 is at least 3. The answer is 2.
Query = [2,4]: Room numbers 1 and 3 both have sizes of at least 4. The answer is 1 since it is smaller.
Query = [2,5]: Room number 3 is the only room with a size of at least 5. The answer is 3.


Constraints:

n == rooms.length
1 <= n <= 105
k == queries.length
1 <= k <= 104
1 <= roomIdi, preferredj <= 10^7
1 <= sizei, minSizej <= 10^7

analysis:
sort rooms and queries by min_size in descending order
 */
public class ClosestRoom {
    public int[] closestRoom(int[][] rooms, int[][] queries) {
        int qLen = queries.length, rLen = rooms.length;
        int[] res = new int[qLen];
        Integer[] sortedQueryIdx = new Integer[qLen];
        for(int i = 0; i < qLen; i++){
            sortedQueryIdx[i] = i;
        }
        Arrays.sort(sortedQueryIdx, (a, b) -> queries[b][1] - queries[a][1]);
        Arrays.sort(rooms, (a, b) -> b[1] - a[1]);
        TreeSet<Integer> roomIdsVisited = new TreeSet<>();
        int i = 0;
        for (int j : sortedQueryIdx) {
            int[] query = queries[j];
            while (i < rLen && rooms[i][1] >= query[1]) {
                roomIdsVisited.add(rooms[i][0]);
                i++;
            }
            res[j] = searchClosetRoomId(roomIdsVisited, query[0]);
        }
        return res;
    }

    private int searchClosetRoomId(TreeSet<Integer> roomIds, int preferred) {
        int diff = Integer.MAX_VALUE, res = -1;
        Integer fl = roomIds.floor(preferred);
        Integer ce = roomIds.ceiling(preferred);
        if (fl != null) {
            if (preferred - fl < diff) {
                diff = preferred - fl;
                res = fl;
            }
        }
        if (ce != null) {
            if (ce - preferred < diff) {
                diff = ce - preferred;
                res = ce;
            }
        }
        return res;
    }
}

