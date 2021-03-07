package BST;

/*
Given a stream of integers, lookup the rank of a given integer x. Rank of an integer in-stream is “Total number of elements less than or equal to x (not including x)”.
If an element is not found in the stream or is smallest in stream, return -1.


Example 1:
Input :  arr[] = {10, 20, 15, 3, 4, 4, 1}
              x = 4;

Output : Rank of 4 in stream is: 3
There are total three elements less than
or equal to x (and not including x)


Example 2:
Input : arr[] = {5, 1, 14, 4, 15, 9, 7, 20, 11},
           x = 20;

Output : Rank of 20 in stream is: 8
 */
public class RankOfAnElementInaStream {
    RankNode root = null;

    public void test(){
        track(10);
        track(20);
        track(15);
        track(3);
        track(4);
        track(4);
        track(1);
        System.out.println(getRankOfNumber(10));
    }

    public void track(int num) {
        if (root == null) {
            root = new RankNode(num);
        } else {
            root.insert(num);
        }
    }

    public int getRankOfNumber(int num) {
        return root.getRank(num);
    }

    private class RankNode {
        public int rank = 0, val = 0, cnt = 0;
        public RankNode left, right;

        public RankNode(int value) {
            val = value;
            cnt = 1;
        }

        public void insert(int num) {
            if (num == val) {
                cnt++;
            } else if (num < val) {
                if (left != null) {
                    left.insert(num);
                } else {
                    left = new RankNode(num);
                }
                rank++;
            } else if (num > val) {
                if (right != null) {
                    right.insert(num);
                } else {
                    right = new RankNode(num);
                }
            }
        }

        public int getRank(int num) {
            if (num == val) {
                return rank;
            } else if (num < val) {
                if (left == null) {
                    return -1;
                } else {
                    return left.getRank(num);
                }
            } else {
                int res = rank + cnt;
                if (right == null) {
                    return -1;
                } else {
                    int rightRank = right.getRank(num);
                    if (rightRank == -1) {
                        return -1;
                    } else {
                        return res + rightRank;
                    }
                }
            }
        }
    }
}
