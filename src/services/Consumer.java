package services;

import java.util.LinkedList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class Consumer implements Runnable {

    private final BlockingQueue<Figure> sharedQueue;
    private String threadNo;
    private int time = 50;
    private boolean bol;
    private LinkedList<Figure> lista_new;

    public Consumer(BlockingQueue<Figure> sharedQueue, String threadNo, LinkedList<Figure> lista_new) {
        this.sharedQueue = sharedQueue;
        this.threadNo = threadNo;
        this.lista_new = lista_new;
    }

    public Consumer(int time, BlockingQueue<Figure> sharedQueue, String threadNo, LinkedList<Figure> lista_new) {
        this.sharedQueue = sharedQueue;
        this.threadNo = threadNo;
        this.lista_new = lista_new;
        this.time = time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    @Override
    public void run() {
        int i = 0;
        bol = true;
        Figure f;


        try {
            while (bol) {
//                System.out.println(bol + threadNo);
                ++i;

//                f=sharedQueue.poll(time*time,TimeUnit.MILLISECONDS);
                f = sharedQueue.take();

                if (f != null) {
//                    System.out.println("cons: "+f.toString());
                    ProducerConsumer.callback.updateViewConsumer("#" + i + " Cons.: " + f.toString() + ", Volume{" + f.volume() + "},by thread:" + threadNo + ",t:" + time, true, sharedQueue.size(), threadNo);
                    TimeUnit.MILLISECONDS.sleep(time);
                }

                if (lista_new.size() == 0 && sharedQueue.size() == 0) {
                    ProducerConsumer.callback.updateViewConsumer("****** Finished by thread:" + threadNo, false, sharedQueue.size(), threadNo);
                    bol = false;
                }

                System.out.println("i " + i + ", LISTA SIZE:" + lista_new.size() + "  SHARED SIZE:" + sharedQueue.size() + "   " + threadNo + " ");
//                System.out.println(i + " Consumed: " + sharedQueue.element().toString() + ":by thread:" + threadNo);
//                ProducerConsumer.callback.updateViewConsumer("#"+i + " Cons.: " + f.toString() +", Volume{"+f.volume()+ "},by thread:" + threadNo+",t:"+time, true, threadNo);
//                TimeUnit.MILLISECONDS.sleep(time);
            }
            System.out.println("T: " + threadNo + ", FINISHED");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

}

