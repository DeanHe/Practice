package OOD.Gossip;

import java.util.HashSet;
import java.util.Set;

public class GossipMessage {
    int msgId;
    Set<Integer> visited;
    public GossipMessage(int id){
        msgId = id;
        visited = new HashSet<>();
    }

    @Override
    public String toString() {
        return "id=" + msgId + ",visted:" + visited.toString();
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + msgId + visited.hashCode();
        return result;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        GossipMessage other = (GossipMessage) obj;
        if (msgId != other.msgId) {
            return false;
        }
        if(!visited.equals(other.visited)){
            return false;
        }
        return true;
    }
}
