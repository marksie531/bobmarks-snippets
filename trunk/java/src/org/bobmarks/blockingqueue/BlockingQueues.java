package org.bobmarks.blockingqueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * @author b.marks
 *
 */
public class BlockingQueues {

    public static void main(String [] args) throws Exception {
        int i = 0;
       
        BlockingQueue<String> q = new SynchronousQueue<String>(true);
        //BlockingQueue<String> q = new LinkedBlockingQueue<String>();
       
        Producer p = new Producer(q);
        Consumer c1 = new Consumer("1", q);
        Consumer c2 = new Consumer("2", q);
        Consumer c3 = new Consumer("3", q);
        new Thread(p).start();
        new Thread(c1).start();
        new Thread(c2).start();
        new Thread(c3).start();
    }
}

/**
 * Producer
 */
 class Producer implements Runnable {
     private final BlockingQueue<String> queue;

     Producer(BlockingQueue<String> q) {
         queue = q;
     }

     public void run() {
         try {
             while (true) {
                 Thread.sleep(500);
                 queue.put(produce());
             }
         } catch (InterruptedException ex) {
             //
         }
     }

     String produce() {
         return String.valueOf(Math.random() * 1000);
     }
 }

 /**
  * Consumer.
  */
 class Consumer implements Runnable {
     private final BlockingQueue<String> queue;
     private final String name;
     
     Consumer(String n, BlockingQueue<String> q) {
         name = n;
         queue = q;
     }

     public void run() {
         try {
             while (true) { consume(queue.take()); }
         } catch (InterruptedException ex) {
         }
     }

     void consume(String x) {
         System.out.println ("\t" + name + " - " + x);
     }
 }