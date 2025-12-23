package OOD.ConsistentHashing;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
import java.util.SortedMap;
import java.util.TreeMap;

public class ConsistentHashing {
    public final TreeMap<Long, String> hashRing;

    private final int numOfReplicas;

    // hash computer: using SHA-256 algorithm
    private final MessageDigest md;

    public ConsistentHashing(int numOfReplicas) throws NoSuchAlgorithmException {
        this.hashRing = new TreeMap<>();
        this.numOfReplicas = numOfReplicas;
        this.md = MessageDigest.getInstance("SHA-256");
    }

    public void addServer(String server) {
        for (int i = 0; i < numOfReplicas; i++) {
            String virtualNode = server + ":" + i;
            long hash = computeHash(virtualNode);
            hashRing.put(hash, server);
        }
    }

    public void removeServer(String server) {
        for (int i = 0; i < numOfReplicas; i++) {
            String virtualNode = server + ":" + i;
            long hash = computeHash(virtualNode);
            hashRing.remove(hash);
        }
    }

    public String getServer(String virtualNode) {
        if (hashRing.isEmpty()) {
            return null;
        }

        long hash = computeHash(virtualNode);
        if (!hashRing.containsKey(hash)) {
            SortedMap<Long, String> tailMap = hashRing.tailMap(hash);
            if (!tailMap.isEmpty()) {
                hash = tailMap.firstKey();
            } else {
                hash = hashRing.firstKey();
            }
        }

        return hashRing.get(hash);
    }

    private long computeHash(String s) {
        md.reset();
        md.update(s.getBytes());
        byte[] digest = md.digest(); // compute SHA-256 hash
        // extract first 8 bytes(64 bits) and convert to long
        long hash = ((long) (digest[0] & 0xFF) << 56) |
                ((long) (digest[1] & 0xFF) << 48) |
                ((long) (digest[2] & 0xFF) << 40) |
                ((long) (digest[3] & 0xFF) << 32) |
                ((long) (digest[4] & 0xFF) << 24) |
                ((long) (digest[5] & 0xFF) << 16) |
                ((long) (digest[6] & 0xFF) << 8)  |
                ((long) (digest[7] & 0xFF));
        return hash;
    }

    public static void main(String[] args) {
        try {
                ConsistentHashing ch = new ConsistentHashing(3);
                Scanner scanner = new Scanner(System.in);
                while (true) {
                    System.out.println("\n consistent hash demo");
                    System.out.println("1. add server");
                    System.out.println("2. remove server");
                    System.out.println("3. look up a server by virtualNode key");
                    System.out.println("4. show all servers");
                    System.out.println("5. exit");
                    System.out.print("please choose: ");
                    int choice = scanner.nextInt();
                    scanner.nextLine();
                    switch (choice) {
                        case 1:
                            System.out.print("input the new server name: ");
                            String serverToAdd = scanner.nextLine();
                            ch.addServer(serverToAdd);
                            System.out.println("server added: " + serverToAdd);
                            break;
                        case 2:
                            System.out.print("input the server name to remove: ");
                            String serverToRemove = scanner.nextLine();
                            ch.removeServer(serverToRemove);
                            System.out.println("server removed: " + serverToRemove);
                            break;
                        case 3:
                            System.out.print("input virtualNode key: ");
                            String key = scanner.nextLine();
                            String server = ch.getServer(key);
                            System.out.println("key '" + key + "' correspondent server: " + server);
                            break;
                        case 4:
                            System.out.println("all servers:");
                            System.out.println(ch.hashRing.toString());
                            break;
                        case 5:
                            System.out.println("exit...");
                            scanner.close();
                            System.exit(0);
                        default:
                            System.out.println("invalid option，please choose again");
                    }
                }
        } catch (NoSuchAlgorithmException e) {
            System.err.println("error: " + e.getMessage());
        }
    }
}
