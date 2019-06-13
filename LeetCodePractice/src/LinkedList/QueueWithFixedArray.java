package LinkedList;
/*
Assume in your programming language you only have a fixed size array of length 5. 
Implement a queue datastructure that can get unlimitted number of elements.

Build a queue class with the enqueue and dequeue methods. The queue can store an UNLIMITED number of elements 
but you are limited to using arrays that can store up to 5 elements max.

test:
		QueueWithFixedArray queue = new QueueWithFixedArray(5);
		for(int i = 0; i < 16; i++){
			queue.offer(2);
		}
		for(int i = 0; i < 16; i++){
			try {
				int val = queue.poll();
				System.out.println(val);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
*/
public class QueueWithFixedArray {
	int fixedSize;
	QueueNode first;
	QueueNode last;
	int total;
	
	public QueueWithFixedArray(int fixedSize){
		this.fixedSize = fixedSize;
		first = new QueueNode();
		last = first;
	}
	
	public void offer(int num) {
		last.offer(num);
		if(last.end == fixedSize){
			last.next = new QueueNode();
			last = last.next;
		}
		total++;
	}
	
	public int poll() throws Exception {
		if(total == 0){
			throw new Exception("queue is empty");
		}
		int val = first.poll();
		if(first.start == fixedSize){
			first = first.next;
		}
		total--;
		return val;
	}
	
	public int size(){
		return total;
	}
	
	class QueueNode {
		int start;
		int end;
		int[] arr;
		QueueNode next;
		
		public QueueNode() {
			arr = new int[fixedSize];
			start = 0;
			end = 0;
		}
		public void offer(int num){
			arr[end] = num;
			end++;
		}
		
		public int poll(){
			int val = arr[start];
			start++;
			return val;
		}
	}
}
