package bst;

/*
One way to serialize a binary tree is to use pre-order traversal. When we encounter a non-null node, we record the node's value. If it is a null node, we record using a sentinel value such as #.

_9_
/   \
3     2
/ \   / \
4   1  #  6
/ \ / \   / \
# # # #   # #
For example, the above binary tree can be serialized to the string "9,3,4,#,#,1,#,#,2,#,6,#,#", where # represents a null node.

Given a string of comma separated values, verify whether it is a correct preorder traversal serialization of a binary tree.
Note: You are not allowed to reconstruct the tree.

Each comma separated value in the string must be either an integer or a character '#' representing null pointer.

You may assume that the input format is always valid, for example it could never contain two consecutive commas such as "1,,3"

Constraints:

1 <= preorder.length <= 104
preorder consist of integers in the range [0, 100] and '#' separated by commas ','.

analysis:
Some used stack. Some used the depth of a stack. Here I use a different perspective. In a binary tree, if we consider null as leaves, then

all non-null node provides 2 outdegree and 1 indegree (2 children and 1 parent), except root
all null node provides 0 outdegree and 1 indegree (0 child and 1 parent).

Suppose we try to build this tree. During building, we record the difference between out degree and in degree diff = outdegree - indegree.
 When the next node comes, we then decrease diff by 1, because the node provides an in degree. If the node is not null, we increase diff by 2,
 because it provides two out degrees. If a serialization is correct, diff should never be negative and diff will be zero when finished.
*/
public class VerifyPreorderSerializationOfaBinaryTree {
	public boolean isValidSerialization(String preorder) {
		String[] nodes = preorder.split(",");
		int indegree = 0, outdegree = 0;
		int len = nodes.length;
		if (len == 1 && nodes[0].equals("#")) {
			return true;
		}
		for (int i = 0; i < len; i++) {
			if (i == 0 && !nodes[0].equals("#")) {
				outdegree += 2;
			} else {
				if (nodes[i].equals("#")) {
					indegree++;
				} else {
					indegree++;
					outdegree += 2;
				}
				if (outdegree < indegree || (outdegree == indegree && i < len - 1)) {
					return false;
				}
			}
		}
		return outdegree == indegree;
	}
}
