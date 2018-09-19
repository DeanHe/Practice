// https://www.lintcode.com/problem/log-sorting/description

import java.util.*;

public class LogSorting {
	/**
     * @param logs: the logs
     * @return: the log after sorting
     */
    class MyCompartor implements Comparator {
        @Override
        public int compare(Object a1, Object a2) {
            String o1 = (String)a1;
            String o2 = (String)a2;
            int idx1 = o1.indexOf(' ');
            int idx2 = o2.indexOf(' ');
            String head1 = o1.substring(0, idx1);
            String head2 = o2.substring(0, idx2);
            String body1 = o1.substring(idx1);
            String body2 = o2.substring(idx2);
            if(body1.equals(body2)) {
                return head1.compareTo(head2);
            } else {
                return body1.compareTo(body2);
            }
        }
    } 
    
    public String[] logSort(String[] logs) {
        // Write your code here
        List<String> list = new ArrayList<String>();
        String [] ans = new String[logs.length];
        int cnt = logs.length - 1;
        for(int i = logs.length - 1; i >= 0; i--) {
            int index = logs[i].indexOf(' ');
            String body = logs[i].substring(index + 1);
            if(body.charAt(0) >= '0' && body.charAt(0) <= '9') {
                ans[cnt--] = logs[i];
            } else {
                list.add(new String(logs[i]));
            }
        }
        MyCompartor mc = new MyCompartor();
        Collections.sort(list, mc);
        
        cnt = 0;
        for(String i: list) {
            ans[cnt++] = i; 
        }
        return ans;
    }
}
