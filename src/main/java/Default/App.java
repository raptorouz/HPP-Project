package Default;

import Model.Forest;
import Model.Top3Chains;
import Thread.DeserializationThread;
import Thread.WorkerThread;

import java.io.IOException;
import java.util.concurrent.ConcurrentLinkedQueue;

import Model.Forest.Country;
import Model.Top3;

public class App {
	
	public static void main(String[] args) {

		Country countries[] = {Country.FRANCE, Country.SPAIN, Country.ITALY };
		String path = "resources/data/50000/";
		
		ConcurrentLinkedQueue<Top3> top3Fifo = new ConcurrentLinkedQueue<Top3>();
		
		WorkerThread workers[] = new WorkerThread[3];
		Thread threads[] = new Thread[3];
		for(int i = 0; i < 3; ++i) {
			workers[i] = new WorkerThread(countries[i], path, top3Fifo);
			threads[i] = new Thread(workers[i]);
		}
		
		
		long start = System.nanoTime();
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
		
		long end = System.nanoTime();
		float ms = (end - start) / 1000000;
		System.out.println(ms + " ms");
	}

}

