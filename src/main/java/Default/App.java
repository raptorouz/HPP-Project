package Default;

import Model.Forest;
import Model.Top3Chains;
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
		
		
		for(int i = 0; i < 3; ++i) {
			threads[i].start();
		}
	}

}

