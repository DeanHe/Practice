package string;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

/*
There were too many IP blocks to store and we want to reduce the storage size by minimizing the set of address block.
For instance, 172.226.69.220/31 and 172.226.69.222/31 can be merged into 172.226.69.220/30

Given the IPv4 address blocks, write a program to minimize the set of blocks which has the same coverage as input data

Example:
input:
172.224.224.32/31
172.224.224.34/31
172.224.224.36/31
146.75.169.110/31
146.75.169.112/31
146.75.169.114/31
146.75.169.116/31
146.75.169.118/31

output:
146.75.169.110/31
146.75.169.112/29
172.224.224.32/30
172.224.224.36/31

constraint:
2 <= n <= 10^5
 */
public class MergeIpSubset {
    public List<String> merge(String[] input){
        SortedSet<String> set = new TreeSet<>();
        for(String subset : input){
            check(subset, set);
        }
        return new ArrayList<>(set);
    }

    private void check(String subset, SortedSet<String> set) {
        String s_binary = toBinary(subset);
        String s_complement = toComplement(s_binary);
        List<String> toRemove = new ArrayList<>();
        for(String e : set){
            String e_binary = toBinary(e);
            if(e_binary.equals(s_complement)){
                toRemove.add(e);
                s_binary = update(s_binary);
            }
        }
        String merged = toSubSet(s_binary);
        if(toRemove.isEmpty()){
            set.add(merged);
            return;
        }
        for(String e : toRemove){
            set.remove(e);
        }
        check(merged, set);
    }

    private String toSubSet(String binary) {
        int len = binary.length();
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < 32; i++){
            if(i < len){
                sb.append(binary.charAt(i));
            } else {
                sb.append('0');
            }
        }
        StringBuilder res = new StringBuilder();
        for(int i = 0; i + 8 <= 32; i += 8){
            String sub = sb.substring(i, i + 8);
            int val = Integer.parseInt(sub, 2);
            res.append(val);
            res.append('.');
        }
        res.deleteCharAt(res.length() - 1);
        res.append('/');
        res.append(len);
        return res.toString();

    }

    private String toComplement(String binary) {
        StringBuilder sb = new StringBuilder();
        int len = binary.length();
        sb.append(binary.substring(0, len - 1));
        if(binary.charAt(len - 1) == '1'){
            sb.append('0');
        } else {
            sb.append('1');
        }
        return sb.toString();
    }

    private String update(String binary) {
        int len = binary.length();
        return binary.substring(0, len - 1);
    }

    private String toBinary(String subset){
        StringBuilder sb = new StringBuilder();
        String[] arr = subset.split("/");
        String[] subs = arr[0].split("\\.");
        for(String sub : subs){
            String binary = Integer.toBinaryString(Integer.valueOf(sub));
            binary = String.format("%8s", binary).replaceAll(" ", "0");
            sb.append(binary);
        }
        int len = Integer.valueOf(arr[1]);
        return sb.substring(0, len);
    }
}
