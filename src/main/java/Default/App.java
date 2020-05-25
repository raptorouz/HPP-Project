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
		String path = "resources/data/100000/";

		long start = System.nanoTime();
		String res = "NO result Yet !";
		try {
			res = process(path, countries);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		long end = System.nanoTime();
		double s = (end - start) / 1000000000.0;
		System.out.println(s + " s");
		
		System.out.println(res);
		
		System.exit(0);
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

