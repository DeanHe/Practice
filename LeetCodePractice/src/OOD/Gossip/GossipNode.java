package OOD.Gossip;

/*
gossip protocol
 */
import java.util.*;

import static java.util.stream.Collectors.joining;

public class GossipNode {
    int nodeId;
    public List<GossipNode> neighbors;
    public Set<Integer> canReach;
    public Set<GossipMessage> seenMsg;

    public GossipNode(int id){
        nodeId = id;
        neighbors = new ArrayList<>();
        canReach = new HashSet<>();
        seenMsg = new HashSet<>();
    }

    public void send(GossipMessage msg){
        msg.visited.add(this.nodeId);
        seenMsg.add(msg);
        for(GossipNode nb : neighbors){
            nb.receive(msg);
        }
    }

    public void receive(GossipMessage msg){
        if(seenMsg.contains(msg)){
            return;
        }
        canReach.addAll(msg.visited);
        System.out.println(String.format("at Node %s receive msg %s", nodeId, msg.visited.stream().map(String::valueOf).collect(joining())));
        send(msg);
    }
}