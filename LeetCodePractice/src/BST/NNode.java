package BST;

import java.util.ArrayList;
import java.util.List;

public class NNode {
	public int val;
    public List<NNode> children;
 
    public NNode() {}
 
    public NNode(int val) {
        this.val = val;
        this.children = new ArrayList<>();
    }
}
