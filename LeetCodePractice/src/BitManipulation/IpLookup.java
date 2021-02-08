package BitManipulation;

import java.util.HashMap;
import java.util.Map;

/*
#Google
Given a destination ip, and a routing table of net_ip/cidr -> next_hop_ip, find the next_hop_ip for the destination ip
if there are multiple matching cidr, return the correspondent cidr has largest range
 */
public class IpLookup {
    BNode root = new BNode();

    public String findNextHopIp(String ip, Map<String, String> routingTable) {
        for (String ip_cidr : routingTable.keySet()) {
            String[] sp = ip_cidr.split("/");
            int prefix = Integer.parseInt(sp[1]);
            byte[] binaryArr = ipToByteArray(sp[0]);
            String next_hop_ip = routingTable.get(ip_cidr);
            insert(binaryArr, prefix, next_hop_ip);
        }
        byte[] ipBinaryArr = ipToByteArray(ip);
        return search(ipBinaryArr);
    }

    public void test() {
        String ip = "255.0.0.10";
        Map<String, String> routingTable = new HashMap<>();
        routingTable.put("255.0.0.16/32", "A");
        routingTable.put("255.0.0.8/29", "B");
        routingTable.put("255.0.0.0/28", "C");
        routingTable.put("255.0.0.156/29", "D");
        System.out.println(findNextHopIp(ip, routingTable));
    }

    private byte[] ipToByteArray(String ip) {
        byte[] res = new byte[4];
        String[] sections = ip.split("\\.");
        for (int i = 0; i < 4; i++) {
            int num = Integer.parseInt(sections[i]);
            res[i] = (byte) num;
        }
        return res;
    }

    private void insert(byte[] byteArr, int prefix, String next_hop_ip) {
        BNode cur = root;
        for (byte by : byteArr) {
            for (int i = 7; i >= 0; i--) {
                int bit = by & (1 << i);
                if (prefix == 0) {
                    cur.next_hop_ip = next_hop_ip;
                    return;
                }
                if (bit == 0) {
                    if (cur.arr[0] == null) {
                        cur.arr[0] = new BNode();
                    }
                    cur = cur.arr[0];
                } else {
                    if (cur.arr[1] == null) {
                        cur.arr[1] = new BNode();
                    }
                    cur = cur.arr[1];
                }
                prefix--;
            }
        }
    }

    private String search(byte[] byteArr) {
        BNode cur = root;
        for (byte by : byteArr) {
            for (int i = 7; i >= 0; i--) {
                int bit = by & (1 << i);
                if (cur.next_hop_ip != null) {
                    return cur.next_hop_ip;
                }
                if (bit == 0) {
                    if (cur.arr[0] == null) {
                        cur.arr[0] = new BNode();
                    }
                    cur = cur.arr[0];
                } else {
                    if (cur.arr[1] == null) {
                        cur.arr[1] = new BNode();
                    }
                    cur = cur.arr[1];
                }
            }
        }
        return "not found";
    }

    private class BNode {
        public BNode[] arr;
        public String next_hop_ip;

        public BNode() {
            arr = new BNode[2];
            next_hop_ip = null;
        }
    }

}
