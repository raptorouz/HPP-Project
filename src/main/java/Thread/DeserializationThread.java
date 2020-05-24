package Thread;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.swing.plaf.SliderUI;

import Model.Top3;
import Model.Top9;

public class DeserializationThread implements Runnable {
	
	ConcurrentLinkedQueue<Top3> waitingQueue;
	Top9 top9;
	FileWriter writer;
	BufferedWriter buffer;
	
	private final static int WAIT_FOR_MILLIS = 50;
	
	public DeserializationThread(ConcurrentLinkedQueue<Top3> waitingQueue) {

		this.waitingQueue = waitingQueue;
		this.top9 = new Top9();
		
		try {
			writer = new FileWriter("Global_Top3.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}  
	    buffer = new BufferedWriter(writer); 
	}
	
	public synchronized void writeInFile(Top3 item) {
		
	    try {
			buffer.write(top9.toTop3().toString() + "\n");
		    //buffer.flush();		
		} catch (IOException e) {
			e.printStackTrace();
		}  
	}

	@Override
	public void run() {
		while(true)
		{
			Top3 element;
			while((element = waitingQueue.poll()) != null)
			{
				top9.updatePartOfTop9(element);
				writeInFile(element);
			}
			
			if(Thread.interrupted())
			{
				try {
					buffer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			}
			
			try {
				Thread.sleep(WAIT_FOR_MILLIS);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}
	
	}
	
}
