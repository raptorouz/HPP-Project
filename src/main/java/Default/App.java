package Default;

import Thread.DeserializationThread;
import Thread.WorkerThread;

import java.util.concurrent.ConcurrentLinkedQueue;

import Model.Forest.Country;
import Model.Top3;

public class App {
	
	public static void main(String[] args) {

		Country countries[] = {Country.FRANCE, Country.SPAIN, Country.ITALY };
		//String path = "resources/data/1000000/";
		String path = "resources/data/50000/";

		long start = System.nanoTime();
		String res = "NO result Yet !";
		try {
			res = process(path, countries);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long end = System.nanoTime();
		float ms = (end - start) / 1000000;
		System.out.println(ms + " ms");
		
		System.out.println(res);
	}
	
	public static String process(String path, Country countries[]) throws InterruptedException {
		ConcurrentLinkedQueue<Top3> top3Fifo = new ConcurrentLinkedQueue<Top3>();
		
		WorkerThread workers[] = new WorkerThread[3];
		Thread threads[] = new Thread[3];
		for(int i = 0; i < 3; ++i) {
			workers[i] = new WorkerThread(countries[i], path, top3Fifo);
			threads[i] = new Thread(workers[i]);
		}
		
		
		for(int i = 0; i < 3; ++i) {
			threads[i].start();
		}
		
		DeserializationThread deserRunnable = new DeserializationThread(top3Fifo);
		Thread deserThread = new Thread(deserRunnable);
		deserThread.start();
		
		for(int i = 0; i < 3; ++i) {
			try {
				threads[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		deserThread.interrupt();
		deserThread.join();
		return deserRunnable.toString();
	}

}

