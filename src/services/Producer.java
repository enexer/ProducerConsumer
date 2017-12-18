package services;

import java.util.LinkedList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class Producer implements Runnable {

    private final BlockingQueue<Figure> sharedQueue;
    private String threadNo;
    private LinkedList<Figure> lista_new;
    private boolean bol;
    private int time = 50;

    public Producer(BlockingQueue<Figure> sharedQueue, String threadNo, LinkedList<Figure> lista_new) {
        this.threadNo = threadNo;
        this.sharedQueue = sharedQueue;
        this.lista_new = lista_new;
    }

    public Producer(int time, BlockingQueue<Figure> sharedQueue, String threadNo, LinkedList<Figure> lista_new) {
        this.threadNo = threadNo;
        this.sharedQueue = sharedQueue;
        this.lista_new = lista_new;
        this.time = time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public LinkedList<Figure> getLista_new() {
        return lista_new;
    }

    public void setLista_new(LinkedList<Figure> lista_new) {
        this.lista_new = lista_new;
    }


    @Override
    public void run() {
        bol = true;
        int i = 0;

        try {
            while (bol) {
                if (lista_new.size() == 0) {
                    bol = false;
                    ProducerConsumer.callback.updateViewProducer("****** Finished by thread:" + threadNo, false, sharedQueue.size(), lista_new.size(), threadNo);
                } else {
                    ++i;
//                    System.out.println("#"+i +" "+ lista_new.getFirst().toString() + ":by thread:" + threadNo);
                    ProducerConsumer.callback.updateViewProducer("#" + i + " Prod. " + lista_new.getFirst().toString() + ":by thread:" + threadNo + ",t:" + time, true, sharedQueue.size(), lista_new.size(), threadNo);
                    sharedQueue.put(lista_new.getFirst());
                    lista_new.removeFirst();

                    System.out.println("i " + i + ", LISTA SIZE:" + lista_new.size() + "  SHARED SIZE:" + sharedQueue.size() + "   " + threadNo + " ");
                }
                TimeUnit.MILLISECONDS.sleep(time);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

