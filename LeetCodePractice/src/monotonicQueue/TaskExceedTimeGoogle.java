package monotonicQueue;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

/*
given an array of tasks [time, task_id, start/finish], and maxProcessTime which allows each task maximum run time
for example: [[1, 1, start], [3, 2, start], [6, 2, finish], [8, 1, finish]]

return the earliest time any task exceeds limit.
 */
public class TaskExceedTimeGoogle {
    public int terminate(Task[] tasks, int maxProcessTime){
        Deque<Task> deque = new ArrayDeque<>();
        Set<Integer> active = new HashSet<>();
        for(Task task : tasks){
            if(!deque.isEmpty()){
                if(!active.contains(deque.peek().id)){
                    deque.pollFirst();
                }
                if(deque.peek().time <= task.time - maxProcessTime){
                    return task.time;
                }
            }
            if(task.start){
                active.add(task.id);
                deque.offerLast(task);
            } else {
                active.remove(task.id);
            }
        }
        return -1;
    }

    public class Task {
        int id, time;
        boolean start;
        public Task(int id, int time, boolean start){
            this.id = id;
            this.time = time;
            this.start = start;
        }
    }
}
