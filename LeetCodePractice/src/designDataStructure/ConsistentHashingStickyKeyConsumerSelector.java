package designDataStructure;

import java.util.*;

public class ConsistentHashingStickyKeyConsumerSelector {
    NavigableMap<Integer, List<Consumer>> hashRing = new TreeMap<>();
    // count of virtual points for a consumer
    int numberOfPoints = 100;

    public void addConsumer(Consumer consumer){
        for(int i = 0; i < numberOfPoints; i++){
            String key = consumer.consumerName() + i;
            int hash = makeHash(key.getBytes());
            hashRing.computeIfAbsent(hash, x -> new ArrayList<>()).add(consumer);
        }
    }

    public void removeConsumer(Consumer consumer){
        for(int i = 0; i < numberOfPoints; i++){
            String key = consumer.consumerName() + i;
            int hash = makeHash(key.getBytes());
            if(hashRing.containsKey(hash)){
                hashRing.get(hash).remove(consumer);
            }
        }
    }

    public Consumer select(int hash){
        if(hashRing.isEmpty()){
            return null;
        }
        Map.Entry<Integer, List<Consumer>> ceilingEntry = hashRing.ceilingEntry(hash);
        List<Consumer> consumerList;
        if(ceilingEntry != null){
            consumerList = ceilingEntry.getValue();
        } else {
            consumerList = hashRing.firstEntry().getValue();
        }
        return consumerList.get(hash % consumerList.size());
    }

    private int makeHash(byte[] arr){
        return arr.hashCode();
    }

    class Consumer {
        public String consumerName(){
            return "dummy";
        }

    }
}
