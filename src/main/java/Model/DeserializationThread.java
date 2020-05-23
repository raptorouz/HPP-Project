package Model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.swing.plaf.SliderUI;

public class DeserializationThread implements Runnable {
	
	ConcurrentLinkedQueue<Top3> waitingQueue;
	Top9 top9;
	FileWriter writer;
	BufferedWriter buffer;
	
	public DeserializationThread(ConcurrentLinkedQueue<Top3> waitingQueue) throws IOException {

		this.waitingQueue = waitingQueue;
		this.top9 = new Top9();
		
		writer = new FileWriter("Global_Top3.txt");  
	    buffer = new BufferedWriter(writer); 
	}
	
	public void WriteInFile(Top3 item) throws IOException {
		
	    buffer.write(top9.toTop3().toString());  
	    buffer.flush();		
	}

	@Override
	public void run() {
		while(true)
		{
			Top3 element;
			while((element=waitingQueue.poll()) != null)
			{
				top9.updatePartOfTop9(element);
				try {
					WriteInFile(element);
				} catch (IOException e) {

					e.printStackTrace();
				}
			}
			try {
				if(Thread.interrupted())
				{
					buffer.close();
					break;
				}
				Thread.sleep(20);
			} catch (InterruptedException | IOException e) {
				e.printStackTrace();
			}
		}	
	}
}
