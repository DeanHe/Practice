package UnionFind;
/*Given a list accounts, each element accounts[i] is a list of strings, where the first element accounts[i][0] is a name, and the rest of the elements are emails representing emails of the account.
Now, we would like to merge these accounts. Two accounts definitely belong to the same person if there is some email that is common to both accounts. Note that even if two accounts have the same name, they may belong to different people as people could have the same name. A person can have any number of accounts initially, but all of their accounts definitely have the same name.
After merging the accounts, return the accounts in the following format: the first element of each account is the name, and the rest of the elements are emails in sorted order. The accounts themselves can be returned in any order.

Example 1:
Input: 
accounts = [["John", "johnsmith@mail.com", "john00@mail.com"], ["John", "johnnybravo@mail.com"], ["John", "johnsmith@mail.com", "john_newyork@mail.com"], ["Mary", "mary@mail.com"]]
Output: [["John", 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com'],  ["John", "johnnybravo@mail.com"], ["Mary", "mary@mail.com"]]
Explanation: 
The first and third John's are the same person as they have the common email "johnsmith@mail.com".
The second John and Mary are different people as none of their email addresses are used by other accounts.
We could return these lists in any order, for example the answer [['Mary', 'mary@mail.com'], ['John', 'johnnybravo@mail.com'], 
['John', 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com']] would still be accepted.
Note:

The length of accounts will be in the range [1, 1000].
The length of accounts[i] will be in the range [1, 10].
The length of accounts[i][j] will be in the range [1, 30].*/

import java.util.*;

public class AccountsMerge {
	// email : person
	Map<String, String> owner;
	// email : email root
	Map<String, String> parent;
	// email root : emails in group
	Map<String, TreeSet<String>> group;

	public List<List<String>> accountsMerge(List<List<String>> accounts) {
		owner = new HashMap<>();
		parent = new HashMap<>();
		group = new HashMap<>();
		List<List<String>> res = new ArrayList<>();
		for (List<String> acct : accounts) {
			String person = acct.get(0);
			for (int i = 1; i < acct.size(); i++) {
				String email = acct.get(i);
				parent.put(email, email);
				owner.put(email, person);
			}
		}
		for (List<String> acct : accounts) {
			String root = find(acct.get(1));
			for (int i = 2; i < acct.size(); i++) {
				union(acct.get(i), root);
			}
		}
		for (List<String> acct : accounts) {
			String root = find(acct.get(1));
			if (!group.containsKey(root)) {
				group.put(root, new TreeSet<>());
			}
			for (int i = 1; i < acct.size(); i++) {
				group.get(root).add(acct.get(i));
			}
		}
		for (String root : group.keySet()) {
			List<String> ls = new ArrayList<>(group.get(root));
			ls.add(0, owner.get(root));
			res.add(ls);
		}
		return res;
	}

	private String find(String email) {
		while (!email.equals(parent.get(email))) {
			email = parent.get(email);
		}
		return email;
	}

	private void union(String a, String b) {
		String root_a = find(a);
		String root_b = find(b);
		parent.put(root_a, root_b);
	}
}
