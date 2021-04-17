package sweepLine;

import java.util.*;
/*
Google interview
assign jobs to machine, job has (jobId, startTime, endTime), each machine can only process one job concurrently,
you need to add more machines when necessary. output the schedule
 */
public class JobSchedule {

    public void test(){
        List<Job> jobs = new ArrayList<>();
        jobs.add(new Job(1, 0, 3));
        jobs.add(new Job(2, 1, 4));
        jobs.add(new Job(3, 2, 10));
        jobs.add(new Job(4, 8, 14));
        jobs.add(new Job(5, 11, 12));
        jobs.add(new Job(6, 13, 17));
        jobs.add(new Job(7, 20, 23));
        List<Pair> res = arrangeNoOverlap(jobs);
        for(Pair p : res){
            System.out.println("job id:" + p.job.id + ", machine id:" + p.machine.id);
        }
    }

    public List<Pair> arrangeNoOverlap(List<Job> jobs){
        int machineCnt = 0;
        PriorityQueue<Machine> machineQueue = new PriorityQueue<>(Comparator.comparingInt(Machine::getId));
        List<Pair> res = new ArrayList<>();
        List<Tick> axis = new ArrayList<>();
        Map<Job, Machine> map = new HashMap<>();
        for(Job job : jobs){
            axis.add(new Tick(job.start, job, true));
            axis.add(new Tick(job.end, job, false));
        }
        Collections.sort(axis, (a, b) -> {
            if(a.x != b.x){
                return a.x - b.x;
            } else {
                if(a.isStart){
                    return 1;
                } else {
                    return -1;
                }
            }
        });
        for(Tick tick : axis){
            if(tick.isStart){
                if(machineQueue.isEmpty()){
                    machineCnt++;
                    machineQueue.offer(new Machine(machineCnt));
                }
                Machine m = machineQueue.poll();
                res.add(new Pair(tick.job, m));
                map.put(tick.job, m);
            } else {
                Machine m = map.get(tick.job);
                machineQueue.offer(m);
            }
        }
        return res;
    }

    class Tick {
        int x;
        boolean isStart;
        Job job;
        Tick(int x, Job job, boolean isStart){
            this.x = x;
            this.job = job;
            this.isStart = isStart;
        }
    }

    class Pair {
        Job job;
        Machine machine;
        Pair(Job job, Machine machine){
            this.job = job;
            this.machine = machine;
        }
    }

    class Job {
        int id;
        int start, end;
        Job(int id, int start, int end){
            this.id = id;
            this.start = start;
            this.end = end;
        }
    }

    class Machine {
        int id;
        Machine(int id){
            this.id = id;
        }
        int getId(){
            return id;
        }
    }
}
