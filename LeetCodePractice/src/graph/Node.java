package graph;

import java.util.ArrayList;
import java.util.List;

public class Node {
    public int id;
    public List<Node> neighbors;

    public Node(int id){
        this.id = id;
        neighbors = new ArrayList<>();
    }
}
