package MultiThread;

import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Client {

    private BlockingQueue<Member> memberList;

    private BlockingQueue<Member> deadList;

    private Member me;


    public Client() throws InterruptedException, SocketException, UnknownHostException {

        memberList = new ArrayBlockingQueue<Member>(10);

        deadList = new ArrayBlockingQueue<Member>(10);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("a new thread is here");
            }
        });

        Thread thread2 = new Thread(() -> {
            System.out.println("a new thread is here");
        });
    }

    public void addMember(Member member) throws InterruptedException {
        synchronized (this.me){
            memberList.put(member);
        }
    }

    public Member getOneMember() throws InterruptedException{
        synchronized (this.me){
            return memberList.take();
        }
    }

    private void start() throws InterruptedException {
        ExecutorService executor = Executors.newCachedThreadPool();
        executor.execute(new Runnable() {
            @Override
            public void run() {

            }
        });
    }

    public static void main(String[] args) throws InterruptedException, SocketException, UnknownHostException {
        Client client = new Client();
        client.start();
    }

}
