package bst.iterator;

import java.util.ArrayList;
import java.util.List;

public class DecodingTreeNode {
    public String id, value;
    public List<DecodingTreeNode> children;

    public DecodingTreeNode(String id){
        this.id = id;
        this.value = "";
        this.children = new ArrayList<>();
    }
}
