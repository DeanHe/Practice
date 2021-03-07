package OOD.KeyValueStoreWithTransaction;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/*
implement (code) a Key value store with transactions.

Write a Fully functional code in 25-30 min in interview with test cases

Set
Get
Delete

are methods in Key value store

for transactions
Begin
Commit
Rollback
 */
public class KVstore {
    private Deque<ConcurrentHashMap<String, String>> store;

    public KVstore() {
        store = new ArrayDeque<>();
        store.offerLast(new ConcurrentHashMap<>());
    }

    public void Begin() {
        store.offerLast(new ConcurrentHashMap<>());
    }

    public void Commit() {
        if (store.size() > 1) {
            ConcurrentHashMap<String, String> map = store.pollLast();
            for (Map.Entry<String, String> entry : map.entrySet()) {
                store.peekLast().put(entry.getKey(), entry.getValue());
            }
        }
    }

    public void rollback() {
        store.pollLast();
    }

    public String Get(String key) {
        Iterator<ConcurrentHashMap<String, String>> descendingIter = store.descendingIterator();
        while (descendingIter.hasNext()) {
            ConcurrentHashMap<String, String> map = descendingIter.next();
            if (map.contains(key)) {
                return map.get(key);
            }
        }
        return "Not Set!";
    }

    public void Set(String key, String value) {
        store.peekLast().put(key, value);
    }

    public void Delete(String key) {
        if (store.peekLast().contains(key)) {
            store.peekLast().remove(key);
        }
    }

    public void test() {
        Begin();
        Set("k1", "v1");
        System.out.println(Get("k1"));
        Begin();
        System.out.println(Get("k1"));
        Commit();
        Commit();
    }
}
