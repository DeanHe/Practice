package Array;

public class CircularBuffer {
	private int capacity;
	private int start = 0;
	private int currentSize = 0;
	private int[] buffer;
	
	public CircularBuffer(int cap){
		this.capacity = cap;
		buffer = new int[cap];
	}
	
	private boolean isEmpty(){
		return currentSize == 0;
	}
	private boolean isFull() {
		return currentSize == capacity;
	}
	
	public synchronized void produce(int data){
		while(isFull()){
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		buffer[(start + currentSize) % capacity] = data;
		currentSize++;
		notifyAll();
	}
	
	public synchronized int consume(){
		while(isEmpty()){
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		int data = buffer[start];
		start = (start + 1) % capacity;
		currentSize--;
		notifyAll();
		return data;
	}
}
